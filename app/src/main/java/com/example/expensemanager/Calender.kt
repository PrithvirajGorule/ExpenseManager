package com.example.expensemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView

class Calender : AppCompatActivity() {

    private lateinit var textViewSelectedDate:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)
        supportActionBar?.hide()

        val calendarView=findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener{view,year,month,dayOfMonth ->
            val selectedDate="$dayOfMonth/${month+1}/$year"
            textViewSelectedDate.text=selectedDate
        }
    }

}