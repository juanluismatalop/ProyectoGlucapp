package com.example.proyectoglucapp.fragment.Noticias.editar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.fragment.Noticias.Noticia.Noticia

class EditarNoticiaFragment : Fragment() {

    private lateinit var tituloEditText: EditText
    private lateinit var descripcionEditText: EditText
    private lateinit var editarButton: Button
    private lateinit var onNoticiaEdited: (Noticia) -> Unit

    private lateinit var noticia: Noticia

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_noticia, container, false)

        tituloEditText = view.findViewById(R.id.editTextTitulo)
        descripcionEditText = view.findViewById(R.id.editTextDescripcion)
        editarButton = view.findViewById(R.id.buttonEditarNoticia)

        tituloEditText.setText(noticia.titulo)
        descripcionEditText.setText(noticia.descripcion)

        editarButton.setOnClickListener {
            val titulo = tituloEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()

            if (titulo.isNotEmpty() && descripcion.isNotEmpty()) {
                noticia.titulo = titulo
                noticia.descripcion = descripcion
                onNoticiaEdited(noticia)
                parentFragmentManager.popBackStack()
            }
        }

        return view
    }

    fun setOnNoticiaEditedListener(listener: (Noticia) -> Unit) {
        onNoticiaEdited = listener
    }

    fun setNoticia(noticia: Noticia) {
        this.noticia = noticia
    }
}

