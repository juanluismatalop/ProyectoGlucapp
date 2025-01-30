package com.example.proyectoglucapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username
    init {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email
        _username.value = email?.substringBefore("@") ?: "Usuario"
    }
    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}

