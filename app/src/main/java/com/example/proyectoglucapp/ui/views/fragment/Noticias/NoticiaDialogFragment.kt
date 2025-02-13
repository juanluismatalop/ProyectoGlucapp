package com.example.proyectoglucapp.ui.views.fragment.Noticias

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.data.local.noticia.Noticia


class NoticiaDialogFragment(
    private val noticia: Noticia?,
    private val onSaveClick: (Noticia) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.fragment_noticia_dialog, null)

        val editTextTitulo = view.findViewById<EditText>(R.id.editTextTitulo)
        val editTextDescripcion = view.findViewById<EditText>(R.id.editTextDescripcion)
        val buttonGuardar = view.findViewById<Button>(R.id.buttonGuardar)

        noticia?.let {
            editTextTitulo.setText(it.titulo)
            editTextDescripcion.setText(it.descripcion)
        }

        buttonGuardar.setOnClickListener {
            val titulo = editTextTitulo.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()

            if (titulo.isNotEmpty() && descripcion.isNotEmpty()) {
                val nuevaNoticia = Noticia(noticia?.id ?: 0, titulo, descripcion)
                onSaveClick(nuevaNoticia)
                dismiss()
            }
        }

        builder.setView(view)
        return builder.create()
    }
}
