package com.example.cheerup.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewmodel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}