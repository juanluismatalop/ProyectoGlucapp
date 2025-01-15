package com.example.proyectoglucapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MisDatosFragment : Fragment() {

    private lateinit var factorDeSensibilidad: EditText
    private lateinit var ratioManana: EditText
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
            val sensibilidad = sensibilidadStr.toFloat()
            val manana = mananaStr.toFloat()
            val medioDia = medioDiaStr.toFloat()
            val tarde = tardeStr.toFloat()
            val noche = nocheStr.toFloat()

            if (!validarDatos(sensibilidad, manana, medioDia, tarde, noche)) {
                Toast.makeText(requireContext(), "Los valores deben ser mayores a 0 y con 1 decimal.", Toast.LENGTH_SHORT).show()
                return
            }

            guardarDatosEnFirebase(sensibilidad, manana, medioDia, tarde, noche) { exitoso ->
                if (exitoso) {
                    val bundle = Bundle().apply {
                        putFloat("sensibilidad", sensibilidad)
                        putFloat("manana", manana)
                        putFloat("medioDia", medioDia)
                        putFloat("tarde", tarde)
                        putFloat("noche", noche)
                    }

                    val calculadoraFragment = CalculadoraFragment().apply {
                        arguments = bundle
                    }

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, calculadoraFragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    Toast.makeText(requireContext(), "No se pudo guardar en Firebase.", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Por favor, ingresa valores numéricos válidos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarDatos(
        sensibilidad: Float,
        manana: Float,
        medioDia: Float,
        tarde: Float,
        noche: Float
    ): Boolean {
        return sensibilidad > 0 && tieneUnaDecimal(sensibilidad) &&
                manana > 0 && tieneUnaDecimal(manana) &&
                medioDia > 0 && tieneUnaDecimal(medioDia) &&
                tarde > 0 && tieneUnaDecimal(tarde) &&
                noche > 0 && tieneUnaDecimal(noche)
    }

    private fun tieneUnaDecimal(numero: Float): Boolean {
        return "%.1f".format(numero).toFloat() == numero
    }

    private fun guardarDatosEnFirebase(
        sensibilidad: Float,
        manana: Float,
        medioDia: Float,
        tarde: Float,
        noche: Float,
        onComplete: (Boolean) -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Toast.makeText(requireContext(), "Usuario no autenticado.", Toast.LENGTH_SHORT).show()
            onComplete(false)
            return
        }

        val userId = currentUser.uid
        val databaseRef = FirebaseDatabase.getInstance().getReference("users").child(userId)

        val userData = mapOf(
            "sensibilidad" to sensibilidad,
            "manana" to manana,
            "medioDia" to medioDia,
            "tarde" to tarde,
            "noche" to noche
        )

        databaseRef.setValue(userData).addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }
}
