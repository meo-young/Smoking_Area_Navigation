package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AllOpinionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_opinion)

        val getIntent = getIntent()
        val items : ArrayList<OpinionRVModel> = getIntent.getSerializableExtra("OpinionList") as ArrayList<OpinionRVModel>



        val rv = findViewById<RecyclerView>(R.id.allOpinionList)
        val rvAdapter = OpinionRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)

        val intent = Intent(this, WriteOpinionActivity::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(intent)
        }



    }
}