package com.example.proyectoglucapp.ui.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculadoraMisDatosViewModel @Inject constructor() : ViewModel() {
    var sensibilidad: Double? = null
    var ratioManana: Double? = null
    var ratioMediodia: Double? = null
    var ratioTarde: Double? = null
    var ratioNoche: Double? = null
}