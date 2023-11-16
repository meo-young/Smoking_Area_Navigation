package com.example.smoku

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import java.time.LocalDate

class CommunityViewActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_view)

        val database = Firebase.database
        val getIntent = getIntent()
        val title = getIntent.getStringExtra("communityTitle")
        val textArea = getIntent.getStringExtra("communityText")
        val passed_days = getIntent.getStringExtra("communityDays")
        val communityKey = getIntent.getStringExtra("communityKey")

        var items = mutableListOf<OpinionRVModel>()

        val rv = findViewById<RecyclerView>(R.id.communityView_rv)
        val rvAdapter = OpinionRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)

        val myRef = database.getReference(communityKey.toString())

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                items.clear()
                for (dataModel in snapshot.children){
                    items.add(dataModel.getValue(OpinionRVModel::class.java)!!)
                }
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val communityTitle = findViewById<TextView>(R.id.community_title)
        val communityTextArea = findViewById<TextView>(R.id.community_textArea)
        val communityPassedDays = findViewById<TextView>(R.id.passed_days)

        communityTitle.setText(title.toString())
        communityTextArea.setText(textArea.toString())
        communityPassedDays.setText(passed_days.toString())

        val comment = findViewById<EditText>(R.id.comment)

        val inputBtn = findViewById<ImageView>(R.id.inputBtn)

        inputBtn.setOnClickListener{
            val input = comment.text.toString()
            if(input.isEmpty()){
                Toast.makeText(baseContext, "글을 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                val currentDate = LocalDate.now()
                val month = currentDate.month.value // 월 (1부터 12까지)
                val day = currentDate.dayOfMonth
                val opinion_model = OpinionRVModel(1,input,month.toString()+"/"+day.toString())
                myRef.push().setValue(opinion_model)
            }
        }








    }
}