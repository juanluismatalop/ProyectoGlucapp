package com.example.proyectoglucapp.ui.Noticias.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.data.local.noticia.Noticia

import com.example.proyectoglucapp.ui.viewmodel.NoticiaViewModel
import com.example.proyectoglucapp.ui.views.fragment.Noticias.NoticiaDialogFragment
import com.example.proyectoglucapp.ui.views.fragment.noticias.NoticiaAdapter

class NoticiaFragment : Fragment() {
    private val noticiaViewModel: NoticiaViewModel by viewModels()
    private lateinit var adapter: NoticiaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_noticias_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewNoticias)
        val addNoticiaButton: ImageButton = view.findViewById(R.id.addNoticiaButton)

        adapter = NoticiaAdapter(
            onEditClick = { noticia -> mostrarDialogoEditar(noticia) },
            onDeleteClick = { noticia -> noticiaViewModel.delete(noticia) }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        noticiaViewModel.allNoticias.observe(viewLifecycleOwner) { noticias ->
            adapter.setNoticias(noticias)
        }

        addNoticiaButton.setOnClickListener {
            mostrarDialogoAgregar()
        }
    }

    private fun mostrarDialogoAgregar() {
        NoticiaDialogFragment(null) { nuevaNoticia ->
            noticiaViewModel.insert(nuevaNoticia)
        }.show(parentFragmentManager, "AgregarNoticia")
    }

    private fun mostrarDialogoEditar(noticia: Noticia) {
        NoticiaDialogFragment(noticia) { noticiaEditada ->
            noticiaViewModel.update(noticiaEditada)
        }.show(parentFragmentManager, "EditarNoticia")
    }
}
