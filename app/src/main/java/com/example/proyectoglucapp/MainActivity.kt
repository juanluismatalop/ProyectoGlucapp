package com.example.proyectoglucapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvWelcomeMessage = findViewById<TextView>(R.id.Bienvenido)

        val username = intent.getStringExtra("usuario")
        val password = intent.getStringExtra("usuario")

        tvWelcomeMessage.text = "Bienvenido, $username! Tu contraseña es: $password"
    }


}