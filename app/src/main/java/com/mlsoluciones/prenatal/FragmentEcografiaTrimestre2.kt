package com.mlsoluciones.prenatal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_ecografia_trimestre2.view.*

class FragmentEcografiaTrimestre2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_ecografia_trimestre2, container, false)

        view.btnEcoObtetricaTrans.setOnClickListener {
            val intent = Intent(activity, ActivitySubirEcoObtreticaTransabdominal::class.java)
            startActivity(intent)
        }

        return view

    }
}