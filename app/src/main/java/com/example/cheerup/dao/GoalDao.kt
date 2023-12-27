package com.example.cheerup.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cheerup.entities.Goal

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal : Goal): Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Query("SELECT first_goal FROM goals WHERE userId = :userId")
    suspend fun getFirstGoal(userId: Long): String?

    @Query("SELECT reminder_time FROM goals WHERE userId = :userId")
    suspend fun getReminderTime(userId: Long): Long?

    @Query("UPDATE goals SET reminder_time = :reminderTime WHERE userId = :userId")
    suspend fun updateReminderTime(userId: Long, reminderTime: Long)

}