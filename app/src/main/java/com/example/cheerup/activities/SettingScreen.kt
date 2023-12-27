package com.example.cheerup.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.cheerup.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class SettingScreen : AppCompatActivity() {
    private lateinit var text: TextView
    private lateinit var favoriteQuotes : CardView
    private lateinit var setGoals : CardView
    private lateinit var setReminder : CardView
    private lateinit var rate : CardView
    private lateinit var share : CardView
    private lateinit var about : CardView
    private lateinit var back : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_screen)
        text = findViewById(R.id.username)
        back = findViewById(R.id.back_icon)

        val intent: Intent = getIntent()
        val name : String? = intent.getStringExtra("TheName").toString()
        text.setText("$name")

        favoriteQuotes = findViewById(R.id.Favorite)
        setGoals = findViewById(R.id.setGoals)
        setReminder = findViewById(R.id.setReminder)
        rate = findViewById(R.id.rateUs)
        share = findViewById(R.id.share)
        about = findViewById(R.id.aboutUs)
        back = findViewById(R.id.back_icon)

        favoriteQuotes.setOnClickListener{
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
        setGoals.setOnClickListener{
            startActivity(Intent(this,SetGoals::class.java))
            Log.d("Start","Activity has begun")
            finish()
        }
        setReminder.setOnClickListener{
            startActivity(Intent(this, setReminder::class.java))
        }
        rate.setOnClickListener{
            rateApp()
        }
        share.setOnClickListener{
            shareApp()
        }
        about.setOnClickListener{
            aboutUs()
        }
        back.setOnClickListener{
            moveToMainActivity()
        }
    }
    fun moveToMainActivity(){
        super.onBackPressed()
    }
    private fun rateApp(){
        val appPackageName = packageName
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (e: android.content.ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }

    }
    private fun shareApp(){
        val appPackageName = packageName
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Check out this awesome app on the Play Store: https://play.google.com/store/apps/details?id=$appPackageName"
        )
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
    private fun aboutUs(){
        val bottomSheetDialog =  BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_about_us,null)

        val cancelButton = view.findViewById<ImageView>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}