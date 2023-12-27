package com.example.cheerup.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cheerup.models.Converters

@Entity(tableName = "users",
    indices = [Index(value = ["userId"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId : Long,

    @ColumnInfo(name = "userName")
    val userName : String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "favoriteQuotes")
    val favoriteQuotes : List<Long>,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "selectedCategories")
    val selectedCategories : List<String>
)