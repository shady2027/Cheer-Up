package com.example.cheerup.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cheerup.dao.GoalDao
import com.example.cheerup.dao.QuoteDao
import com.example.cheerup.dao.UserDao
import com.example.cheerup.entities.Quote
import com.example.cheerup.entities.User
import com.example.cheerup.entities.Goal
import com.example.cheerup.models.Converters

@Database(entities = [Quote::class, User::class, Goal::class],
    version = 3,
    exportSchema = false
//    autoMigrations = [
//        AutoMigration(from = 2, to = 3)
//    ]
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao() : QuoteDao
    abstract fun userDao() : UserDao
    abstract fun goalDao() : GoalDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}