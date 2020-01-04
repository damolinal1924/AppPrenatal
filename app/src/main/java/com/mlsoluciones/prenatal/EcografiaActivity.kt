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
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.mlsoluciones.prenatal.model.UsersDBHelper
import com.mlsoluciones.prenatal.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_ecografia.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class EcografiaActivity : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

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

    var textNotification_badge: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecografia)

        usersDBHelper = UsersDBHelper(this)

        // incrusta el menu de navegacion de abajo
        val navView: BottomNavigationView = findViewById(R.id.nav_view_ecografia)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // codigo para los tab
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        //tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_car))
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_semana1)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_semana2)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_semana3)

        // Se selecciona opcion por defecto del BottomNavigationView
        nav_view_ecografia.getMenu().getItem(1).setChecked(true)

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

        var edadGestacional = showAllUsers()
        var numNotificacion = selNotificacion(edadGestacional)

        if(numNotificacion == 0){
            // se quita el badge de notificacion cuando no exista nada que notificar
            textNotification_badge!!.setText("")
            textNotification_badge!!.setBackgroundResource(android.R.color.transparent)
            //Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()
        }else{
            // se muestra el badge de notificacion cuando exista que notificar
            textNotification_badge!!.setText(numNotificacion.toString())
            textNotification_badge!!.setBackgroundResource(R.drawable.badge_background)
            //Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()
        }

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

        //var mje: String = ""
        var notificacion = usersDBHelper.selNotificacion(edadGestacional)

        //Toast.makeText(this, "Total de registros: " + notificacion.size, Toast.LENGTH_LONG).show()

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

    // metodo que realiza el ir atras del actionBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
