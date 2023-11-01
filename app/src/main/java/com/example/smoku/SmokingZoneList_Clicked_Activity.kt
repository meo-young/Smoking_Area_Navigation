package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SmokingZoneList_Clicked_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smoking_zone_list_clicked)

        val getIntent = getIntent()
        val title = getIntent.getStringExtra("smokingZoneTitle")
        val imageUrl = getIntent.getIntExtra("smokingZoneImage",0)

        findViewById<TextView>(R.id.smokingZoneTitle).setText(title)
        findViewById<ImageView>(R.id.smokingZoneImage).setImageResource(imageUrl)

        var items = ArrayList<OpinionRVModel>()
        items.add(OpinionRVModel(1,"흡연장이 너무 지저분해요..",3))
        items.add(OpinionRVModel(2,"냄새가 너무 나요",14))
        items.add(OpinionRVModel(3,"시험 빨리 끝났으면 ..",34))
        items.add(OpinionRVModel(4,"밥 같이 먹을 사람 ??",45))



        val rv = findViewById<RecyclerView>(R.id.opinion_list)
        val rvAdapter = OpinionRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)


        val intent = Intent(this, AllOpinionActivity::class.java)

        findViewById<TextView>(R.id.showAllOpinionBtn).setOnClickListener {
            intent.putExtra("OpinionList",items)
            startActivity(intent)
        }




    }
}