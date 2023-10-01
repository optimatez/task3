package com.example.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SavedDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_data)

        val textViewLastClickedItem = findViewById<TextView>(R.id.textViewLastClickedItem)

        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val lastClickedItem = sharedPreferences.getString("lastClickedCountry", "")

        textViewLastClickedItem.text = "Last Clicked Item: $lastClickedItem"
    }
}