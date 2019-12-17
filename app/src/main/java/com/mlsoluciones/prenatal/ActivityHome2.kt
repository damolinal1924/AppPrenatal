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
