package com.example.cheerup.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheerup.entities.User
import kotlinx.coroutines.launch

class UserViewmodel(private val userRepository: UserRepository) : ViewModel() {

    // LiveData for observing user data changes
    val liveUserData: LiveData<List<User>> = userRepository.getLiveUserData

    // Function to insert or update user data
//    fun insertUser(user: User) {
//        viewModelScope.launch {
//            userRepository.insertUser(user)
//        }
//    }
    suspend fun insertUser(user: User): Long {
        return userRepository.insertUser(user)
    }

    // Function to get user by ID
    fun getUser(userId: Long) {
        viewModelScope.launch {
            userRepository.getUser(userId)
        }
    }

    //function to get the selected user categories using their userId
    fun getSelectedCategories(userId: Long){
        viewModelScope.launch {
            userRepository.getSelectedCategories(userId)
        }
    }

    // Function to update user data
    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }
}