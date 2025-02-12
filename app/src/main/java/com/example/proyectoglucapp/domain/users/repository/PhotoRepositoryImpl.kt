package com.example.proyectoglucapp.domain.users.repository

import com.example.proyectoglucapp.data.local.PhotoDao
import com.example.proyectoglucapp.data.local.PhotoEntity
import com.example.proyectoglucapp.domain.models.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepositoryImpl(private val photoDao: PhotoDao) : PhotoRepository {

    override suspend fun savePhoto(photo: Photo) {
        withContext(Dispatchers.IO) {
            val entity = PhotoEntity(
                id = photo.id,
                image = photo.bitmap.let { bitmap ->
                    val outputStream = java.io.ByteArrayOutputStream()
                    bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.toByteArray()
                },
                timestamp = photo.timestamp
            )
            photoDao.insertPhoto(entity)
        }
    }

    override suspend fun getAllPhotos(): List<Photo> = withContext(Dispatchers.IO) {
        photoDao.getAllPhotos().map { entity ->
            Photo(
                id = entity.id,
                bitmap = android.graphics.BitmapFactory.decodeByteArray(entity.image, 0, entity.image.size),
                timestamp = entity.timestamp
            )
        }
    }

    override suspend fun getPhotoById(id: Long): Photo? = withContext(Dispatchers.IO) {
        photoDao.getPhotoById(id)?.let { entity ->
            Photo(
                id = entity.id,
                bitmap = android.graphics.BitmapFactory.decodeByteArray(entity.image, 0, entity.image.size),
                timestamp = entity.timestamp
            )
        }
    }

    override suspend fun deletePhoto(id: Long) {
        withContext(Dispatchers.IO) {
            photoDao.getPhotoById(id)?.let { photoDao.deletePhoto(it) }
        }
    }
}