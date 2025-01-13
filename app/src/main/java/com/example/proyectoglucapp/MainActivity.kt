package com.example.proyectoglucapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.proyectoglucapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        navView = binding.navView

        // Configurar el toggle del DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configuración del NavigationView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_noticias -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, NoticiasRecycler())
                        .commit()
                }
                R.id.nav_ajustes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AjustesFragment())
                        .commit()
                }
                R.id.nav_mis_datos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MisDatosFragment())
                        .commit()
                }
                R.id.nav_logout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LogInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        // Cargar el fragmento inicial
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NoticiasRecycler())
            .commit()

        // Configurar el saludo con el email del usuario
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email

        if (email != null) {
            val username = email.substringBefore("@")
            binding.Bienvenido.text = "Bienvenido, $username!"
        } else {
            binding.Bienvenido.text = "Bienvenido, Usuario!"
        }

        // Listener para el botón "Cerrar Sesión"
        binding.boronCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Configurar los botones para cambiar el fragment
        binding.botonMisDatos.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MisDatosFragment())
                .commit()
        }

        binding.botonCalculadora.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CalculadoraFragment())
                .commit()
        }
    }
}







