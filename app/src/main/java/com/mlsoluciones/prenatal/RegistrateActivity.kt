package com.mlsoluciones.prenatal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class RegistrateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrate)

    }

    fun seleccionarMadreRH(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbMadreRHsi ->
                    if (checked) {
                        Toast.makeText(this@RegistrateActivity, "Se ha presionado que SI", Toast.LENGTH_LONG).show()
                    }
                R.id.rbMadreRHno ->
                    if (checked) {
                        Toast.makeText(this@RegistrateActivity, "Se ha presionado que No", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}
