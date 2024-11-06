package com.example.proyectoglucapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LogInActivity : AppCompatActivity() {

    private val MYUSER = "usuario"
    private val MYPASS = "contrasena"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val etUsername = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etContrasenna)
        val btnValidar = findViewById<Button>(R.id.Validar)

        btnValidar.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == MYUSER && password == MYPASS) {
                // Si es correcto, inicia el Activity Principal
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}