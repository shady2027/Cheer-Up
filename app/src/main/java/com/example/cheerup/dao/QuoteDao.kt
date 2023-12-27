package com.example.cheerup.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cheerup.entities.Quote

@Dao
interface QuoteDao {
    @Query("Select text FROM quotes WHERE category = :category")
    suspend fun getQuotesByCategory(category: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertQuotes(quotes : List<Quote>)
}