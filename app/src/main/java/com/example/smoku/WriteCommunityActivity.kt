package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.database.database

class WriteCommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_community)

        val opinion = findViewById<EditText>(R.id.opinionArea)
        var title = findViewById<EditText>(R.id.title)

        val comBtn = findViewById<Button>(R.id.completeBtn)
        val database = Firebase.database
        val myRef = database.getReference("community")




        comBtn.setOnClickListener {
            val message = opinion.text.toString()
            val getTitle = title.text.toString()
            val communityModel = CommunityRVModel(getTitle,message,"18:20")
            myRef.push().setValue(communityModel)
            finish()
        }
    }
}