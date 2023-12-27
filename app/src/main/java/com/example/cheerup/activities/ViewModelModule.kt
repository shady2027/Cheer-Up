package com.example.cheerup.activities

import com.example.cheerup.database.GoalRepository
import com.example.cheerup.database.GoalViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

// ViewModelModule.kt
@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideGoalViewModel(repository: GoalRepository): GoalViewModelFactory {
        return GoalViewModelFactory(repository)
    }
}
