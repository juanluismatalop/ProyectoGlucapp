package com.example.proyectoglucapp.domain.users.repository

import android.content.Context
import com.example.proyectoglucapp.data.local.AppDatabase
import com.example.proyectoglucapp.data.local.photo.PhotoEntity

class PhotoRepository(context: Context) {
    private val photoDao = AppDatabase.getDatabase(context).photoDao()

    suspend fun insertPhoto(photo: PhotoEntity) {
        photoDao.insert(photo)
    }

    suspend fun getAllPhotos(): List<PhotoEntity> {
        return photoDao.getAllPhotos()
    }
}
