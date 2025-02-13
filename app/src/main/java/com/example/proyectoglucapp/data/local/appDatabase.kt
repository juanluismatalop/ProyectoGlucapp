package com.example.proyectoglucapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectoglucapp.data.local.noticia.Noticia
import com.example.proyectoglucapp.data.local.noticia.NoticiaDao
import com.example.proyectoglucapp.data.local.photo.PhotoDao
import com.example.proyectoglucapp.data.local.photo.PhotoEntity

@Database(entities = [Noticia::class, PhotoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noticiaDao(): NoticiaDao
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}