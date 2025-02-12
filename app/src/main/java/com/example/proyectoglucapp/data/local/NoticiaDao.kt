package com.example.proyectoglucapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proyectoglucapp.domain.models.Noticia

@Dao
interface NoticiaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoticia(noticia: Noticia)

    @Query("SELECT * FROM noticias")
    fun getAllNoticias(): LiveData<List<Noticia>>

    @Update
    suspend fun updateNoticia(noticia: Noticia)

    @Delete
    suspend fun deleteNoticia(noticia: Noticia)
}
