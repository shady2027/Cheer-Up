package com.example.cheerup.database

import androidx.lifecycle.LiveData
import com.example.cheerup.dao.UserDao
import com.example.cheerup.entities.User

class UserRepository(private val userDao: UserDao) {

    val getLiveUserData : LiveData<List<User>> = userDao.getLiveUserData()

    suspend fun insertUser(user: User):Long {
        return userDao.insertUser(user)
    }

    suspend fun getUser(userId: Long): User? {
        return userDao.getUser(userId)
    }
    suspend fun getSelectedCategories(userId: Long): List<String>{
        return userDao.getSelectedCategories(userId)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}