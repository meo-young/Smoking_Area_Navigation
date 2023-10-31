package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WriteCommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_community)

        val intent = Intent(this, CommunityActivity::class.java)

        findViewById<Button>(R.id.completeBtn).setOnClickListener {
            finish()
        }
    }
}