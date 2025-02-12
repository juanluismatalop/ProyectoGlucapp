package com.example.proyectoglucapp.ui.viewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoglucapp.domain.users.repository.PhotoRepository
import com.example.proyectoglucapp.domain.users.repository.PhotoRepositoryImpl
import com.example.proyectoglucapp.ui.viewmodel.TablasViewModel


class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TablasViewModel::class.java)) {
            val database = AppDatabase.getInstance(context)
            val photoRepository: PhotoRepository = PhotoRepositoryImpl(database.photoDao())
            return TablasViewModel(photoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
