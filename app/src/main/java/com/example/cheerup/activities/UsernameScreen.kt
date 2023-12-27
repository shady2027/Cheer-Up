package com.example.cheerup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.cheerup.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UsernameScreen : AppCompatActivity() {
    private lateinit var name : EditText
    private lateinit var fab : FloatingActionButton
    private lateinit var userName : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username_screen)
        name = findViewById(R.id.etUserName);

        fab = findViewById(R.id.fab)
        fab.setOnClickListener{
            userName = name.text.toString()
            if(check()) {
                val intent = Intent(this, CategoryScreen::class.java)
                intent.putExtra("Name",userName)
//                val i = Intent(this, SettingScreen::class.java)
//                i.putExtra("Name",userName)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun check():Boolean{
        if(userName.length >20){
            Toast.makeText(this,"Name is too long",Toast.LENGTH_LONG).show()
            return false
        }
        else if (userName.isEmpty()){
            Toast.makeText(this,"Please enter a name",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}