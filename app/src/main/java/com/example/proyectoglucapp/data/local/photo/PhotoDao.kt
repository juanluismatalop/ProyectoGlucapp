package com.example.proyectoglucapp.data.local.photo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: PhotoEntity)

    @Query("SELECT * FROM photos ORDER BY timestamp DESC")
    suspend fun getAllPhotos(): List<PhotoEntity>
}
