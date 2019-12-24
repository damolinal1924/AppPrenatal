package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_vacuna_trimestre1.view.*

class FragmentVacunaTrimestre1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_vacuna_trimestre1, container, false)

        view.btn1.setOnClickListener {
            val intent = Intent(activity, ActivitySubirVacInfluenza::class.java)
            startActivity(intent)
        }

        view.btn2.setOnClickListener {
            val intent = Intent(activity, ActivitySubirVacTetano::class.java)
            startActivity(intent)
        }

        return view

    }
}