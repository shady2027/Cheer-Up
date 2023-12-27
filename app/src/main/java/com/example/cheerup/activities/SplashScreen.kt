package com.example.cheerup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cheerup.R
import com.example.cheerup.database.AppDatabase
import com.example.cheerup.database.DatabaseInitializer
import kotlinx.coroutines.runBlocking

class SplashScreen : AppCompatActivity() {
    private val SPLASH_DELAY : Long  = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val database = AppDatabase.getInstance(this)
        val quoteDao = database.quoteDao()
        val initializer = DatabaseInitializer(quoteDao)

        // Populate quotes
        runBlocking {
            initializer.populateQuotes()
        }

        Handler().postDelayed({
            startActivity(Intent(this,UsernameScreen::class.java))
            finish()
        },SPLASH_DELAY)

    }
}