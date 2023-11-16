package com.example.smoku

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.Firebase
import com.google.firebase.database.database
import java.time.LocalDate

class WriteCommunityActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
            if(message.isEmpty()){
                Toast.makeText(baseContext, "글을 작성해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                if(getTitle.isEmpty()){
                    Toast.makeText(baseContext, "제목을 작성해주세요", Toast.LENGTH_SHORT).show()
                }
                else{
                    val currentDate = LocalDate.now()
                    val month = currentDate.month.value // 월 (1부터 12까지)
                    val day = currentDate.dayOfMonth
                    val communityModel = CommunityRVModel(getTitle,message,month.toString()+"/"+day.toString())
                    myRef.push().setValue(communityModel)
                    finish()
                }
            }
        }
    }
}