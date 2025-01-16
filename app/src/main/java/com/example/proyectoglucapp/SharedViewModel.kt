package com.example.proyectoglucapp

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var sensibilidad: Float? = null
    var ratioManana: Float? = null
    var ratioMediodia: Float? = null
    var ratioTarde: Float? = null
    var ratioNoche: Float? = null
}
