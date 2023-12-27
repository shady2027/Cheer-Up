package com.example.cheerup.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cheerup.entities.User

@Dao
interface UserDao {
    @Query("Select * FROM users WHERE userId = :userId")
    suspend fun getUser(userId : Long) : User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User) : Long

    @Update
    suspend fun updateUser(user: User)

    @Query("Select selectedCategories FROM users WHERE userId = :userId")
    suspend fun getSelectedCategories(userId: Long) : List<String>

    @Query("Select * FROM users ORDER BY userId ASC")
    fun getLiveUserData():LiveData<List<User>>
}