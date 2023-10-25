package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        val items = mutableListOf<CommunityRVModel>()

        items.add(CommunityRVModel("반가워요","아 진짜 너무 덥다","18:21"))
        items.add(CommunityRVModel("반가워요","아 진짜 너무 덥다","18:21"))
        items.add(CommunityRVModel("반가워요","아 진짜 너무 덥다","18:21"))
        items.add(CommunityRVModel("반가워요","아 진짜 너무 덥다","18:21"))


        val rv = findViewById<RecyclerView>(R.id.community_rv)
        val rvAdapter = CommunityRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)

        val intent = Intent(this, WriteCommunityActivity::class.java)

        findViewById<Button>(R.id.writeTextBtn).setOnClickListener {
            startActivity(intent)
        }

        val intent2communityView = Intent(this, CommunityViewActivity::class.java)

        rvAdapter.itemClick = object : CommunityRVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                startActivity(intent2communityView)
            }
        }
    }
}