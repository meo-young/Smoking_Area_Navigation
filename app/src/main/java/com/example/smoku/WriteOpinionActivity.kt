package com.example.smoku

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.Firebase
import com.google.firebase.database.database
import java.time.LocalDate

class WriteOpinionActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_opinion)

        val getIntent = getIntent()
        val title = getIntent.getStringExtra("smokingZoneTitle")


        val opinion = findViewById<EditText>(R.id.opinionArea)
        val comBtn = findViewById<Button>(R.id.completeBtn)

        val database = Firebase.database
        val myRef = database.getReference(title.toString())


        comBtn.setOnClickListener {
            if(opinion.text.toString().isEmpty()){
                Toast.makeText(baseContext, "글을 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                val currentDate = LocalDate.now()
                val month = currentDate.month.value // 월 (1부터 12까지)
                val day = currentDate.dayOfMonth
                val opinion_model = OpinionRVModel(1,opinion.text.toString(),month.toString()+"/"+day.toString())
                myRef.push().setValue(opinion_model)
                finish()
            }
        }




    }
}