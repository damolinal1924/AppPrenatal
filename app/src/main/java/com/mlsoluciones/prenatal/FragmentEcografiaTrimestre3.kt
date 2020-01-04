package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_ecografia_trimestre3.view.*

class FragmentEcografiaTrimestre3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_ecografia_trimestre3, container, false)

        view.btnEcooTransabdominal.setOnClickListener {
            val intent = Intent(activity, ActivitySubirEcoObtetricaTransabdominalTercerTrimestre::class.java)
            startActivity(intent)
        }

        view.btnPerfilBiofisico.setOnClickListener {
            val intent = Intent(activity, ActivitySubirEcoPerfilBiofisico::class.java)
            startActivity(intent)
        }

        view.btnDooplerEvaluacionCirculacion.setOnClickListener {
            val intent = Intent(activity, ActivitySubirEcoDopplerEvaluacionCirculacion::class.java)
            startActivity(intent)
        }

        return view

    }
}