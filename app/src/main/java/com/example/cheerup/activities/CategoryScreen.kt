package com.example.cheerup.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.cheerup.R
import com.example.cheerup.adapters.CategoryAdapter
import com.example.cheerup.dao.UserDao
import com.example.cheerup.database.AppDatabase
import com.example.cheerup.database.UserRepository
import com.example.cheerup.database.UserViewmodel
import com.example.cheerup.database.ViewModelFactory
import com.example.cheerup.entities.User
import com.example.cheerup.models.Category
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class CategoryScreen : AppCompatActivity() {
    private lateinit var text : TextView
    private lateinit var fab : FloatingActionButton
    private var categorySelected:Boolean = false
    private var userName : String = ""
    val selectedCategories = mutableListOf<String>()
    private var addedUserId: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_screen)
        text = findViewById(R.id.textView2)

        fab = findViewById(R.id.fab_home)

        fab.setOnClickListener{
            lifecycleScope.launch {
                createUser()
            }
        }


        val intent:Intent = getIntent()
        val name : String? = intent.getStringExtra("Name")
        userName = intent.getStringExtra("Name").toString()
        text.setText("Hello, $name")


        val recyclerView : RecyclerView = findViewById(R.id.categoryRV)
        val cards = listOf(
           Category("Resilience",false),
            Category("Persevere",false),
            Category("Hope",false),
            Category("Stress & Anxiety",false),
            Category("Work Inspiration",false),
         Category("Happiness",false),
         Category("Positive Thoughts",false),
         Category("Gratitude",false),
         Category("Peace",false)
        )
        val myAdapter = CategoryAdapter(cards){
            selectedCategory ->
            categorySelected = true;
            if (!selectedCategories.contains(selectedCategory.title.toString())) {
                selectedCategories.add(selectedCategory.title.toString())
                Log.d("Cats", "Category $selectedCategories")
            } else {
                // Category already selected, you might want to handle this case
                Log.d("CategoryScreen", "Category $selectedCategory already selected")
            }
        }
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = GridLayoutManager(this,3)
    }

    suspend fun createUser(){
        val newUser = User(
            userId = 0,  // Assuming it's auto-generated
            userName = userName,
            favoriteQuotes = emptyList(),  // Initially empty
            selectedCategories = selectedCategories
        )
        // Log.d("CategoryScreen", "New User: $newUser")

        val userRepository = UserRepository(AppDatabase.getInstance(applicationContext).userDao())
        val viewModelFactory = ViewModelFactory(userRepository)
        val userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewmodel::class.java)

        Log.d("BeforeInsertion", "New User: $newUser")
        lifecycleScope.launch {
            // delay(1000)
            // Log.d("CategoryScreen", "Inserting User: $newUser")
            addedUserId = userViewModel.insertUser(newUser)
            Log.d("AfterInsertion", "Inserted User: $newUser and user id is $addedUserId")
            moveToNewScreen()
        }
        //  return addedUserId
    }
    fun moveToNewScreen(){
        if(categorySelected){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Current User Id", addedUserId)
            Log.d("value sent","$addedUserId")
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(this,"Please select some goals",Toast.LENGTH_SHORT).show()
        }
    }
}