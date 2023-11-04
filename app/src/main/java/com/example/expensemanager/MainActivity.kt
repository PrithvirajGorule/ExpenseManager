package com.example.expensemanager

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Suppress("DEPRECATION", "OverrideDeprecatedMigration", "OverrideDeprecatedMigration",
    "OverrideDeprecatedMigration"
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val profileIcon = findViewById<ImageView>(R.id.profile_home)
        profileIcon.setOnClickListener {
            val intent = Intent(this@MainActivity, Profile::class.java)

            startActivity(intent)
        }
        val ratingIcon=findViewById<ImageView>(R.id.rating_home)
        ratingIcon.setOnClickListener {
            val intent=Intent(this@MainActivity,Rating::class.java)

            startActivity(intent)
        }
        val shareIcon=findViewById<ImageView>(R.id.share_home)
        shareIcon.setOnClickListener {
            val intent=Intent(this@MainActivity,Share::class.java)

            startActivity(intent)
        }
        val billIcon=findViewById<ImageView>(R.id.bills_home)
        billIcon.setOnClickListener {
            val intent=Intent(this@MainActivity,Bills::class.java)

            startActivity(intent)
        }
        val calandarIcon=findViewById<ImageView>(R.id.calendar_home)
        calandarIcon.setOnClickListener{
            val intent=Intent(this@MainActivity,Calender::class.java)

            startActivity(intent)
        }
        val LimitsIcon=findViewById<ImageView>(R.id.limits_home)
        LimitsIcon.setOnClickListener {
            val intent=Intent(this@MainActivity,SetLimits::class.java)
            startActivity(intent)
        }
        val HistoryIcon=findViewById<ImageView>(R.id.history_home)
        HistoryIcon.setOnClickListener {
            val intent=Intent(this@MainActivity,History::class.java)
            startActivity(intent)
        }
        val SavingIcon=findViewById<ImageView>(R.id.savings)
        SavingIcon.setOnClickListener{
            val intent=Intent(this@MainActivity,Savings::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        finish()
        startActivity(intent)
    }
}