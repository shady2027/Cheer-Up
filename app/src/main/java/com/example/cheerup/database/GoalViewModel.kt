package com.example.cheerup.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheerup.entities.Goal
import kotlinx.coroutines.launch
import javax.inject.Inject

class GoalViewModel @Inject constructor(private val repository: GoalRepository) : ViewModel() {

    private val _firstGoal = MutableLiveData<String?>()
    val firstGoal: LiveData<String?> get() = _firstGoal

    private val _secondGoal = MutableLiveData<String?>()
    val secondGoal: LiveData<String?> get() = _secondGoal

    private val _thirdGoal = MutableLiveData<String?>()
    val thirdGoal: LiveData<String?> get() = _thirdGoal

    private val _reminderTime = MutableLiveData<Long?>()
    val reminderTime: LiveData<Long?> get() = _reminderTime

    fun insertGoal(goal: Goal) {
        viewModelScope.launch {
            repository.insertGoal(goal)
        }
    }

    fun updateGoal(goal: Goal) {
        viewModelScope.launch {
            repository.updateGoal(goal)
        }
    }

    fun loadGoals(userId: Long) {
        viewModelScope.launch {
            _firstGoal.value = repository.getFirstGoal(userId)
            _secondGoal.value = repository.getSecondGoal(userId)
            _thirdGoal.value = repository.getThirdGoal(userId)
        }
    }

    fun getReminderTime(userId: Long) {
        viewModelScope.launch {
            _reminderTime.value = repository.getReminderTime(userId)
        }
    }

    fun updateReminderTime(userId: Long, reminderTime: Long) {
        viewModelScope.launch {
            repository.updateReminderTime(userId, reminderTime)
        }
    }
}