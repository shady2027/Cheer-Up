package com.example.cheerup.database

import androidx.lifecycle.LiveData
import com.example.cheerup.dao.GoalDao
import com.example.cheerup.entities.Goal
import javax.inject.Inject

class GoalRepository  @Inject constructor(private val goalDao: GoalDao) {
    suspend fun insertGoal(goal: Goal): Long{
        return goalDao.insertGoal(goal)
    }

    suspend fun updateGoal(goal: Goal) {
        goalDao.updateGoal(goal)
    }

    suspend fun getFirstGoal(userId: Long): String? {
        return goalDao.getFirstGoal(userId)
    }

    suspend fun getSecondGoal(userId: Long): String? {
        return goalDao.getSecondGoal(userId)
    }
    suspend fun getThirdGoal(userId: Long): String? {
        return goalDao.getThirdGoal(userId)
    }

    suspend fun getReminderTime(userId: Long): Long? {
        return goalDao.getReminderTime(userId)
    }

    suspend fun updateReminderTime(userId: Long, reminderTime: Long) {
        goalDao.updateReminderTime(userId, reminderTime)
    }
}