package com.example.smoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SmokingZoneList_Clicked_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smoking_zone_list_clicked)

        val getIntent = getIntent()
        val title = getIntent.getStringExtra("smokingZoneTitle")
        val imageUrl = getIntent.getIntExtra("smokingZoneImage",0)

        findViewById<TextView>(R.id.smokingZoneTitle).setText(title)
        findViewById<ImageView>(R.id.smokingZoneImage).setImageResource(imageUrl)
    }
}