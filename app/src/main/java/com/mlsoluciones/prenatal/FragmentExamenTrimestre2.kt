package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_examen_trimestre1.view.*

class FragmentExamenTrimestre2 : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_examen_trimestre2, container, false)

        view.btn1.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamVihT2::class.java)
            startActivity(intent)
        }

        view.btn2.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamToxoplasmaT2::class.java)
            startActivity(intent)
        }

        view.btn3.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamHepatitisT2::class.java)
            startActivity(intent)
        }

        view.btn4.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamCitomegalovirusT2::class.java)
            startActivity(intent)
        }

        view.btn5.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamVdrlT2::class.java)
            startActivity(intent)
        }

        view.btn6.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamCurvaToleranciaT2::class.java)
            startActivity(intent)
        }

        view.btn7.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamCoombsT2::class.java)
            startActivity(intent)
        }

        view.btn8.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamUrocultivoT2::class.java)
            startActivity(intent)
        }

        view.btn9.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamUroanalisisT2::class.java)
            startActivity(intent)
        }

        view.btn10.setOnClickListener {
            val intent = Intent(activity, ActivitySubirExamHemogramaT2::class.java)
            startActivity(intent)
        }

        return view
    }
}
