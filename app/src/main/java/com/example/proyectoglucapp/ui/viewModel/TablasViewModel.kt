package com.example.proyectoglucapp.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.example.proyectoglucapp.domain.models.Photo
import com.example.proyectoglucapp.domain.users.repository.PhotoRepository
import kotlinx.coroutines.launch

class TablasViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    fun savePhoto(bitmap: Bitmap) {
        val photo = Photo(id = 0, bitmap = bitmap, timestamp = System.currentTimeMillis())
        viewModelScope.launch {
            photoRepository.savePhoto(photo)
            loadPhotos()
        }
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _photos.value = photoRepository.getAllPhotos()
        }
    }
}