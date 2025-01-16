package com.example.proyectoglucapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MisDatosFragment : Fragment() {

    private lateinit var factorDeSensibilidad: EditText
    private lateinit var ratioManana: EditText
    private lateinit var ratioMedioDia: EditText
    private lateinit var ratioTarde: EditText
    private lateinit var ratioNoche: EditText
    private lateinit var saveButton: Button

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mis_datos, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        factorDeSensibilidad = view.findViewById(R.id.factorDeSensibilidad)
        ratioManana = view.findViewById(R.id.ratioMannana)
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
        val sensibilidadStr = factorDeSensibilidad.text.toString()
        val mananaStr = ratioManana.text.toString()
        val medioDiaStr = ratioMedioDia.text.toString()
        val tardeStr = ratioTarde.text.toString()
        val nocheStr = ratioNoche.text.toString()

        if (sensibilidadStr.isBlank() || mananaStr.isBlank() ||
            medioDiaStr.isBlank() || tardeStr.isBlank() || nocheStr.isBlank()) {
            Toast.makeText(requireContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val sensibilidad = sensibilidadStr.toDouble()
            val manana = mananaStr.toDouble()
            val medioDia = medioDiaStr.toDouble()
            val tarde = tardeStr.toDouble()
            val noche = nocheStr.toDouble()

            if (!validarDatos(sensibilidad, manana, medioDia, tarde, noche)) {
                Toast.makeText(requireContext(), "Los valores deben ser mayores a 0 y con 1 decimal.", Toast.LENGTH_SHORT).show()
                return
            }

            sharedViewModel.sensibilidad = sensibilidad
            sharedViewModel.ratioManana = manana
            sharedViewModel.ratioMediodia = medioDia
            sharedViewModel.ratioTarde = tarde
            sharedViewModel.ratioNoche = noche

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CalculadoraFragment())
                .addToBackStack(null)
                .commit()

        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Por favor, ingresa valores numéricos válidos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarDatos(
        sensibilidad: Double,
        manana: Double,
        medioDia: Double,
        tarde: Double,
        noche: Double
    ): Boolean {
        return sensibilidad > 0 && tieneUnaDecimal(sensibilidad) &&
                manana > 0 && tieneUnaDecimal(manana) &&
                medioDia > 0 && tieneUnaDecimal(medioDia) &&
                tarde > 0 && tieneUnaDecimal(tarde) &&
                noche > 0 && tieneUnaDecimal(noche)
    }

    private fun tieneUnaDecimal(numero: Double): Boolean {
        return "%.1f".format(numero).toDouble() == numero
    }
}
