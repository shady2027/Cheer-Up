package com.example.cheerup.activities

import com.example.cheerup.dao.GoalDao
import com.example.cheerup.database.GoalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// GoalModule.kt
@Module
@InstallIn(SingletonComponent::class)
object GoalModule {

    @Provides
    @Singleton
    fun provideGoalRepository(goalDao: GoalDao): GoalRepository {
        return GoalRepository(goalDao)
    }
}
