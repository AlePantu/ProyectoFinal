package com.example.proyectofinal.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.proyectofinal.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_login) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.loginFragment -> hideBottomBar()
                R.id.beachFragment -> hideBottomBar()
                R.id.recuMailFragment -> hideBottomBar()
                R.id.editProfileFragment -> hideBottomBar()
                else -> showBottomBar()
            }

        }



    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "dark_mode"){
            val prefs = sharedPreferences?.getString(key, "1")

            when(prefs?.toInt()){
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    private fun showBottomBar(){
        val navBar: BottomNavigationView = findViewById(R.id.bottom_bar)
        navBar.visibility = View.VISIBLE
    }

    private fun hideBottomBar(){
        val navBar: BottomNavigationView = findViewById(R.id.bottom_bar)
        navBar.visibility = View.GONE
    }
}


