package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val find_smokingZone = findViewById<View>(R.id.find_view)
        val list_smokingZone = findViewById<View>(R.id.list_view)
        val community = findViewById<View>(R.id.community_view)

        find_smokingZone.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        list_smokingZone.setOnClickListener {
            val intent = Intent(this, SmokingZoneListActivity::class.java)
            startActivity(intent)
        }

        community.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}