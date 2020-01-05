package com.mlsoluciones.prenatal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mlsoluciones.prenatal.model.UsersDBHelper
import kotlinx.android.synthetic.main.activity_recuperar_clave.*

class ActivityRecuperarClave : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_clave)

        usersDBHelper = UsersDBHelper(this)

        // agrega el icono de ir atras del menu actionBar
        var actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayShowHomeEnabled(true)

    }

    fun validarDatos(v: View){
        //activityHome()
        if(txtClave.text.toString().equals("")){
            Toast.makeText(this, "Por favor ingrese la contraseña", Toast.LENGTH_LONG).show()
        }
        else if(txtClave.text.toString().length < 5){
            Toast.makeText(this, "La contraseña debe se mayor a 5 caracteres", Toast.LENGTH_LONG).show()
        }
        else if(!txtClave.text.toString().equals(txtRepetirClave.text.toString())){
            Toast.makeText(this, "La contraseña no coinciden", Toast.LENGTH_LONG).show()
        }
        else{
            updateClaveUsuario()
        }
    }

    fun updateClaveUsuario(){
        var clave = txtClave.text.toString()
        var result = usersDBHelper.updateClaveUsuario(1, clave)

        if(result){
            Toast.makeText(this, "Contraseña actualizada exitosamente!", Toast.LENGTH_LONG).show()
            val intento1 = Intent(this, ActivityLogin::class.java)
            startActivity(intento1)
        }
        else{
            Toast.makeText(this, "Ocurrio un error al momento de guardar!", Toast.LENGTH_LONG).show()
        }
    }

    // metodo que realiza el ir atras del actionBar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
