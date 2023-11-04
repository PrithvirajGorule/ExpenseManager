package com.example.expensemanager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        supportActionBar?.hide()

        val sharedPrefs=getSharedPreferences("my_prefs",Context.MODE_PRIVATE)
        val feedbackText=findViewById<EditText>(R.id.rating_feedback).text.toString()

        val editor=sharedPrefs.edit()
        editor.putString("feedback",feedbackText)
        editor.apply()

    }
}