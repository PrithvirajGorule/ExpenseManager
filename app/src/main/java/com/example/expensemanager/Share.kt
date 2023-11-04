package com.example.expensemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Share : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        supportActionBar?.hide()
    }
}