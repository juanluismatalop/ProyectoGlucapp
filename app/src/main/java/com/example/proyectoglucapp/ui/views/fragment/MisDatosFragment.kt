package com.example.proyectoglucapp.ui.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.ui.viewModel.CalculadoraMisDatosViewModel

class MisDatosFragment : Fragment() {

    private lateinit var factorDeSensibilidad: EditText
    private lateinit var ratioManana: EditText
    private lateinit var ratioMedioDia: EditText
    private lateinit var ratioTarde: EditText
    private lateinit var ratioNoche: EditText
    private lateinit var saveButton: Button

    private lateinit var sharedViewModel: CalculadoraMisDatosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mis_datos, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(CalculadoraMisDatosViewModel::class.java)

        factorDeSensibilidad = view.findViewById(R.id.factorDeSensibilidad)
        ratioManana = view.findViewById(R.id.ratioMannana)
        ratioMedioDia = view.findViewById(R.id.ratioMedioDia)
        ratioTarde = view.findViewById(R.id.ratioTarde)
        ratioNoche = view.findViewById(R.id.ratioNoche)
        saveButton = view.findViewById(R.id.button)

        // Configurar los EditText para permitir solo números con un decimal
        configurarEntradaNumerica(factorDeSensibilidad)
        configurarEntradaNumerica(ratioManana)
        configurarEntradaNumerica(ratioMedioDia)
        configurarEntradaNumerica(ratioTarde)
        configurarEntradaNumerica(ratioNoche)

        saveButton.setOnClickListener {
            guardarDatosYEnviar()
        }

        return view
    }

    private fun configurarEntradaNumerica(editText: EditText) {
        // Configurar el teclado para que solo acepte números decimales
        editText.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL

        // Limitar el número de decimales a 1 usando un filtro
        editText.filters = arrayOf(android.text.InputFilter { source, start, end, dest, dstart, dend ->
            val resultado = StringBuilder(dest).replace(dstart, dend, source.subSequence(start, end).toString())
            if (resultado.toString().matches(Regex("^\\d*\\.?\\d{0,1}\$"))) {
                return@InputFilter null
            }
            return@InputFilter ""
        })
    }

    private fun guardarDatosYEnviar() {
        val sensibilidadStr = factorDeSensibilidad.text.toString()
            .replace(",", ".") // Reemplazar comas por puntos
        val mananaStr = ratioManana.text.toString()
            .replace(",", ".")
        val medioDiaStr = ratioMedioDia.text.toString()
            .replace(",", ".")
        val tardeStr = ratioTarde.text.toString()
            .replace(",", ".")
        val nocheStr = ratioNoche.text.toString()
            .replace(",", ".")

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
        return (numero * 10) % 1 == 0.0
    }
}