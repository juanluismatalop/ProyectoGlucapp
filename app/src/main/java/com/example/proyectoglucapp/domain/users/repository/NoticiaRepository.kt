package com.example.proyectoglucapp.data.repository

import androidx.lifecycle.LiveData
import com.example.proyectoglucapp.data.local.noticia.Noticia
import com.example.proyectoglucapp.data.local.noticia.NoticiaDao


class NoticiaRepository(private val noticiaDao: NoticiaDao) {
    val allNoticias: LiveData<List<Noticia>> = noticiaDao.getAllNoticias()

    suspend fun insert(noticia: Noticia) {
        noticiaDao.insert(noticia)
    }

    suspend fun update(noticia: Noticia) {
        noticiaDao.update(noticia)
    }

    suspend fun delete(noticia: Noticia) {
        noticiaDao.delete(noticia)
    }
}
