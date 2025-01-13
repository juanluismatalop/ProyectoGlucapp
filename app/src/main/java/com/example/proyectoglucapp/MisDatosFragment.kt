package com.example.proyectoglucapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MisDatosFragment : Fragment() {

    private lateinit var factorDeSensibilidad: EditText
    private lateinit var ratioMannana: EditText
    private lateinit var ratioMedioDia: EditText
    private lateinit var ratioTarde: EditText
    private lateinit var ratioNoche: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mis_datos, container, false)

        factorDeSensibilidad = view.findViewById(R.id.factorDeSensibilidad)
        ratioMannana = view.findViewById(R.id.ratioMannana)
        ratioMedioDia = view.findViewById(R.id.ratioMedioDia)
        ratioTarde = view.findViewById(R.id.ratioTarde)
        ratioNoche = view.findViewById(R.id.ratioNoche)
        saveButton = view.findViewById(R.id.button)

        saveButton.setOnClickListener {
            guardarDatosYEnviar()
        }

        return view
    }

    private fun guardarDatosYEnviar() {
        val sensibilidad = factorDeSensibilidad.text.toString()
        val manana = ratioMannana.text.toString()
        val medioDia = ratioMedioDia.text.toString()
        val tarde = ratioTarde.text.toString()
        val noche = ratioNoche.text.toString()

        val bundle = Bundle().apply {
            putString("sensibilidad", sensibilidad)
            putString("manana", manana)
            putString("medioDia", medioDia)
            putString("tarde", tarde)
            putString("noche", noche)
        }
    }
}
