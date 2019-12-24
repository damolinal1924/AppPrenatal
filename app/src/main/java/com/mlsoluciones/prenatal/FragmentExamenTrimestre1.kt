package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_examen_trimestre1.view.*

class FragmentExamenTrimestre1 : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_examen_trimestre1, container, false)

        view.btn1.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamHemograma::class.java)
            startActivity(intent)
        }

        view.btn2.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamHemoclasificacion::class.java)
            startActivity(intent)
        }

        view.btn3.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamCitologia::class.java)
            startActivity(intent)
        }

        view.btn4.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamFrotisVaginal::class.java)
            startActivity(intent)
        }
        // De aqui en adelante hay que agregarlo en el manifest
        view.btn5.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamUorianalisis::class.java)
            startActivity(intent)
        }

        view.btn6.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamUrocultivo::class.java)
            startActivity(intent)
        }

        view.btn7.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamVih::class.java)
            startActivity(intent)
        }

        return view
    }
}
