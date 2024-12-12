package com.example.proyectoglucapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoglucapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NoticiasRecycler())
            .commit()


        val usuario = intent.getStringExtra("usuario")
        binding.Bienvenido.text = "Bienvenido, $usuario!"


        /**binding.botonAjuste.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AjustesFragment())
                .commit()
        }*/

        /**binding.botonCalculadora.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CalculadoraFragment())
                .commit()
        }*/

        /**binding.botonMisDatos.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MisDatosFragment())
                .commit()
        }*/

        binding.boronCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}




