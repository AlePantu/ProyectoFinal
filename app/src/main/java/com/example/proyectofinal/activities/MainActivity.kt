package com.example.proyectofinal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.proyectofinal.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_login) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.loginFragment -> hideBottomBar()
                R.id.beachFragment -> hideBottomBar()
                else -> showBottomBar()
            }

        }



    }

    fun showBottomBar(){
        val navBar: BottomNavigationView = findViewById(R.id.bottom_bar)
        navBar.visibility = View.VISIBLE
    }

    fun hideBottomBar(){
        val navBar: BottomNavigationView = findViewById(R.id.bottom_bar)
        navBar.visibility = View.GONE
    }
}