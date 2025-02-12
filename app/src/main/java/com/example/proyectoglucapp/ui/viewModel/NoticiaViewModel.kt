package com.example.proyectoglucapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.proyectoglucapp.domain.repository.NoticiaRepository
import com.example.proyectoglucapp.domain.models.Noticia

class NoticiaViewModel(private val noticiaRepository: NoticiaRepository) : ViewModel() {
    val noticias: LiveData<List<Noticia>> = noticiaRepository.getAllNoticias()

    fun addNoticia(noticia: Noticia) {
        viewModelScope.launch { noticiaRepository.insertNoticia(noticia) }
    }

    fun updateNoticia(noticia: Noticia) {
        viewModelScope.launch { noticiaRepository.updateNoticia(noticia) }
    }

    fun deleteNoticia(noticia: Noticia) {
        viewModelScope.launch { noticiaRepository.deleteNoticia(noticia) }
    }
}