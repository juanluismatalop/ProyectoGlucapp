package com.example.proyectoglucapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noticias")
data class Noticia(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descripcion: String
)
