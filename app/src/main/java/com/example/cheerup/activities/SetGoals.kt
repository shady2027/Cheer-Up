package com.example.cheerup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cheerup.R
import com.example.cheerup.database.AppDatabase
import com.example.cheerup.database.GoalRepository
import com.example.cheerup.database.GoalViewModel
import com.example.cheerup.database.GoalViewModelFactory
import com.example.cheerup.database.UserRepository
import com.example.cheerup.database.UserViewmodel
import com.example.cheerup.database.ViewModelFactory
import com.example.cheerup.entities.Goal
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar


@AndroidEntryPoint
class SetGoals : AppCompatActivity() {
    private lateinit var backIcon : ImageView
    private lateinit var saveButton: Button
    private lateinit var goal1 : TextInputEditText
    private lateinit var goal2 : TextInputEditText
    private lateinit var goal3 : TextInputEditText
    private lateinit var timePicker : TimePicker
    private var goalExists : Boolean = false
    private lateinit var goalViewModel : GoalViewModel
  //  private lateinit var goalViewModel: GoalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_goals)

        goal1 = findViewById(R.id.etFirstGoal)
        goal2 = findViewById(R.id.etSecondGoal)
        goal3 = findViewById(R.id.etThirdGoal)
        timePicker = findViewById(R.id.setTime)

        val goalRepository = GoalRepository(AppDatabase.getInstance(applicationContext).goalDao())
        val goalViewModelFactory = GoalViewModelFactory(goalRepository)
        goalViewModel = ViewModelProvider(this, goalViewModelFactory).get(GoalViewModel::class.java)


        if (!goalExists) {
            goal1.setText(goalViewModel.firstGoal.value.toString())
            goal2.setText(goalViewModel.secondGoal.value.toString())
            goal3.setText(goalViewModel.thirdGoal.value.toString())
        }

        backIcon = findViewById(R.id.back_icon)
        backIcon.setOnClickListener{
            moveToHomeScreen()
        }
        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener{
            if (goal1.text.toString().isBlank() || goal2.text.toString().isBlank()
                || goal3.text.toString().isBlank()) {
                Toast.makeText(this,"No field should be empty",Toast.LENGTH_LONG).show()
            }
            else {
                addGoals()
                val intent = Intent(this, SettingScreen::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun addGoals(){
        lifecycleScope.launch {
            val firstGoal = goal1.text.toString()
            val secondGoal = goal2.text.toString()
            val thirdGoal = goal3.text.toString()
            val hour = timePicker.hour
            val minute = timePicker.minute

            val calendar: Calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR, hour)
            calendar.set(Calendar.MINUTE, minute)

            // Create a Goal object
            val goal = Goal(
                firstGoal = firstGoal,
                secondGoal = secondGoal,
                thirdGoal = thirdGoal,
                reminderTime = calendar.timeInMillis
            )

            if (!goalExists) {
                // Insert the goal into the database and get the user ID
                val userId = goalViewModel.insertGoal(goal)
                Log.d("userId is", "{$userId}")
                goalExists = true
            } else {
                // Update the existing goals
                goalViewModel.updateGoal(goal)
            }
        }
    }


    fun moveToHomeScreen(){
        super.onBackPressed()
        finish()
    }
}