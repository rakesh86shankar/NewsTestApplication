package com.newsapi.newsapp.newsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_acitivity)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

}