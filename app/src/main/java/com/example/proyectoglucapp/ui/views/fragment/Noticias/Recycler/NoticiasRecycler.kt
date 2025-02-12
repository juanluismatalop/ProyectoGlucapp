package com.example.proyectoglucapp.ui.view.fragment

import EditarNoticiaFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.databinding.FragmentNoticiasRecyclerBinding
import com.example.proyectoglucapp.domain.models.Noticia
import com.example.proyectoglucapp.domain.repository.NoticiaRepository
import com.example.proyectoglucapp.ui.viewModel.NoticiaViewModelFactory
import com.example.proyectoglucapp.ui.viewmodel.NoticiaViewModel
import com.example.proyectoglucapp.ui.views.fragment.noticias.NoticiasAdapter

class NoticiasRecycler : Fragment() {
    private var _binding: FragmentNoticiasRecyclerBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NoticiaViewModel
    private lateinit var adapter: NoticiasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticiasRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = NoticiaRepository(AppDatabase.getInstance(requireContext()).noticiaDao())
        val factory = NoticiaViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoticiaViewModel::class.java]

        adapter = NoticiasAdapter(
            onDeleteClick = { noticia -> viewModel.deleteNoticia(noticia) },
            onEditClick = { noticia -> mostrarEditarNoticiaFragment(noticia) }
        )


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.noticias.observe(viewLifecycleOwner) { noticias ->
            adapter.updateNoticias(noticias)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun mostrarEditarNoticiaFragment(noticia: Noticia) {
        val fragment = EditarNoticiaFragment().apply {
            setNoticia(noticia)
            setOnNoticiaEditedListener { noticiaEditada ->
                viewModel.updateNoticia(noticiaEditada)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}