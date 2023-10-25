package com.example.smoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommunityViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_view)

        var items = ArrayList<OpinionRVModel>()
        items.add(OpinionRVModel(1,"흡연장이 너무 지저분해요..",3))
        items.add(OpinionRVModel(2,"냄새가 너무 나요",14))
        items.add(OpinionRVModel(3,"시험 빨리 끝났으면 ..",34))
        items.add(OpinionRVModel(4,"밥 같이 먹을 사람 ??",45))

        val rv = findViewById<RecyclerView>(R.id.communityView_rv)
        val rvAdapter = OpinionRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)


    }
}