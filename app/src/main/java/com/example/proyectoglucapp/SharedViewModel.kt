package com.example.proyectoglucapp

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var sensibilidad: Double? = null
    var ratioManana: Double? = null
    var ratioMediodia: Double? = null
    var ratioTarde: Double? = null
    var ratioNoche: Double? = null
}
