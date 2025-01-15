package com.example.proyectoglucapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class CalculadoraFragment : Fragment() {

    private lateinit var valueManana: TextView
    private lateinit var valueMediodia: TextView
    private lateinit var valueTarde: TextView
    private lateinit var valueNoche: TextView
    private lateinit var nivelGlucosa: EditText
    private lateinit var racionesComida: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculadora, container, false)

        valueManana = view.findViewById(R.id.valueManana)
        valueMediodia = view.findViewById(R.id.valueMediodia)
        valueTarde = view.findViewById(R.id.valueTarde)
        valueNoche = view.findViewById(R.id.valueNoche)

        nivelGlucosa = view.findViewById(R.id.nivelGlucosa)
        racionesComida = view.findViewById(R.id.racionesComida)

        val calcularButton: Button = view.findViewById(R.id.calcularButton)
        calcularButton.setOnClickListener { calcularResultados() }

        return view
    }

    private fun calcularResultados() {
        val nivelGlucosaStr = nivelGlucosa.text.toString()
        val racionesComidaStr = racionesComida.text.toString()

        if (nivelGlucosaStr.isBlank() || racionesComidaStr.isBlank()) {
            Toast.makeText(requireContext(), "Por favor, ingresa todos los valores.", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val nivelGlucosa = nivelGlucosaStr.toInt()
            val racionesComida = racionesComidaStr.toFloat()
            val ratioManana = arguments?.getFloat("manana") ?: 0f
            val ratioMediodia = arguments?.getFloat("medioDia") ?: 0f
            val ratioTarde = arguments?.getFloat("tarde") ?: 0f
            val ratioNoche = arguments?.getFloat("noche") ?: 0f

            if (nivelGlucosa == 0 || racionesComida == 0f || ratioManana == 0f ||
                ratioMediodia == 0f || ratioTarde == 0f || ratioNoche == 0f) {
                valueManana.text = "Datos incompletos"
                valueMediodia.text = "Datos incompletos"
                valueTarde.text = "Datos incompletos"
                valueNoche.text = "Datos incompletos"
                return
            }

            val factorSensibilidad = 100
            val ajusteSensibilidad = calcularAjusteSensibilidad(nivelGlucosa, factorSensibilidad)

            val resultadoManana = (ratioManana * racionesComida) + ajusteSensibilidad
            val resultadoMediodia = (ratioMediodia * racionesComida) + ajusteSensibilidad
            val resultadoTarde = (ratioTarde * racionesComida) + ajusteSensibilidad
            val resultadoNoche = (ratioNoche * racionesComida) + ajusteSensibilidad

            valueManana.text = String.format("%.2f", resultadoManana)
            valueMediodia.text = String.format("%.2f", resultadoMediodia)
            valueTarde.text = String.format("%.2f", resultadoTarde)
            valueNoche.text = String.format("%.2f", resultadoNoche)

        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Por favor, ingresa valores numéricos válidos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calcularAjusteSensibilidad(nivelGlucosa: Int, factorSensibilidad: Int): Int {
        return if (nivelGlucosa > 200) {
            val exceso = nivelGlucosa - 200
            val ajustes = (exceso / 100)
            ajustes * factorSensibilidad
        } else {
            0
        }
    }
}
