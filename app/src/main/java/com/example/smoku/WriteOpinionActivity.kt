package com.example.smoku

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.google.firebase.Firebase
import com.google.firebase.database.database
import java.time.LocalDate

class WriteOpinionActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_opinion)

        val opinion = findViewById<EditText>(R.id.opinionArea)
        val comBtn = findViewById<Button>(R.id.completeBtn)

        val database = Firebase.database
        val myRef = database.getReference("opinion")
        val opinion_model = ArrayList<OpinionRVModel>()
        comBtn.setOnClickListener {
            val onlyDate: LocalDate = LocalDate.now()
            opinion_model.add(OpinionRVModel(1,opinion.text.toString(),3))
            myRef.push().setValue(opinion_model)
            finish()
        }




    }
}