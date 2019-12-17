package com.mlsoluciones.prenatal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mlsoluciones.prenatal.model.UsersDBHelper
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usersDBHelper = UsersDBHelper(this)

    }

    fun validarDatos(v: View){

        activityHome()
        //if(txtemail.text.toString().equals("")){
        //    Toast.makeText(this, "Por favor ingrese correo eléctronico", Toast.LENGTH_LONG).show()
        //}
        //else if(txtcontrasena.text.toString().equals("")){
        //    Toast.makeText(this, "Por favor ingrese la contraseña", Toast.LENGTH_LONG).show()
        //}
        //else{
        //    loginUser()
        //}
    }

    fun loginUser(){
        val email = txtemail.text.toString()
        val pass = txtcontrasena.text.toString()
        var user = usersDBHelper.loginUser(email, pass)

        Toast.makeText(this, "User::: " + user, Toast.LENGTH_LONG).show()
        if(user > 0){
            activityHome()
        }
        else{
            Toast.makeText(this, "Correo eléctronico o contraseña incorrectas", Toast.LENGTH_LONG).show()
        }
    }

    fun activityHome(){
        val intento1 = Intent(this, ActivityHome2::class.java)
        startActivity(intento1)
    }
}
