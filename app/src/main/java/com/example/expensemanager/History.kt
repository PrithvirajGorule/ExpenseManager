package com.example.expensemanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.common.net.InternetDomainName

class History : AppCompatActivity() {

    private var userLimit:Long=0

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_history)

       val buttonSave=findViewById<Button>(R.id.buttonSave)
       var editTextExpenseLimit=findViewById<EditText>(R.id.editTextExpenseLimit)

       buttonSave.setOnClickListener {
           val limit=editTextExpenseLimit.text.toString().toLongOrNull()
           if(limit!=null){
               userLimit=limit
               Toast.makeText(this,"expense limit saved",Toast.LENGTH_SHORT).show()
               checkExpenseLimit()
           }
           else{
               Toast.makeText(this,"Invalid expense",Toast.LENGTH_SHORT).show()
           }
       }
   }

    private fun checkExpenseLimit() {
        if(userLimit<DataManger.getExpense())
        {
            val intent=Intent(this@History,LimitExceed::class.java)
            startActivity(intent)
        }

    }
}