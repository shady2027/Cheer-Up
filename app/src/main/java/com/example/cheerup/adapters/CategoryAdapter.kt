package com.example.cheerup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cheerup.R
import com.example.cheerup.models.Category

class CategoryAdapter(private val categories : List<Category>,
                      private val onCategoryClick: (Category) -> Unit) :
        RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
        inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val cardView: CardView = itemView.findViewById(R.id.card)
                val checkIcon: ImageView = itemView.findViewById(R.id.item_check)
                val titleTextView: TextView = itemView.findViewById(R.id.title)
        }

        private val gradientResources = intArrayOf(
                R.drawable.gradient_1, R.drawable.gradient_2, R.drawable.gradient_4,
                R.drawable.gradient_4, R.drawable.gradient_3, R.drawable.gradient_1,
                R.drawable.gradient_3, R.drawable.gradient_1, R.drawable.gradient_2
        )
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
                return CategoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
                val category = categories[position]
                holder.titleTextView.text = category.title
                holder.cardView.setOnClickListener{
                        toggleSelection(holder,category)
                }
                val gradientResourceId = gradientResources.getOrNull(position) ?: R.drawable.gradient_1
                holder.cardView.setBackgroundResource(gradientResourceId)
              //  holder.cardView.setBackgroundColor(R.drawable.gradient_1)
                if (category.isSelected) {
                        holder.checkIcon.visibility = View.VISIBLE
                } else {
                        holder.checkIcon.visibility = View.GONE
                }
        }
        private fun toggleSelection(holder: CategoryViewHolder, category: Category) {
                category.isSelected = !category.isSelected
                if (category.isSelected) {
                        holder.checkIcon.visibility = View.VISIBLE
                } else {
                        holder.checkIcon.visibility = View.GONE
                }
                onCategoryClick(category)
        }

        override fun getItemCount(): Int {
                return categories.size
        }
}