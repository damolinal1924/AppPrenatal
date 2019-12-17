package com.mlsoluciones.prenatal

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*
import java.text.SimpleDateFormat
import kotlinx.android.synthetic.main.activity_calculo_edad_gestacional.*
import java.util.concurrent.TimeUnit
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.math.BigDecimal
import java.math.RoundingMode


class CalculoEdadGestacionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo_edad_gestacional)

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
            val intento1 = Intent(this, ActivityLogin::class.java)
            startActivity(intento1)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun calcularEdadGestacional(){
        var totalSemanas: Float
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        // se calcula fecha de las 24 primeras semanas mas 2 que se regalan es decir 26
        val fechaMestruacion = dtfechamestruacion.text.toString().split("/")
        val mes = fechaMestruacion[1].toInt() + 6
        val fechaSemenas = fechaMestruacion[0] +"/"+ mes +"/"+ fechaMestruacion[2]
        val fechaSemenas26Format = dateFormat.format(dateFormat.parse(fechaSemenas))
        totalSemanas = 26F

        // se calcula una semana mas
        var fechaSemanasMasUna = fechaSemenas26Format
        val c = Calendar.getInstance()
        c.time = dateFormat.parse(fechaSemanasMasUna)
        c.add(Calendar.DATE, 7)  // se le agrega 1 semana
        fechaSemanasMasUna = dateFormat.format(c.time)
        totalSemanas = totalSemanas + 1F

        // se calcula la diferencia en dias que hay entre la fecha actual y la fecha de la semana 24
        val millisAcompletarHastaFechaActual = Calendar.getInstance().timeInMillis - c.getTimeInMillis()
        val diasAcompletarHastaFechaActual = TimeUnit.MILLISECONDS.toDays(millisAcompletarHastaFechaActual)

        var semanas: Double
        // se divide la diferencia de dia entre 1 semana (7 dias)
        // para saber a cuantas semanas equivalen los dias de diferencia
        semanas = (diasAcompletarHastaFechaActual / "7".toDouble())

        // se limita a un solo decimal, sin redondear
        var diferenciaDias = BigDecimal(semanas)
        diferenciaDias = diferenciaDias.setScale(1, RoundingMode.DOWN)

        Toast.makeText(this, "$semanas diferenciaDias: $diferenciaDias diasAcompletarHastaFechaActual: $diasAcompletarHastaFechaActual", Toast.LENGTH_LONG).show()
        if(diasAcompletarHastaFechaActual < 0) {
            totalSemanas = (totalSemanas + (diferenciaDias.toFloat()))
        }
        else if(diasAcompletarHastaFechaActual >= 0 && diasAcompletarHastaFechaActual < 7){
            val dias = "0."+diasAcompletarHastaFechaActual
            totalSemanas = totalSemanas + dias.toFloat()
        }
        else{
            totalSemanas = totalSemanas + diferenciaDias.toFloat()
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
}
