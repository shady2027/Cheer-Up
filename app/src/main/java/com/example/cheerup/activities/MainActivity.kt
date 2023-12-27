package com.example.cheerup.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.cheerup.QuotesFragment
import com.example.cheerup.R
import com.example.cheerup.adapters.DataPassListener
import com.example.cheerup.adapters.QuotesPagerAdapter
import com.example.cheerup.database.AppDatabase
import com.example.cheerup.database.UserRepository
import com.example.cheerup.database.UserViewmodel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var button : Button
    private var quoteId: Long = 1
    private var selectedCategories: List<String> = emptyList()
    private lateinit var userViewModel: UserViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val intent: Intent = getIntent()
            quoteId = intent.getLongExtra("Current User Id", 1)
            Log.d("check", "Current user id is $quoteId")
            val quotesFragment = QuotesFragment.newInstance(quoteId)
            val userRepository = UserRepository(AppDatabase.getInstance(applicationContext).userDao(),)
            selectedCategories = userRepository.getSelectedCategories(quoteId) ?: emptyList()
            Log.d("catgs","$selectedCategories")

            val viewPager: ViewPager2 = findViewById(R.id.viewPager)
            val adapter = QuotesPagerAdapter(this@MainActivity, quoteId, selectedCategories)
            Log.d("display","$selectedCategories")
            viewPager.adapter = adapter
        }


    }

}