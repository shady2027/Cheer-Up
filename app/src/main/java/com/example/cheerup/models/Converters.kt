package com.example.cheerup.models

import androidx.room.TypeConverter
import androidx.room.TypeConverters

public class Converters {
    @TypeConverter
    fun fromLongList(value: List<Long>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toLongList(value: String?): List<Long>? {
        return value?.split(",")?.map { it.toLong() }
    }
    @TypeConverter
    fun fromStringList(value:List<String>?): String?{
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String?) : List<String>?{
        return value?.split(",")
    }
}