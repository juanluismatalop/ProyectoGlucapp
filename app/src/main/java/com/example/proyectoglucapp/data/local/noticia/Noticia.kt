package com.example.proyectoglucapp.data.local.noticia

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noticias")
data class Noticia(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String,
    val descripcion: String
)

