package com.example.proyectoglucapp.data.local

import androidx.room.*

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val image: ByteArray,
    val timestamp: Long
)