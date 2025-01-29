package com.example.proyectoglucapp.ui.views.Activity.Main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.proyectoglucapp.ui.views.fragment.Noticias.Recycler.NoticiasRecycler
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.databinding.ActivityMainBinding
import com.example.proyectoglucapp.ui.views.Activity.LogInActivity
import com.example.proyectoglucapp.ui.views.fragment.AjustesFragment
import com.example.proyectoglucapp.ui.views.fragment.CalculadoraFragment
import com.example.proyectoglucapp.ui.views.fragment.MisDatosFragment
import com.example.proyectoglucapp.ui.views.fragment.TablasFragment
import com.example.proyectoglucapp.ui.views.fragment.VideoFragment
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

        setupDrawerLayout()
        setupNavigationView()
        setupBottomButtons()

        // Mostrar el fragmento inicial
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NoticiasRecycler())
            .commit()

        // Configurar el texto de bienvenida
        displayWelcomeMessage()
    }

    private fun setupDrawerLayout() {
        drawerLayout = binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupNavigationView() {
        navView = binding.navView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_mis_datos -> navigateToFragment(MisDatosFragment())
                R.id.nav_ajustes -> navigateToFragment(AjustesFragment())
                R.id.nav_tablas -> navigateToActivity(TablasFragment::class.java)
                R.id.nav_video -> navigateToActivity(VideoFragment::class.java)
                R.id.nav_logout -> logoutUser()
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun setupBottomButtons() {
        binding.botonNoticias.setOnClickListener {
            navigateToFragment(NoticiasRecycler())
        }
        binding.botonAjuste.setOnClickListener {
            navigateToFragment(AjustesFragment())
        }
        binding.botonMisDatos.setOnClickListener {
            navigateToFragment(MisDatosFragment())
        }
        binding.botonCalculadora.setOnClickListener {
            navigateToFragment(CalculadoraFragment())
        }
    }

    private fun navigateToFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun <T> navigateToActivity(activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }

    private fun logoutUser() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun displayWelcomeMessage() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email
        val username = email?.substringBefore("@") ?: "Usuario"
        binding.Bienvenido.text = "Bienvenido, $username!"
    }
}