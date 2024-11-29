package com.example.proyectoglucapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectoglucapp.databinding.FragmentNoticiasRecyclerBinding

class NoticiasRecycler : Fragment() {
    private var _binding: FragmentNoticiasRecyclerBinding? = null
    private val binding get() = _binding!!

    private val noticiasList = mutableListOf(
        Noticia("¿Posible cura a la diabetes?", "Se ha descubierto en Japón que se puede curar la diabetes a base de células madre"),
    )

    private val adapter by lazy {
        NoticiasAdapter(noticiasList, { position ->
            removeItem(position)
        }, { position ->
            editItem(position)
        }, {
            addNewItem()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoticiasRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.addNoticiaButton.setOnClickListener {
            val fragment = AgregarNoticiaFragment()
            fragment.setOnNoticiaAddedListener { noticia ->
                noticiasList.add(noticia)
                adapter.notifyItemInserted(noticiasList.size - 1)
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun removeItem(position: Int) {
        noticiasList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun addNewItem() {
        val newNoticia = Noticia("Nueva Noticia sobre Diabetes", "Descripción de la nueva noticia.")
        adapter.addItem(newNoticia)
    }

    private fun editItem(position: Int) {
        val noticia = noticiasList[position]
        val fragment = EditarNoticiaFragment()
        fragment.setNoticia(noticia)
        fragment.setOnNoticiaEditedListener { editedNoticia ->
            noticiasList[position] = editedNoticia
            adapter.notifyItemChanged(position)
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



