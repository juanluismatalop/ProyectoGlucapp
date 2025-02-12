package com.example.proyectoglucapp.domain.models

import android.graphics.Bitmap

data class Photo(
    val id: Long,
    val bitmap: Bitmap,
    val timestamp: Long
)