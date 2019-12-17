package com.mlsoluciones.prenatal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistrate.setOnClickListener {
            //val intento1 = Intent(this, RegistrateActivity::class.java)
            //startActivity(intento1)
            val intento1 = Intent(this, CalculoEdadGestacionalActivity::class.java)
            startActivity(intento1)
        }

        btnInicioSesion.setOnClickListener {
            val intento1 = Intent(this, ActivityLogin::class.java)
            startActivity(intento1)
        }
    }


}
