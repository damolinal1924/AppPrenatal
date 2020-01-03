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
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ActivitySubirExamHemogramaT3 : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText("")
                val intento1 = Intent(this, ActivityHome2::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ecografia -> {
                textMessage.setText("")
                val intento1 = Intent(this, EcografiaActivity::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_vacuna -> {
                textMessage.setText("")
                val intento1 = Intent(this, VacunaActivity::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_examenes -> {
                textMessage.setText("")
                val intento1 = Intent(this, ActivityExamenes::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_perfil -> {
                textMessage.setText("")
                val intento1 = Intent(this, ActivityPerfil::class.java)
                startActivity(intento1)
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
        setContentView(R.layout.activity_subir_exam_hemograma_t3)

        usersDBHelper = UsersDBHelper(this)

        selImgExamen()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Se selecciona opcion por defecto del BottomNavigationView
        navView.getMenu().getItem(3).setChecked(true)

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
     override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menuopciones, menu)

        val menuItem = menu.findItem(R.id.menunotificacion)

        val actionView = MenuItemCompat.getActionView(menuItem)
        textNotification_badge = actionView.findViewById(R.id.notification_badge) as TextView

        mostrarNotificacion()

        actionView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                onOptionsItemSelected(menuItem)
            }
        })

        return true
    }

     fun mostrarNotificacion(){
         var edadGestacional = showAllUsers()
         var numNotificacion = selNotificacion(edadGestacional)

        if(numNotificacion == 0){
            // se quita el badge de notificacion cuando no exista nada que notificar
            textNotification_badge!!.setText("")
            textNotification_badge!!.setBackgroundResource(android.R.color.transparent)
        }else{
            // se muestra el badge de notificacion cuando exista que notificar
            textNotification_badge!!.setText(numNotificacion.toString())
            textNotification_badge!!.setBackgroundResource(R.drawable.badge_background)
        }
    }

    // se muestra un mensaje dependiendo la opcion o el icono pulsado del menu ActionBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.menunotificacion) {
            val intento1 = Intent(this, ActivityNotificacion::class.java)
            startActivity(intento1)
            return true
        }
        if (id == R.id.btnMenuCerrarSesion) {
            val intento1 = Intent(this, ActivityLogin::class.java)
            startActivity(intento1)
            return true
        }
        if (id == R.id.menuSignoAlarma) {
            val intento1 = Intent(this, ActivitySignosAlarmas::class.java)
            startActivity(intento1)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun selNotificacion(edadGestacional: Float) : Int {
        var notificacion = usersDBHelper.selNotificacion(edadGestacional)

        return notificacion.size
    }

    fun showAllUsers():Float{
        var users = usersDBHelper.readAllUsers()
        var fechaUltimaMenstruacion: String = ""
        users.forEach {
            fechaUltimaMenstruacion = it.fechaUltimaMenstruacion
        }
        var edadGestacional: Float
        var resulEdadGestacional = calcularEdadGestacional(fechaUltimaMenstruacion)

        if(resulEdadGestacional >= 1F && resulEdadGestacional <= 12.6F ){
            edadGestacional = 12.6F
        }
        else if(resulEdadGestacional >= 13F && resulEdadGestacional <= 24.6F ){
            edadGestacional = 24.6F
        }
        else{
            edadGestacional = 40F
        }
        return edadGestacional
    }

    fun calcularEdadGestacional(fechaUltimaMestruacion: String) :Float{
        var totalSemanas: Float = 0F
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fecha = Date()
        val fechaActual = Calendar.getInstance()
        fechaActual.time = fecha

        var fechaMestruacionC = Calendar.getInstance()
        fechaMestruacionC.time = dateFormat.parse(fechaUltimaMestruacion)

        while(fechaMestruacionC.before(fechaActual) || fechaMestruacionC.equals(fechaActual)){

            fechaMestruacionC.add(Calendar.MONTH, 1)  // se le suma 1 mes
            //Toast.makeText(this, ""+fechaMestruacionC.time, Toast.LENGTH_LONG).show()

            if(fechaMestruacionC.before(fechaActual) || fechaMestruacionC.equals(fechaActual)) {
                totalSemanas += 4F
                if (totalSemanas == 12F || totalSemanas == 25F) {
                    totalSemanas += 1
                }
            }
        }

        fechaMestruacionC.add(Calendar.MONTH, -1)  // se le resta 1 mes
        //Toast.makeText(this, ""+fechaMestruacionC.time, Toast.LENGTH_LONG).show()

        while(fechaMestruacionC.before(fechaActual) || fechaMestruacionC.equals(fechaActual)){

            fechaMestruacionC.add(Calendar.DATE, 7)  // se le agrega 7 dias
            //Toast.makeText(this, ""+fechaMestruacionC.time, Toast.LENGTH_LONG).show()
            if(fechaMestruacionC.before(fechaActual) || fechaMestruacionC.equals(fechaActual)) {
                totalSemanas += 1
            }
        }

        fechaMestruacionC.add(Calendar.DATE, -7)  // se le resta 7 dias
        //Toast.makeText(this, ""+fechaMestruacionC.time, Toast.LENGTH_LONG).show()

        // se calcula la diferencia en dias que hay entre la fecha actual y la fecha de la semana de la ultima mestruacion fechaMestruacionC
        val millisAcompletarHastaFechaActual = fechaActual.timeInMillis - fechaMestruacionC.getTimeInMillis()
        val diasAcompletarHastaFechaActual = TimeUnit.MILLISECONDS.toDays(millisAcompletarHastaFechaActual)

        var semanas: Double
        // se divide la diferencia de dia entre 1 semana (7 dias)
        // para saber a cuantas semanas equivalen los dias de diferencia
        semanas = (diasAcompletarHastaFechaActual / "7".toDouble())

        // se limita a un solo decimal, sin redondear
        var diferenciaDias = BigDecimal(semanas)
        diferenciaDias = diferenciaDias.setScale(1, RoundingMode.DOWN)

        //Toast.makeText(this, "fecha actual: " + fechaActual.time + " FUM: " + fechaMestruacionC.time , Toast.LENGTH_LONG).show()
        //Toast.makeText(this, ""+diasAcompletarHastaFechaActual, Toast.LENGTH_LONG).show()

        if(diasAcompletarHastaFechaActual >= 0 && diasAcompletarHastaFechaActual < 7){
            val dias = "0."+diasAcompletarHastaFechaActual
            totalSemanas += dias.toFloat()
        }
        else{
            totalSemanas += diferenciaDias.toFloat()
        }

        return totalSemanas
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
        }
    }

    fun imageViewToByte(image: ImageView): ByteArray {
        val bitmap = (image.getDrawable() as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun addImagen(v: View){
        var imgExamen = imageViewToByte(image_view)
        var result = usersDBHelper.updateImgExamen("EXAMEN36", imgExamen)

        if(result){
            mostrarNotificacion()
            Toast.makeText(this, "Imagen guardada exitosamente!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Imagen no guardo!", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("Range")
    fun selImgExamen() {

        var imagens = usersDBHelper.selImgExamen("EXAMEN36")

            if(imagens != null){
                image_view.alpha = 5F
                image_view.scaleType = ImageView.ScaleType.CENTER_CROP
                image_view.setImageBitmap(getImage(imagens))
            }
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
