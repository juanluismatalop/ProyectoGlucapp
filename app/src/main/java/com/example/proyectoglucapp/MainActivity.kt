package com.example.proyectoglucapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoticiasAdapter
    private var noticiasList = mutableListOf<Noticia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        noticiasList = mutableListOf(
            Noticia("¿Posible cura a la diabetes?", "Se ha descubierto en Japón que se puede curar la diabetes a base de células madre"),
            Noticia("Nuevo tratamiento para diabetes tipo 2", "Un nuevo tratamiento basado en la insulina promete revolucionar el tratamiento de la diabetes tipo 2.")
        )

        adapter = NoticiasAdapter(noticiasList) { position ->
            adapter.removeItem(position) // Eliminar noticia
        }

        recyclerView.adapter = adapter

        val tvWelcomeMessage = findViewById<TextView>(R.id.Bienvenido)
        val usuario = intent.getStringExtra("usuario")
        tvWelcomeMessage.text = "Bienvenido, $usuario!"

        val botonCerrarSesion = findViewById<ImageView>(R.id.boronCerrarSesion)
        botonCerrarSesion.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

