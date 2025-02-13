package com.example.proyectoglucapp.data.local.noticia

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoticiaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noticia: Noticia)

    @Update
    suspend fun update(noticia: Noticia)

    @Delete
    suspend fun delete(noticia: Noticia)

    @Query("SELECT * FROM noticias ORDER BY id DESC")
    fun getAllNoticias(): LiveData<List<Noticia>>

}
