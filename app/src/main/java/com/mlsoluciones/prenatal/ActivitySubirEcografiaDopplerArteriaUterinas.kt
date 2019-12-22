package com.mlsoluciones.prenatal

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mlsoluciones.prenatal.model.UsersDBHelper
import kotlinx.android.synthetic.main.activity_subir_eccobtretica_transvaginal.*
import java.io.ByteArrayOutputStream

class ActivitySubirEcografiaDopplerArteriaUterinas : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText("")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ecografia -> {
                textMessage.setText("")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_vacuna -> {
                textMessage.setText("")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_examenes -> {
                textMessage.setText("")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_perfil -> {
                textMessage.setText("")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    var textNotification_badge: TextView? = null

    private val PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 101
    var image_uri: Uri? = null

    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_ecografia_doppler_arteria_uterinas)

        usersDBHelper = UsersDBHelper(this)

        selImgExamen()

        val navView: BottomNavigationView = findViewById(R.id.nav_view_bottom)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Se selecciona opcion por defecto del BottomNavigationView
        navView.getMenu().getItem(1).setChecked(true)

        btnCapturarExamen.setOnClickListener {
            //if system os is Marshmallow or Above, we need to request runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                    //permission was not enabled
                    val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    //show popup to request permission
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    //permission already granted
                    openCamera()
                }
            }
            else{
                //system os is < marshmallow
                openCamera()
            }
        }

        // agrega el icono de ir atras del menu actionBar
        var actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayShowHomeEnabled(true)
    }

    // se asocia el menu ActionBar a la activity actual
    public override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menuopciones, menu)

        val menuItem = menu.findItem(R.id.menunotificacion)

        val actionView = MenuItemCompat.getActionView(menuItem)
        textNotification_badge = actionView.findViewById(R.id.notification_badge) as TextView

        // se quita el badge de notificacion cuando no exista nada que notificar
        //textNotification_badge!!.setText("");
        //Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()
        //textNotification_badge!!.setBackgroundResource(android.R.color.transparent);

        // se muestra el badge de notificacion cuando exista que notificar
        textNotification_badge!!.setText("3");
        textNotification_badge!!.setBackgroundResource(R.drawable.badge_background);
        Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()

        actionView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                onOptionsItemSelected(menuItem)
            }
        })
        return true
    }

    // se muestra un mensaje dependiendo la opcion o el icono pulsado del menu ActionBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menunotificacion) {
            Toast.makeText(this, "Se presionó el ícono de notificación 1", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.btnMenuCerrarSesion) {
            val intento1 = Intent(this, ActivityLogin::class.java)
            startActivity(intento1)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    openCamera()
                }
                else{
                    //permission from popup was denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //called when image was captured from camera intent
        if (resultCode == Activity.RESULT_OK){
            image_view.alpha = 5F
            image_view.scaleType = ImageView.ScaleType.CENTER_CROP
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            image_view.setImageBitmap(imageBitmap)

            //set image captured to image view
            //image_view.setImageURI(image_uri)

        }
    }

    fun imageViewToByte(image: ImageView): ByteArray {
        val bitmap = (image.getDrawable() as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        Toast.makeText(this, "ArrayByte:: " + stream.toByteArray(), Toast.LENGTH_LONG).show()
        return stream.toByteArray()
    }

    fun addImagen(v: View){
        var imgExamen = imageViewToByte(image_view)
        var result = usersDBHelper.updateImgExamen("EXAMEN2", imgExamen)

        Toast.makeText(this, "Resultado " + result, Toast.LENGTH_LONG).show()

        if(result){
            Toast.makeText(this, "Imagen guardada exitosamente!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Imagen no guardo!", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("Range")
    fun selImgExamen() {

        var imagens = usersDBHelper.selImgExamen("EXAMEN2")
        Toast.makeText(this, "imagen data " + imagens, Toast.LENGTH_LONG).show()

        if(imagens != null){
            image_view.alpha = 5F
            image_view.scaleType = ImageView.ScaleType.CENTER_CROP
            image_view.setImageBitmap(getImage(imagens))
        }
        Toast.makeText(this, "Mostrando imagen " + imagens, Toast.LENGTH_LONG).show()
    }

    fun getImage(image: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(image, 0, image.size)
    }

    // metodo que realiza el ir atras del actionBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
