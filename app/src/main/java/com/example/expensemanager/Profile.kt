package com.example.expensemanager

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text

class Profile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        val currentUser=auth.currentUser

        if(currentUser!=null)
        {
            val uid=currentUser.uid
            val userRef=FirebaseDatabase.getInstance().getReference("user").child(uid)
            userRef.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name=snapshot.child("name").getValue(String::class.java)

                    val nameTextView=findViewById<TextView>(R.id.name_textview)
                    nameTextView.text=name
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "User doesn't exist")
                }
            })
        }
        val tvUsername=findViewById<TextView>(R.id.email_textview)
        val tvName=findViewById<TextView>(R.id.name_textview)
        tvUsername.text=currentUser?.email
        tvName.text=currentUser?.displayName

    }
}