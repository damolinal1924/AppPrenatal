package com.mlsoluciones.prenatal

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.mlsoluciones.prenatal.model.UsersDBHelper
import java.util.*
import java.text.SimpleDateFormat
import kotlinx.android.synthetic.main.activity_calculo_edad_gestacional.*
import kotlinx.android.synthetic.main.activity_subir_eccobtretica_transvaginal.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

class CalculoEdadGestacionalActivity : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo_edad_gestacional)

        usersDBHelper = UsersDBHelper(this)

        // logica para cargar la fecha
        val mPickTimeBtn = findViewById<Button>(R.id.btnfechamestruacion)
        val textView    = findViewById<TextView>(R.id.dtfechamestruacion)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                val mes = mMonth + 1
                //Display Selected date in TextView
                textView.setText(""+mDay + "/" + mes + "/" + mYear)
            }, year, month, day)
            dpd.show()
        }
    }

    // se asocia el menu ActionBar a la activity actual
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.boton_menu, menu)

        return true
    }

    // se muestra un mensaje dependiendo la opcion o el icono pulsado del menu ActionBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if (id == R.id.btnCalcular) {
            if(!dtfechamestruacion.text.toString().equals("")) {
                calcularEdadGestacional()
            }
            else{
                Toast.makeText(this, "Seleccione fecha última menstruación", Toast.LENGTH_LONG).show()
            }
            return true
        }
        if (id == R.id.btnGuardar) {
            if(!dtfechamestruacion.text.toString().equals("")) {
                updateTusuario()
            }
            else{
                Toast.makeText(this, "Calcule la edad gestacional antes de guardar", Toast.LENGTH_LONG).show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun calcularEdadGestacional(){
        var totalSemanas: Float = 0F
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val fecha = Date()
        val fechaActual = Calendar.getInstance()
        fechaActual.time = fecha

        var fechaMestruacionC = Calendar.getInstance()
        fechaMestruacionC.time = dateFormat.parse(dtfechamestruacion.text.toString())

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

        this.txtSemanaGestacion.text = String.format("%.1f Semanas de gestación", totalSemanas)

        // Se calcula la fecha probable del parto
        var fechaUltimaRegla = dtfechamestruacion.text.toString()
        val cfecha = Calendar.getInstance()
        cfecha.time = dateFormat.parse(fechaUltimaRegla)
        cfecha.add(Calendar.DATE, 7)  // se le agrega 7 dias
        cfecha.add(Calendar.YEAR, 1) // se le agrega un anno
        cfecha.add(Calendar.MONTH, -3) // se le restan 3 meses
        fechaUltimaRegla = dateFormat.format(cfecha.time)

        this.txtFechaProbableParto.text = fechaUltimaRegla

    }

    fun updateTusuario(){
        var edadGestacional = txtSemanaGestacion.text.toString().replace(" Semanas de gestación", "").toFloat()
        var fechaProbableParto = txtFechaProbableParto.text.toString()
        var fechaUltimaMenstruacion = dtfechamestruacion.text.toString()

        var result = usersDBHelper.updateTusuario(1, edadGestacional, fechaProbableParto, fechaUltimaMenstruacion)

        //Toast.makeText(this, "Resultado " + result, Toast.LENGTH_LONG).show()

        if(result){
            Toast.makeText(this, "Edad gestacional guardada exitosamente!", Toast.LENGTH_LONG).show()
            val intento1 = Intent(this, ActivityLogin::class.java)
            startActivity(intento1)
        }
        else{
            Toast.makeText(this, "Ocurrio un error al momento de guardar!", Toast.LENGTH_LONG).show()
        }
    }
}
