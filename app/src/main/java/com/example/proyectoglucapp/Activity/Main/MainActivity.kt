package com.example.proyectoglucapp.Activity.Main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.proyectoglucapp.Activity.Login.LogInActivity
import com.example.proyectoglucapp.fragment.Ajustes.AjustesFragment
import com.example.proyectoglucapp.fragment.calculadora.CalculadoraFragment
import com.example.proyectoglucapp.fragment.misDatos.MisDatosFragment
import com.example.proyectoglucapp.fragment.Noticias.Recycler.NoticiasRecycler
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.fragment.tablas.TablasFragment
import com.example.proyectoglucapp.fragment.video.VideoFragment
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

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_mis_datos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MisDatosFragment())
                        .commit()
                }
                R.id.nav_ajustes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AjustesFragment())
                        .commit()
                }
                R.id.nav_tablas -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, TablasFragment::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_video -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, VideoFragment::class.java)
                    startActivity(intent)
                    finish()
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

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NoticiasRecycler())
            .commit()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email

        if (email != null) {
            val username = email.substringBefore("@")
            binding.Bienvenido.text = "Bienvenido, $username!"
        } else {
            binding.Bienvenido.text = "Bienvenido, Usuario!"
        }

        binding.botonNoticias.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NoticiasRecycler())
                .commit()
        }
        binding.botonAjuste.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AjustesFragment())
                .commit()
        }

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







