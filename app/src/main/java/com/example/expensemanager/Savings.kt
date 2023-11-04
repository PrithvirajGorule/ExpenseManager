package com.example.expensemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Savings : AppCompatActivity() {
    //private var income:Long=0
    //private var expense:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savings)

        val amount=DataManger.getIncome()-DataManger.getExpense()
        val savings=findViewById<TextView>(R.id.amount1)
        savings.text= amount.toString()

    }
}

