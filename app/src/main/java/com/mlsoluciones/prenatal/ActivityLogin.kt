package com.mlsoluciones.prenatal

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.ContentView
import com.mlsoluciones.prenatal.model.UsersDBHelper
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "com.mlsoluciones.prenatal"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usersDBHelper = UsersDBHelper(this)

        // mensaje de notificacion
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //selTexamenById()

        // agrega el icono de ir atras del menu actionBar
        var actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayShowHomeEnabled(true)

    }

    fun validarDatos(v: View){
        //activityHome()
        if(txtemail.text.toString().equals("")){
            Toast.makeText(this, "Por favor ingrese correo eléctronico", Toast.LENGTH_LONG).show()
        }
        else if(txtcontrasena.text.toString().equals("")){
            Toast.makeText(this, "Por favor ingrese la contraseña", Toast.LENGTH_LONG).show()
        }
        else{
            loginUser()
        }
    }

    fun loginUser(){
        val email = txtemail.text.toString()
        val pass = txtcontrasena.text.toString()
        var user = usersDBHelper.loginUser(email, pass)

        //Toast.makeText(this, "User::: " + user, Toast.LENGTH_LONG).show()
        if(user > 0){
            activityHome()
        }
        else{
            Toast.makeText(this, "Correo eléctronico o contraseña incorrectas", Toast.LENGTH_LONG).show()
        }
    }

    fun selTexamenById() {

        var mje: String = ""
        var examens = usersDBHelper.selTexamenById("EXAMEN3")

        examens.forEach {
            mje = mje + it.codigo + " - " + it.nombreExamen + " - " + it.trimestreHasta + " - " +
                    it.numTrimestre.toString() + " - " + it.imgExamen.toString() + " - "
        }
        //Toast.makeText(this, mje, Toast.LENGTH_LONG).show()
    }

    fun activityHome(){
        val intento1 = Intent(this, ActivityHome2::class.java)
        startActivity(intento1)
    }

    fun clickTextView(view: View) {
        mensajeNotificacion()
    }

    private fun mensajeNotificacion() {

        val intent = Intent(this, ActivityRecuperarClave::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val contentView = RemoteViews(packageName, R.layout.notification_layout)
        contentView.setTextViewText(R.id.txtTitle, "Notificación de contraseña")
        contentView.setTextViewText(R.id.txtContenido, "Presione aquí para recuperar tu contraseña")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.img_recuperar_clave)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.img_recuperar_clave))
                    .setContentIntent(pendingIntent)
        }
        else{

            builder = Notification.Builder(this)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.img_recuperar_clave)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.img_recuperar_clave))
                    .setContentIntent(pendingIntent)

        }
            notificationManager.notify(1234, builder.build())
    }

    // metodo que realiza el ir atras del actionBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}
