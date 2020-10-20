package com.koleychik.nasaapi.ui.screens.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.koleychik.nasaapi.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host)
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.mainMenuFragment -> {
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}