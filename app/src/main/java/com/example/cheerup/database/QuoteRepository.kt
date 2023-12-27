package com.example.cheerup.database

import com.example.cheerup.dao.QuoteDao
import com.example.cheerup.dao.UserDao
import com.example.cheerup.entities.Quote

class QuoteRepository(
    private val userDao: UserDao,
    private val quoteDao: QuoteDao
) {
    suspend fun getSelectedCategories(userId: Long): List<String> {
       return userDao.getSelectedCategories(userId)
    }

    suspend fun getQuotesByCategory(category: String): List<String> {
        return quoteDao.getQuotesByCategory(category)
    }

}