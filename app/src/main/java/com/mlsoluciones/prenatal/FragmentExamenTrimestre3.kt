package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_examen_trimestre1.view.*

class FragmentExamenTrimestre3 : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_examen_trimestre3, container, false)

        view.btn1.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamHemogramaT3::class.java)
            startActivity(intent)
        }

        view.btn2.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamVihT3::class.java)
            startActivity(intent)
        }

        view.btn3.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamToxoplasmaT3::class.java)
            startActivity(intent)
        }

        view.btn4.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamHepatitisT3::class.java)
            startActivity(intent)
        }

        view.btn5.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamCitomegalovirusT3::class.java)
            startActivity(intent)
        }

        view.btn6.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamVdrlT3::class.java)
            startActivity(intent)
        }

        view.btn7.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamCultivoRectoVaginalT3::class.java)
            startActivity(intent)
        }

        view.btn8.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamUrocultivoT3::class.java)
            startActivity(intent)
        }

        view.btn9.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamUroanalisisT3::class.java)
            startActivity(intent)
        }

        return view
    }
}
