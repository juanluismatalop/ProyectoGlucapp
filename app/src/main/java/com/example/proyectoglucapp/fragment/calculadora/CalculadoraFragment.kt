package com.example.proyectoglucapp.fragment.calculadora

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.viewModel.misDatos_Calculadora.SharedViewModel

class CalculadoraFragment : Fragment() {

    private lateinit var valueManana: TextView
    private lateinit var valueMediodia: TextView
    private lateinit var valueTarde: TextView
    private lateinit var valueNoche: TextView
    private lateinit var nivelGlucosa: EditText
    private lateinit var racionesComida: EditText
    private lateinit var tiempoEsperar: TextView

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculadora, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        valueManana = view.findViewById(R.id.valueManana)
        valueMediodia = view.findViewById(R.id.valueMediodia)
        valueTarde = view.findViewById(R.id.valueTarde)
        valueNoche = view.findViewById(R.id.valueNoche)

        nivelGlucosa = view.findViewById(R.id.nivelGlucosa)
        racionesComida = view.findViewById(R.id.racionesComida)
        tiempoEsperar = view.findViewById(R.id.tiempoEsperar)

        val calcularButton: Button = view.findViewById(R.id.calcularButton)
        calcularButton.setOnClickListener { calcularResultados() }

        nivelGlucosa.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizarTiempoEsperar(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        mostrarDatosIniciales()

        return view
    }

    private fun mostrarDatosIniciales() {
        valueManana.text = sharedViewModel.ratioManana?.toString() ?: "N/A"
        valueMediodia.text = sharedViewModel.ratioMediodia?.toString() ?: "N/A"
        valueTarde.text = sharedViewModel.ratioTarde?.toString() ?: "N/A"
        valueNoche.text = sharedViewModel.ratioNoche?.toString() ?: "N/A"
    }

    private fun actualizarTiempoEsperar(nivelGlucosaStr: String) {
        if (nivelGlucosaStr.length >= 2) {
            val primerosDosDigitos = nivelGlucosaStr.substring(0, 2)
            tiempoEsperar.text = primerosDosDigitos
        } else {
            tiempoEsperar.text = "--"
        }
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
            val racionesComida = racionesComidaStr.toDouble()
            val ratioManana = sharedViewModel.ratioManana ?: 0.0
            val ratioMediodia = sharedViewModel.ratioMediodia ?: 0.0
            val ratioTarde = sharedViewModel.ratioTarde ?: 0.0
            val ratioNoche = sharedViewModel.ratioNoche ?: 0.0
            val sensibilidad = sharedViewModel.sensibilidad ?: 0.0

            if (nivelGlucosa == 0 || racionesComida == 0.0 || ratioManana == 0.0 ||
                ratioMediodia == 0.0 || ratioTarde == 0.0 || ratioNoche == 0.0 || sensibilidad == 0.0) {
                valueManana.text = "Datos incompletos"
                valueMediodia.text = "Datos incompletos"
                valueTarde.text = "Datos incompletos"
                valueNoche.text = "Datos incompletos"
                return
            }

            if (sensibilidad == 0.0) {
                Toast.makeText(requireContext(), "El valor de sensibilidad no es válido.", Toast.LENGTH_SHORT).show()
                return
            }

            var excesoGlucosa = nivelGlucosa
            var i = 0
            while (excesoGlucosa > 150) {
                excesoGlucosa -= 100
                i++
            }

            val ajusteSensibilidad = i * sensibilidad

            val resultadoManana = (ratioManana * racionesComida) + ajusteSensibilidad
            val resultadoMediodia = (ratioMediodia * racionesComida) + ajusteSensibilidad
            val resultadoTarde = (ratioTarde * racionesComida) + ajusteSensibilidad
            val resultadoNoche = (ratioNoche * racionesComida) + ajusteSensibilidad

            valueManana.text = String.format("%.1f", resultadoManana)
            valueMediodia.text = String.format("%.1f", resultadoMediodia)
            valueTarde.text = String.format("%.1f", resultadoTarde)
            valueNoche.text = String.format("%.1f", resultadoNoche)

        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Por favor, ingresa valores numéricos válidos.", Toast.LENGTH_SHORT).show()
        }
    }
}
