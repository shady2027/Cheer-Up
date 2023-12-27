package com.example.cheerup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.cheerup.activities.SettingScreen
import com.example.cheerup.adapters.DataPassListener
import com.example.cheerup.adapters.QuotesAdapter
import com.example.cheerup.database.AppDatabase
import com.example.cheerup.database.QuoteRepository
import com.example.cheerup.database.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuotesFragment : Fragment() {
    private lateinit var favIcon: ImageView
    private lateinit var shareIcon: ImageView
    private lateinit var settingIcon: ImageView
    private lateinit var backIcon: ImageView
    private var dataPassListener: DataPassListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    // to accept arguments
    companion object {
        fun newInstance(userId: Long): QuotesFragment {
            val fragment = QuotesFragment()
            val args = Bundle()
            args.putLong("userId", userId)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_quotes,container,false)
        favIcon = view.findViewById(R.id.favIcon)
        shareIcon = view.findViewById(R.id.shareIcon)
        backIcon = view.findViewById(R.id.backIcon)
        settingIcon = view.findViewById(R.id.settingIcon)
        favIcon.setOnClickListener{ onFavoriteClicked()}
        shareIcon.setOnClickListener{ onShareClicked()}
        backIcon.setOnClickListener{ onBackClicked()}
        settingIcon.setOnClickListener{ onSettingClicked()}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userRepository = UserRepository(
            AppDatabase.getInstance(requireContext()).userDao(),
        )
        val quotesRepository = QuoteRepository(
            AppDatabase.getInstance(requireContext()).userDao(),
            AppDatabase.getInstance(requireContext()).quoteDao()
        )
        val userId = arguments?.getLong("userId", 1) ?: 1

        lifecycleScope.launch {
            // Fetch selected categories
            val selectedCategories = userRepository.getSelectedCategories(userId)

            Log.d("select","$selectedCategories")
//
//            var quotes : List<String> = emptyList()
//            for(cats in selectedCategories){
//                quotes = quotesRepository.getQuotesByCategory(cats)
//                Log.d("result","$quotes")
//            }

//
//            val quotes = selectedCategories.flatMap { category ->
//               Log.d("Category", "Fetching quotes for category: $category")
//                quotesRepository.getQuotesByCategory(category)
//            }
//            Log.d("Category", "Quotes for category $quotes")

            val quotes = mutableListOf<String>()

            // Iterate over each category
            for (category in selectedCategories) {
                Log.d("Category", "Fetching quotes for category: $category")

                // Fetch quotes for the current category
                val quotesForCategory = quotesRepository.getQuotesByCategory(category)
                Log.d("Category", "Quotes for $category: $quotesForCategory")



                // Add fetched quotes to the main quotes list
                quotes.addAll(quotesForCategory)
               Log.d("Category", "Quotes for categories $selectedCategories: $quotes")
            }
//
//           // Log the accumulated quotes
//            Log.d("Category", "Quotes for categories $selectedCategories: $quotes")



            Log.d("passed","$quotes")
            // to pass the userId and list of selectedCategories to the QuotesPagerAdapter
            val bundle = Bundle().apply {
                putLong("userId", userId)
                putStringArrayList("selectedCategories", ArrayList(selectedCategories))
            }
            val quotesFragment = QuotesFragment().apply {
                arguments = bundle
            }
            quotes.shuffled()
            Log.d("now","$quotes")

            val quotesAdapter = QuotesAdapter(quotes)
            val recyclerView: RecyclerView = view.findViewById(R.id.quotesRV)
            recyclerView.adapter = quotesAdapter
        }

    }

    private fun onFavoriteClicked(){

    }

    private fun onShareClicked(){

    }

    private fun onSettingClicked(){
        val intent = Intent(requireContext(),SettingScreen::class.java)
        startActivity(intent)
    }

    private fun onBackClicked(){
        requireActivity().finish()
    }
}