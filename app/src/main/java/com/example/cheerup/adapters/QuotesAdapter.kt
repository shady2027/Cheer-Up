package com.example.cheerup.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cheerup.R

class QuotesAdapter(private val quotes: List<String>) : RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quoteText: TextView = itemView.findViewById(R.id.quoteText)
        val item : ConstraintLayout = itemView.findViewById(R.id.quote_bg)
        // Add other views if needed for different properties of Quote
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(itemView)
    }

    private val gradientResources = intArrayOf(
        R.drawable.gradient_1, R.drawable.gradient_2, R.drawable.gradient_4,
        R.drawable.gradient_4, R.drawable.gradient_3, R.drawable.gradient_1,
        R.drawable.gradient_3, R.drawable.gradient_1, R.drawable.gradient_2
    )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val currentQuote = quotes[position]
        Log.d("QuotesAdapter", "Binding quote: $currentQuote")

        // Set quote text
        holder.quoteText.text = currentQuote.toString()
        holder.quoteText.setTextColor(Color.BLACK)
        val gradientResourceId = gradientResources.getOrNull(position) ?: R.drawable.gradient_2
        holder.itemView.setBackgroundResource(gradientResourceId)
        // Set other properties based on your Quote model
        // Example: holder.itemView.setBackgroundColor(currentQuote.backgroundColor)
        // Example: holder.quoteText.setTextColor(currentQuote.textColor)
    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}