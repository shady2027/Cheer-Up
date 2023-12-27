package com.example.cheerup.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val category : String,
    val text : String
)