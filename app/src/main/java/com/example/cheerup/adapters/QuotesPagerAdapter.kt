package com.example.cheerup.adapters

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cheerup.QuotesFragment
import com.example.cheerup.database.AppDatabase
import com.example.cheerup.database.UserRepository
import com.example.cheerup.database.UserViewmodel
import com.example.cheerup.entities.Quote

class QuotesPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val userId: Long,
    private val selectedCategories: List<String>
) : FragmentStateAdapter(fragmentActivity) {

//    override fun getItemCount(): Int {
//        // Number of fragments to be created, which is equal to the number of selected categories
//        return selectedCategories.size
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        // Create a new instance of QuotesFragment for each selected category
//        //val userId =  // obtain the user id, you can pass it from activity or fetch it from a ViewModel
//        val userId = arguments?.getLong("userId", DEFAULT_USER_ID) ?: DEFAULT_USER_ID
//        return QuotesFragment.newInstance(userId)
//    }
override fun getItemCount(): Int {
    // Number of fragments to be created, which is equal to the number of selected categories
    return selectedCategories.size
}

    override fun createFragment(position: Int): Fragment {
        // Create a new instance of QuotesFragment for each selected category
        return QuotesFragment.newInstance(userId)
    }


}