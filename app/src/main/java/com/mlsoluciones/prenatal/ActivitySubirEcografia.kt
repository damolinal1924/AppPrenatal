package com.mlsoluciones.prenatal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_ecografia.*
import kotlinx.android.synthetic.main.activity_subir_ecografia.*

class ActivitySubirEcografia : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_ecografia)

        val navView: BottomNavigationView = findViewById(R.id.nav_view_subir_ecografia)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Se selecciona opcion por defecto del BottomNavigationView
        navView.getMenu().getItem(1).setChecked(true)
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
}
