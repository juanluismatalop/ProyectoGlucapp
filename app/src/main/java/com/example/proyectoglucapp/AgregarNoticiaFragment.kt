package com.example.proyectoglucapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import androidx.fragment.app.Fragment

class AgregarNoticiaFragment : Fragment() {

    private lateinit var tituloEditText: EditText
    private lateinit var descripcionEditText: EditText
    private lateinit var agregarButton: Button
    private lateinit var onNoticiaAdded: (Noticia) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar_noticia, container, false)

        tituloEditText = view.findViewById(R.id.editTextTitulo)
        descripcionEditText = view.findViewById(R.id.editTextDescripcion)
        agregarButton = view.findViewById(R.id.buttonAgregarNoticia)

        agregarButton.setOnClickListener {
            val titulo = tituloEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()

            if (titulo.isNotEmpty() && descripcion.isNotEmpty()) {
                val noticia = Noticia(titulo, descripcion)
                onNoticiaAdded(noticia)
                parentFragmentManager.popBackStack()
            }
        }

        return view
    }

    fun setOnNoticiaAddedListener(listener: (Noticia) -> Unit) {
        onNoticiaAdded = listener
    }
}
