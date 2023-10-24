package com.example.smoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }
}