package com.example.proyectoglucapp.domain.users.repository

import com.example.proyectoglucapp.domain.models.Photo

interface PhotoRepository {
    suspend fun savePhoto(photo: Photo)
    suspend fun getAllPhotos(): List<Photo>
    suspend fun getPhotoById(id: Long): Photo?
    suspend fun deletePhoto(id: Long)
}
