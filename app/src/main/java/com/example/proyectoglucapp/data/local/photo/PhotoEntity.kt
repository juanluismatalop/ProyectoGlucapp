package com.example.proyectoglucapp.data.local.photo

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val imageData: ByteArray,
    val timestamp: Long
) {
    fun toBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    }

    companion object {
        fun fromBitmap(bitmap: Bitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }
    }
}
