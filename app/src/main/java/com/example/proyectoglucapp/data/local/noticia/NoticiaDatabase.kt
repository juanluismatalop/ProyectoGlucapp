package com.example.proyectoglucapp.data.local.noticia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Noticia::class], version = 1, exportSchema = false)
abstract class NoticiaDatabase : RoomDatabase() {
    abstract fun noticiaDao(): NoticiaDao

    companion object {
        @Volatile
        private var INSTANCE: NoticiaDatabase? = null

        fun getDatabase(context: Context): NoticiaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoticiaDatabase::class.java,
                    "noticia_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
