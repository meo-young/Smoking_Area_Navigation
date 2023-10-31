package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SmokingZoneListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smoking_zone_list)

        var items = mutableListOf<RVModel>()
        items.add(RVModel("프론트홀",R.drawable.front_hall_image))
        items.add(RVModel("혁신관",R.drawable.innovation_hall_image))
        items.add(RVModel("문과대학",R.drawable.liberalarts_college_image))
        items.add(RVModel("황소동상",R.drawable.bull_statue_image))
        items.add(RVModel("이과대학",R.drawable.science_college_image))
        items.add(RVModel("도서관",R.drawable.library_image))
        items.add(RVModel("예술디자인대학",R.drawable.artdesign_college_image))
        items.add(RVModel("협동관",R.drawable.cooperative_hall_image))
        items.add(RVModel("공학관",R.drawable.engineering_hall_image))
        items.add(RVModel("신공학관",R.drawable.new_engineering_hall_image))
        items.add(RVModel("행정관",R.drawable.executive_official_hall_image))
        items.add(RVModel("상허관",R.drawable.sangheo_hall_image))
        items.add(RVModel("법학관",R.drawable.law_building_image))
        items.add(RVModel("새천년관",R.drawable.new_millennium_hall_image))



        val rv = findViewById<RecyclerView>(R.id.smokingZoneList)
        val rvAdapter = RVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,2)

        val intent = Intent(this, SmokingZoneList_Clicked_Activity::class.java)

        rvAdapter.itemClick = object : RVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                intent.putExtra("smokingZoneTitle",items[position].title)
                intent.putExtra("smokingZoneImage",items[position].image)
                startActivity(intent)
            }
        }


    }
}