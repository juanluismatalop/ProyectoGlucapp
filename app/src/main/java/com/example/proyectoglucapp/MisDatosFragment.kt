package com.example.proyectoglucapp

import CalculadoraFragment
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

        if (!validarDatos(sensibilidad, manana, medioDia, tarde, noche)) {
            Toast.makeText(requireContext(), "Por favor, completa todos los campos correctamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val bundle = Bundle().apply {
            putString("sensibilidad", sensibilidad)
            putString("manana", manana)
            putString("medioDia", medioDia)
            putString("tarde", tarde)
            putString("noche", noche)
        }

        val calculadoraFragment = CalculadoraFragment().apply {
            arguments = bundle
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, calculadoraFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun validarDatos(
        sensibilidad: String,
        manana: String,
        medioDia: String,
        tarde: String,
        noche: String
    ): Boolean {
        return sensibilidad.toFloatOrNull()?.let { it > 0 && tieneUnaDecimal(it) } == true &&
                manana.toFloatOrNull()?.let { it > 0 && tieneUnaDecimal(it) } == true &&
                medioDia.toFloatOrNull()?.let { it > 0 && tieneUnaDecimal(it) } == true &&
                tarde.toFloatOrNull()?.let { it > 0 && tieneUnaDecimal(it) } == true &&
                noche.toFloatOrNull()?.let { it > 0 && tieneUnaDecimal(it) } == true
    }

    private fun tieneUnaDecimal(numero: Float): Boolean {
        val partes = numero.toString().split(".")
        return partes.size == 2 && partes[1].length <= 1
    }
    private fun guardarDatosEnFirebase(
        sensibilidad: Float,
        manana: Float,
        medioDia: Float,
        tarde: Float,
        noche: Float
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Toast.makeText(requireContext(), "Usuario no autenticado.", Toast.LENGTH_SHORT).show()
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
            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Datos guardados exitosamente.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Error al guardar los datos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

