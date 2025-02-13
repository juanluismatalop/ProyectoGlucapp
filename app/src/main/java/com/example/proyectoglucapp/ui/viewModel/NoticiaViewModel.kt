package com.example.proyectoglucapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.proyectoglucapp.data.local.noticia.Noticia
import com.example.proyectoglucapp.data.local.noticia.NoticiaDatabase

import com.example.proyectoglucapp.data.repository.NoticiaRepository
import kotlinx.coroutines.launch

class NoticiaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoticiaRepository
    val allNoticias: LiveData<List<Noticia>>

    init {
        val noticiaDao = NoticiaDatabase.getDatabase(application).noticiaDao()
        repository = NoticiaRepository(noticiaDao)
        allNoticias = repository.allNoticias
    }

    fun insert(noticia: Noticia) = viewModelScope.launch {
        repository.insert(noticia)
    }

    fun update(noticia: Noticia) = viewModelScope.launch {
        repository.update(noticia)
    }

    fun delete(noticia: Noticia) = viewModelScope.launch {
        repository.delete(noticia)
    }
}

