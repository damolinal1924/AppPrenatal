package com.mlsoluciones.prenatal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.activity_perfil.*

class ActivityPerfil : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

            }
            R.id.navigation_ecografia -> {
                val intento1 = Intent(this, EcografiaActivity::class.java)
                startActivity(intento1)
            }
            R.id.navigation_vacuna -> {

            }
            R.id.navigation_examenes -> {

            }
            R.id.navigation_perfil -> {
                val intento1 = Intent(this, ActivityPerfil::class.java)
                startActivity(intento1)
            }
        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // incrusta el menu de navegacion de abajo
        val navView: BottomNavigationView = findViewById(R.id.nav_view_perfil)
        // Se selecciona opcion por defecto del BottomNavigationView
        nav_view_perfil.getMenu().getItem(4).setChecked(true)
    }
}
