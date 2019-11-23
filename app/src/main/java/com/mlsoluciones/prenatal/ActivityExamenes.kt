package com.mlsoluciones.prenatal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.mlsoluciones.prenatal.ui.main.SectionsPagerAdapter
import com.mlsoluciones.prenatal.ui.main.SectionsTabExamenes
import kotlinx.android.synthetic.main.activity_ecografia.*
import kotlinx.android.synthetic.main.activity_examenes.*

class ActivityExamenes : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examenes)

        // codigo para los tab
        val sectionsPagerAdapter = SectionsTabExamenes(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        //tabs.addTab(tabs.newTab().setIcon(R.drawable.ic_car))
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_semana1)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_semana2)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_semana3)

        // Se selecciona opcion por defecto del BottomNavigationView
        nav_view_examenes.getMenu().getItem(3).setChecked(true)
    }
}
