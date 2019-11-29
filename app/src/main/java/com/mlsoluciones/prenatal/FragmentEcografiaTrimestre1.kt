package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_ecografia_trimestre1.view.*

class FragmentEcografiaTrimestre1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_ecografia_trimestre1, container, false)

        view.btnEcooTransvaginal.setOnClickListener {
            val intent = Intent(activity, ActivitySubirEccobtreticaTransvaginal::class.java)
            startActivity(intent)
        }

        view.btnEcoTranslucenciaNucal.setOnClickListener {
            val intent = Intent(activity, ActivitySubirEcografiaTranslucenciaNucal::class.java)
            startActivity(intent)
        }

        return view

    }
}