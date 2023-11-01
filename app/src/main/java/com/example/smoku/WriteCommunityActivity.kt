package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.database.database

class WriteCommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_community)

        val opinion = findViewById<EditText>(R.id.opinionArea)
        val comBtn = findViewById<Button>(R.id.completeBtn)

        val database = Firebase.database
        val myRef = database.getReference("community")



        comBtn.setOnClickListener {
            val message = opinion.text.toString()
            myRef.setValue(message)
            finish()
        }
    }
}