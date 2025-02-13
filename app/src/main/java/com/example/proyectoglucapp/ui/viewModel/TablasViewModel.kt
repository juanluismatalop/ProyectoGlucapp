package com.example.proyectoglucapp.ui.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.proyectoglucapp.data.local.photo.PhotoEntity
import com.example.proyectoglucapp.domain.users.repository.PhotoRepository

import kotlinx.coroutines.launch

class TablasViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PhotoRepository(application)

    private val _photos = MutableLiveData<List<PhotoEntity>>()
    val photos: LiveData<List<PhotoEntity>> get() = _photos

    fun savePhoto(bitmap: Bitmap) {
        viewModelScope.launch {
            val photo = PhotoEntity(imageData = PhotoEntity.fromBitmap(bitmap), timestamp = System.currentTimeMillis())
            repository.insertPhoto(photo)
            loadPhotos()
        }
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _photos.postValue(repository.getAllPhotos())
        }
    }
}
