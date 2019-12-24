package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.annotation.DrawableRes
import com.mlsoluciones.prenatal.model.UsersDBHelper
import kotlinx.android.synthetic.main.activity_home2.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ActivityHome2 : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            Toast.makeText(this@ActivityHome2, "Pos ${position}", Toast.LENGTH_LONG).show()
            item_list.smoothScrollToPosition(position)
            when(position){
                0 -> {
                    loadSemana1(FragmentSemana1())
                }
                1 -> {
                    loadSemana2(FragmentSemana2())
                }
                2 -> {
                    loadSemana3(FragmentSemana3())
                }
                3 -> {
                    loadSemana4(FragmentSemana4())
                }
                4 -> {
                    loadSemana5(FragmentSemana5())
                }
                5 -> {
                    loadSemana6(FragmentSemana6())
                }
                6 -> {
                    loadSemana7(FragmentSemana7())
                }
                7 -> {
                    loadSemana8(FragmentSemana8())
                }
                8 -> {
                    loadSemana9(FragmentSemana9())
                }
                9 -> {
                    loadSemana10(FragmentSemana10())
                }
                10 -> {
                    loadSemana11(FragmentSemana11())
                }
                11 -> {
                    loadSemana12(FragmentSemana12())
                }
                12 -> {
                    loadSemana13(FragmentSemana13())
                }
                13 -> {
                    loadSemana14(FragmentSemana14())
                }
                14 -> {
                    loadSemana15(FragmentSemana15())
                }
                15 -> {
                    loadSemana16(FragmentSemana16())
                }
                16 -> {
                    loadSemana17(FragmentSemana17())
                }
                17 -> {
                    loadSemana18(FragmentSemana18())
                }
                18 -> {
                    loadSemana19(FragmentSemana19())
                }
                19 -> {
                    loadSemana20(FragmentSemana20())
                }
                20 -> {
                    loadSemana21(FragmentSemana21())
                }
                21 -> {
                    loadSemana22(FragmentSemana22())
                }
                22 -> {
                    loadSemana23(FragmentSemana23())
                }
                23 -> {
                    loadSemana24(FragmentSemana24())
                }
                24 -> {
                    loadSemana25(FragmentSemana25())
                }
                25 -> {
                    loadSemana26(FragmentSemana26())
                }
                26 -> {
                    loadSemana27(FragmentSemana27())
                }
                27 -> {
                    loadSemana28(FragmentSemana28())
                }
                28 -> {
                    loadSemana29(FragmentSemana29())
                }
                29 -> {
                    loadSemana30(FragmentSemana30())
                }
                30 -> {
                    loadSemana31(FragmentSemana31())
                }
                31 -> {
                    loadSemana32(FragmentSemana32())
                }
                32 -> {
                    loadSemana33(FragmentSemana33())
                }
                33 -> {
                    loadSemana34(FragmentSemana34())
                }
                34 -> {
                    loadSemana35(FragmentSemana35())
                }
                35 -> {
                    loadSemana36(FragmentSemana36())
                }
                36 -> {
                    loadSemana37(FragmentSemana37())
                }
                37 -> {
                    loadSemana38(FragmentSemana38())
                }
                38 -> {
                    loadSemana39(FragmentSemana39())
                }
                39 -> {
                    loadSemana40(FragmentSemana40())
                }
            }
        } }
    private val semanasEmbarazoItems = listOf(
        Item("Semana de embarazo", R.drawable.ic_semana1),
        Item("Semanas de embarazo", R.drawable.ic_semana2),
        Item("Semanas de embarazo", R.drawable.ic_semana3),
        Item("Semanas de embarazo", R.drawable.ic_semana4),
        Item("Semanas de embarazo", R.drawable.ic_semana5),
        Item("Semanas de embarazo", R.drawable.ic_semana6),
        Item("Semanas de embarazo", R.drawable.ic_semana7),
        Item("Semanas de embarazo", R.drawable.ic_semana8),
        Item("Semanas de embarazo", R.drawable.ic_semana9),
        Item("Semanas de embarazo", R.drawable.ic_semana10),
        Item("Semanas de embarazo", R.drawable.ic_semana11),
        Item("Semanas de embarazo", R.drawable.ic_semana12),
        Item("Semanas de embarazo", R.drawable.ic_semana13),
        Item("Semanas de embarazo", R.drawable.ic_semana14),
        Item("Semanas de embarazo", R.drawable.ic_semana15),
        Item("Semanas de embarazo", R.drawable.ic_semana16),
        Item("Semanas de embarazo", R.drawable.ic_semana17),
        Item("Semanas de embarazo", R.drawable.ic_semana18),
        Item("Semanas de embarazo", R.drawable.ic_semana19),
        Item("Semanas de embarazo", R.drawable.ic_semana20),
        Item("Semanas de embarazo", R.drawable.ic_semana21),
        Item("Semanas de embarazo", R.drawable.ic_semana22),
        Item("Semanas de embarazo", R.drawable.ic_semana23),
        Item("Semanas de embarazo", R.drawable.ic_semana24),
        Item("Semanas de embarazo", R.drawable.ic_semana25),
        Item("Semanas de embarazo", R.drawable.ic_semana26),
        Item("Semanas de embarazo", R.drawable.ic_semana27),
        Item("Semanas de embarazo", R.drawable.ic_semana28),
        Item("Semanas de embarazo", R.drawable.ic_semana29),
        Item("Semanas de embarazo", R.drawable.ic_semana30),
        Item("Semanas de embarazo", R.drawable.ic_semana31),
        Item("Semanas de embarazo", R.drawable.ic_semana32),
        Item("Semanas de embarazo", R.drawable.ic_semana33),
        Item("Semanas de embarazo", R.drawable.ic_semana34),
        Item("Semanas de embarazo", R.drawable.ic_semana35),
        Item("Semanas de embarazo", R.drawable.ic_semana36),
        Item("Semanas de embarazo", R.drawable.ic_semana37),
        Item("Semanas de embarazo", R.drawable.ic_semana38),
        Item("Semanas de embarazo", R.drawable.ic_semana39),
        Item("Semanas de embarazo", R.drawable.ic_semana40)
    )

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText("")
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        usersDBHelper = UsersDBHelper(this)

        val navView: BottomNavigationView = findViewById(R.id.nav_view_home)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // codigo agregar la lista de las opciones circulares de semanas de gestacion
        item_list.initialize(itemAdapter)
        item_list.setViewsToChangeColor(listOf(R.id.list_item_background, R.id.list_item_text))
        itemAdapter.setItems(getLargeListOfItems())

        loadSemana1(FragmentSemana1())

        // Se selecciona opcion por defecto del BottomNavigationView
        nav_view_home.getMenu().getItem(0).setChecked(true)

        // agrega el icono de ir atras del menu actionBar
         var actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayShowHomeEnabled(true)

    }

    // metodo que agrega cada uno de los numeros de semanas a la lista
    private fun getLargeListOfItems(): List<Item> {
        val items = mutableListOf<Item>()
        semanasEmbarazoItems.forEach {
            items.add(it)
        }
        return items
    }
    // se asocia el menu ActionBar a la activity actual
    public override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
            Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()
        }else{
            // se muestra el badge de notificacion cuando exista que notificar
            textNotification_badge!!.setText(numNotificacion.toString())
            textNotification_badge!!.setBackgroundResource(R.drawable.badge_background)
            Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()
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

        Toast.makeText(this, "Total de registros: " + notificacion.size, Toast.LENGTH_LONG).show()

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

    private fun loadSemana1(frag: FragmentSemana1) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana2(frag: FragmentSemana2) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana3(frag: FragmentSemana3) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana4(frag: FragmentSemana4) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana5(frag: FragmentSemana5) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana6(frag: FragmentSemana6) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana7(frag: FragmentSemana7) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana8(frag: FragmentSemana8) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana9(frag: FragmentSemana9) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana10(frag: FragmentSemana10) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana11(frag: FragmentSemana11) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana12(frag: FragmentSemana12) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana13(frag: FragmentSemana13) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana14(frag: FragmentSemana14) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana15(frag: FragmentSemana15) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana16(frag: FragmentSemana16) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana17(frag: FragmentSemana17) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana18(frag: FragmentSemana18) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana19(frag: FragmentSemana19) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana20(frag: FragmentSemana20) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana21(frag: FragmentSemana21) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana22(frag: FragmentSemana22) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    private fun loadSemana23(frag: FragmentSemana23) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana24(frag: FragmentSemana24) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana25(frag: FragmentSemana25) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana26(frag: FragmentSemana26) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana27(frag: FragmentSemana27) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana28(frag: FragmentSemana28) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana29(frag: FragmentSemana29) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana30(frag: FragmentSemana30) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana31(frag: FragmentSemana31) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana32(frag: FragmentSemana32) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana33(frag: FragmentSemana33) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana34(frag: FragmentSemana34) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana35(frag: FragmentSemana35) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana36(frag: FragmentSemana36) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana37(frag: FragmentSemana37) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana38(frag: FragmentSemana38) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana39(frag: FragmentSemana39) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }
    private fun loadSemana40(frag: FragmentSemana40) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    // metodo que realiza el ir atras del actionBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

data class Item(
    val title: String,
    @DrawableRes val icon: Int
)
