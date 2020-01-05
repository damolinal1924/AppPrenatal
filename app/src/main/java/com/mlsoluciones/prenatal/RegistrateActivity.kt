package com.mlsoluciones.prenatal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mlsoluciones.prenatal.model.UserModel
import com.mlsoluciones.prenatal.model.UsersDBHelper
import kotlinx.android.synthetic.main.activity_registrate.*
import android.util.Patterns

class RegistrateActivity : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrate)

        usersDBHelper = UsersDBHelper(this)

    }

    private fun validarEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun validarDatos(v:View){
        if(txtnombre.text.toString().equals("")){
            Toast.makeText(this, "Por favor ingrese nombre", Toast.LENGTH_LONG).show()
        }
        else if(txtemail.text.toString().equals("")){
            Toast.makeText(this, "Por favor ingrese correo eléctronico", Toast.LENGTH_LONG).show()
        }
        else if(!validarEmail(txtemail.text.toString())){
            Toast.makeText(this, "Email no válido", Toast.LENGTH_LONG).show()
        }
        else if(txttelefono.text.toString().equals("")){
            Toast.makeText(this, "Por favor ingrese teléfono", Toast.LENGTH_LONG).show()
        }
        else if(!rbMadreRHsi.isChecked && !rbMadreRHno.isChecked){
            Toast.makeText(this, "Por favor seleccione madre RH-", Toast.LENGTH_LONG).show()
        }
        else if(!rbPadreRHsi.isChecked && !rbPadreRHno.isChecked){
            Toast.makeText(this, "Por favor seleccione padre RH+", Toast.LENGTH_LONG).show()
        }
        else if(!rbHijoAntRHsi.isChecked && !rbHijoAntRHno.isChecked){
            Toast.makeText(this, "Por favor seleccione hijo anterior RH+", Toast.LENGTH_LONG).show()
        }
        else if(txtcontrasena.text.toString().equals("")){
            Toast.makeText(this, "Por favor ingrese contraseña", Toast.LENGTH_LONG).show()
        }
        else if(txtcontrasena.text.toString().length < 5){
            Toast.makeText(this, "La contraseña debe se mayor a 5 caracteres", Toast.LENGTH_LONG).show()
        }
        else if(!txtcontrasena.text.toString().equals(txtRepContrasena.text.toString())){
            Toast.makeText(this, "La contraseña no coinciden", Toast.LENGTH_LONG).show()
        }
        else{
            showAllUsers()
        }
    }

    fun addUser(){

        var nombre = this.txtnombre.text.toString()
        var email = this.txtemail.text.toString()
        var telefono = this.txttelefono.text.toString()
        var contrasena= this.txtcontrasena.text.toString()
        var edadGestacional = "0".toFloat()
        var semanaGestacionEcografia = "0".toFloat()
        var fechaProbableParto = "07/08/1924"
        var madreRhNegativo = 0
        var padreRhPositivo = 0
        var hijoAnteriorRhPositivo = 0

        if(this.rbMadreRHsi.isChecked){
            madreRhNegativo = 1
        }
        if(this.rbPadreRHsi.isChecked){
            padreRhPositivo = 1
        }
        if(this.rbHijoAntRHsi.isChecked){
            hijoAnteriorRhPositivo = 1
        }
        var fechaUltimaMenstruacion = "07/08/1924"

        var result = usersDBHelper.insertUser(UserModel(iduser = 0, nombre = nombre, email = email, telefono = telefono,
            contrasena = contrasena, edadGestacional = edadGestacional, semanaGestacionEcografia = semanaGestacionEcografia,
            fechaProbableParto = fechaProbableParto, madreRhNegativo = madreRhNegativo, padreRhPositivo = padreRhPositivo,
            hijoAnteriorRhPositivo = hijoAnteriorRhPositivo, fechaUltimaMenstruacion = fechaUltimaMenstruacion))

        //clear all edittext
        this.txtnombre.setText("")
        this.txtemail.setText("")
        this.txttelefono.setText("")
        this.txtcontrasena.setText("")
        this.txtRepContrasena.setText("")
        this.rbMadreRHsi.isChecked = false
        this.rbPadreRHsi.isChecked = false
        this.rbHijoAntRHsi.isChecked = false
        this.rbMadreRHno.isChecked = false
        this.rbPadreRHno.isChecked = false
        this.rbHijoAntRHno.isChecked = false

        if(result != -1L){
            Toast.makeText(this, "Datos guardados exitosamente!", Toast.LENGTH_LONG).show()
            activityCalcularEdadGestacional()
        }else{
            Toast.makeText(this, "Ocurrio un error al guardar", Toast.LENGTH_LONG).show()
        }
    }

    fun showAllUsers(){
        var users = usersDBHelper.readAllUsers()
        var mje: String = ""
        users.forEach {

            mje = mje + it.iduser.toString() + " - " + it.nombre + " - " + it.fechaUltimaMenstruacion +
                    " *** " + it.fechaProbableParto + " - " +
                    it.edadGestacional.toString() + " - madre: " + it.madreRhNegativo + " - padre: " +
                    it.padreRhPositivo + " - hijo: " + it.hijoAnteriorRhPositivo

        }
        //Toast.makeText(this, mje, Toast.LENGTH_LONG).show()
        if(!mje.equals("")){
            Toast.makeText(this, "Usuario ya se encuentra registrado", Toast.LENGTH_LONG).show()
        }
        else{
            addUser()
        }
    }

    fun activityCalcularEdadGestacional(){
        val intento1 = Intent(this, CalculoEdadGestacionalActivity::class.java)
        startActivity(intento1)
    }
}
