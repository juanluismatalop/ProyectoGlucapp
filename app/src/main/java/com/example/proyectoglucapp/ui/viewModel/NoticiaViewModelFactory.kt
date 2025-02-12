package com.example.proyectoglucapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoglucapp.domain.repository.NoticiaRepository
import com.example.proyectoglucapp.ui.viewmodel.NoticiaViewModel

class NoticiaViewModelFactory(private val noticiaRepository: NoticiaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoticiaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoticiaViewModel(noticiaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
