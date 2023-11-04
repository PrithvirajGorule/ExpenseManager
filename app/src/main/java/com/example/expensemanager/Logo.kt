package com.example.expensemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Logo : AppCompatActivity() {
    private val DELAY_TIME=2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)
        supportActionBar?.hide()

        GlobalScope.launch {
            delay(DELAY_TIME)

            val intent= Intent(this@Logo,Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}