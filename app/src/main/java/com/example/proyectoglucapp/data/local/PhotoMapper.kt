package com.example.proyectoglucapp.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.proyectoglucapp.domain.models.Photo
import java.io.ByteArrayOutputStream

class PhotoMapper {
    fun fromDomain(photo: Photo): PhotoEntity {
        val outputStream = ByteArrayOutputStream()
        photo.bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return PhotoEntity(
            id = photo.id,
            image = outputStream.toByteArray(),
            timestamp = photo.timestamp
        )
    }

    fun toDomain(photoEntity: PhotoEntity): Photo {
        val bitmap = BitmapFactory.decodeByteArray(photoEntity.image, 0, photoEntity.image.size)
        return Photo(
            id = photoEntity.id,
            bitmap = bitmap,
            timestamp = photoEntity.timestamp
        )
    }
}