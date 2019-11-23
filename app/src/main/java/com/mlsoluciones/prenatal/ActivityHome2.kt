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
import kotlinx.android.synthetic.main.activity_home2.*

class ActivityHome2 : AppCompatActivity() {

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
    var mCartItemCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
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
        actionbar!!.title = "Prenatal"

        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)

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

        // se quita el badge de notificacion cuando no exista nada que notificar
        textNotification_badge!!.setText("");
        Toast.makeText(this, """notification_badge: ${textNotification_badge!!.text}""", Toast.LENGTH_LONG).show()
        textNotification_badge!!.setBackgroundResource(android.R.color.transparent);

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
        if (id == R.id.menunotificacion) {
            Toast.makeText(this, "Se presionó el ícono de notificación 1", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.notification_badge) {
            Toast.makeText(this, "Se presionó el ícono de notificación 2", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.opcion1) {
            Toast.makeText(this, "Se presionó la opción 1 del menú", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.opcion2) {
            Toast.makeText(this, "Se presionó la opción 2 del menú", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.opcion3) {
            Toast.makeText(this, "Se presionó la opción 3 del menú", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
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
