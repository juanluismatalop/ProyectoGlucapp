package com.example.proyectoglucapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.proyectoglucapp.data.local.NoticiaDao
import com.example.proyectoglucapp.domain.models.Noticia
import javax.inject.Inject

class NoticiaRepository @Inject constructor(private val noticiaDao: NoticiaDao) {
    suspend fun insertNoticia(noticia: Noticia) = noticiaDao.insertNoticia(noticia)
    fun getAllNoticias(): LiveData<List<Noticia>> = noticiaDao.getAllNoticias()
    suspend fun updateNoticia(noticia: Noticia) = noticiaDao.updateNoticia(noticia)
    suspend fun deleteNoticia(noticia: Noticia) = noticiaDao.deleteNoticia(noticia)
}