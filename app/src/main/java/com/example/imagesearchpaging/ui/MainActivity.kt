package com.example.imagesearchpaging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.imagesearchpaging.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var appBarConfiguration: AppBarConfiguration? = null
    private var navController: NavController? = null
    lateinit var viewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adding back button in appBar
        appBarConfiguration = AppBarConfiguration.Builder(R.id.galleryFragment).build()
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController!!, appBarConfiguration!!)
    }

    // Adding back button in appBar
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController!!,
            appBarConfiguration!!
        ) || super.onSupportNavigateUp()
    }
}