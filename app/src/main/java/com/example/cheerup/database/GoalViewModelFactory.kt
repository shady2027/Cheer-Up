package com.example.cheerup.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class GoalViewModelFactory @Inject constructor(private val repository: GoalRepository) :
    ViewModelProvider.Factory {

     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GoalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}