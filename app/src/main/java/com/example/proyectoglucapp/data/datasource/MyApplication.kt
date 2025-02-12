package com.example.proyectoglucapp.data.datasource

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}
