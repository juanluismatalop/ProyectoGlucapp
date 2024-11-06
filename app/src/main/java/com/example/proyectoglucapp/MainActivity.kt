package com.example.proyectoglucapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val usuario = intent.getStringExtra("usuario")
        val contrasenna = intent.getStringExtra("usuario")

        tvWelcomeMessage.text = "Bienvenido, $usuario!"

        val botonCerrarSesion = findViewById<Button>(R.id.boronCerrarSesion)
        botonCerrarSesion.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}