package com.example.proyectoglucapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class CalculadoraFragment : Fragment() {

    private lateinit var nivelGlucosa: EditText
    private lateinit var racionesComida: EditText
    private lateinit var factorDeSensibilidad: EditText
    private lateinit var ratioMannana: EditText
    private lateinit var ratioMedioDia: EditText
    private lateinit var ratioTarde: EditText
    private lateinit var ratioNoche: EditText
    private lateinit var calcularButton: Button
    private lateinit var valueManana: TextView
    private lateinit var valueMediodia: TextView
    private lateinit var valueTarde: TextView
    private lateinit var valueNoche: TextView
    private lateinit var tiempoEsperar: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculadora, container, false)

        nivelGlucosa = view.findViewById(R.id.nivelGlucosa)
        racionesComida = view.findViewById(R.id.racionesComida)
        factorDeSensibilidad = view.findViewById(R.id.factorDeSensibilidad)
        ratioMannana = view.findViewById(R.id.ratioMannana)
        ratioMedioDia = view.findViewById(R.id.ratioMedioDia)
        ratioTarde = view.findViewById(R.id.ratioTarde)
        ratioNoche = view.findViewById(R.id.ratioNoche)
        calcularButton = view.findViewById(R.id.calcularButton)
        valueManana = view.findViewById(R.id.valueManana)
        valueMediodia = view.findViewById(R.id.valueMediodia)
        valueTarde = view.findViewById(R.id.valueTarde)
        valueNoche = view.findViewById(R.id.valueNoche)
        tiempoEsperar = view.findViewById(R.id.tiempoEsperar)

        calcularButton.setOnClickListener {
            calcularResultados()
        }
        if (nivelGlucosa == null) {
            throw IllegalStateException("El campo nivelGlucosa no existe en el diseño inflado")
        }


        return view
    }

    private fun calcularResultados() {
        val nivelGlucosaValue = nivelGlucosa.text.toString().toIntOrNull()
        val racionesValue = racionesComida.text.toString().toIntOrNull()
        val factorSensibilidadValue = factorDeSensibilidad.text.toString().toIntOrNull()

        val ratioMananaValue = ratioMannana.text.toString().toIntOrNull()
        val ratioMediodiaValue = ratioMedioDia.text.toString().toIntOrNull()
        val ratioTardeValue = ratioTarde.text.toString().toIntOrNull()
        val ratioNocheValue = ratioNoche.text.toString().toIntOrNull()

        if (nivelGlucosaValue == null || racionesValue == null || factorSensibilidadValue == null ||
            ratioMananaValue == null || ratioMediodiaValue == null ||
            ratioTardeValue == null || ratioNocheValue == null) {
            valueManana.text = "--"
            valueMediodia.text = "--"
            valueTarde.text = "--"
            valueNoche.text = "--"
            tiempoEsperar.text = "--"
            return
        }

        val ajusteSensibilidad = if (nivelGlucosaValue > 199) {
            ((nivelGlucosaValue - 199) / 100.0).toInt() * factorSensibilidadValue
        } else {
            0
        }

        val tiempoEsperaMinutos = nivelGlucosaValue.toString().take(2).toIntOrNull() ?: 0
        tiempoEsperar.text = "$tiempoEsperaMinutos min"

        val resultadoManana = (ratioMananaValue * racionesValue) + ajusteSensibilidad
        val resultadoMediodia = (ratioMediodiaValue * racionesValue) + ajusteSensibilidad
        val resultadoTarde = (ratioTardeValue * racionesValue) + ajusteSensibilidad
        val resultadoNoche = (ratioNocheValue * racionesValue) + ajusteSensibilidad

        valueManana.text = resultadoManana.toString()
        valueMediodia.text = resultadoMediodia.toString()
        valueTarde.text = resultadoTarde.toString()
        valueNoche.text = resultadoNoche.toString()
    }
}
