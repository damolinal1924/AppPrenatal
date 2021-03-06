package com.mlsoluciones.prenatal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mlsoluciones.prenatal.model.UsersDBHelper
import kotlinx.android.synthetic.main.activity_signos_alarmas.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ActivityNotificacion : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    var dataList = ArrayList<HashMap<String, String>>()

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val intento1 = Intent(this, ActivityHome2::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ecografia -> {
                val intento1 = Intent(this, EcografiaActivity::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_vacuna -> {
                val intento1 = Intent(this, VacunaActivity::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_examenes -> {
                val intento1 = Intent(this, ActivityExamenes::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_perfil -> {
                val intento1 = Intent(this, ActivityPerfil::class.java)
                startActivity(intento1)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacion)

        usersDBHelper = UsersDBHelper(this)

        val navView: BottomNavigationView = findViewById(R.id.nav_view_botton)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        var edadGestacional = showAllUsers()
        var notificacion = usersDBHelper.selNotificacion(edadGestacional)
        var tipoExamen: String
        var iconoTipoExamen: Int

        var vrNombreExamen = arrayOf<String>()
        var vrTipoExamen = arrayOf<String>()
        var vrIconoTipoExamen = arrayOf<Int>()

        notificacion.forEach{
            vrNombreExamen += arrayOf(it.nombreExamen)
            if(it.tipoExamen == 1){
                tipoExamen = "Ecografias"
                iconoTipoExamen = R.drawable.img_ecografia_notificacion
            }
            else if(it.tipoExamen == 2){
                tipoExamen = "Paraclinicos"
                iconoTipoExamen = R.drawable.img_paraclinicos_notificacion
            }
            else{
                tipoExamen = "vacunas"
                iconoTipoExamen = R.drawable.img_vacunas_notificacion
            }
            vrTipoExamen += arrayOf(tipoExamen + " - Trimestre " + it.numTrimestre)
            vrIconoTipoExamen += arrayOf(iconoTipoExamen)
        }

        // se agrega el listview
        val myListAdapter = ListNotificacion(this,vrNombreExamen,vrTipoExamen,vrIconoTipoExamen)
        listView.adapter = myListAdapter

        listView.setOnItemClickListener(){ adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            //Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }

        // agrega el icono de ir atras del menu actionBar
        var actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayShowHomeEnabled(true)

    }
    var textNotification_badge: TextView? = null
    // se asocia el menu ActionBar a la activity actual
    public override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menuopciones, menu)

        val menuItem = menu.findItem(R.id.menunotificacion)

        val actionView = MenuItemCompat.getActionView(menuItem)
        textNotification_badge = actionView.findViewById(R.id.notification_badge) as TextView

        // se quita el badge de notificacion cuando no exista nada que notificar
        textNotification_badge!!.setText("")
        //Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()
        textNotification_badge!!.setBackgroundResource(android.R.color.transparent)

        // se muestra el badge de notificacion cuando exista que notificar
        //textNotification_badge!!.setText("58");
        //textNotification_badge!!.setBackgroundResource(R.drawable.badge_background);
        //Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()

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
        if (id == R.id.btnMenuCerrarSesion) {
            val intento1 = Intent(this, ActivityLogin::class.java)
            startActivity(intento1)
            return true
        }
        if (id == R.id.menuSignoAlarma) {
            val intento1 = Intent(this, ActivityNotificacion::class.java)
            startActivity(intento1)
            return true
        }
        return super.onOptionsItemSelected(item)
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

    // metodo que realiza el ir atras del actionBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
