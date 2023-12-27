package com.example.cheerup.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "goals",
    indices = [Index(value = ["userId"], unique = true)])
class Goal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId : Long = 0L,

    @ColumnInfo(name = "first_goal")
    val firstGoal : String? = null,

    @ColumnInfo(name = "second_goal")
    val secondGoal : String? = null,

    @ColumnInfo(name = "third_goal")
    val thirdGoal : String? = null,

    @ColumnInfo(name = "reminder_time")
    val reminderTime : Long = 0L
)