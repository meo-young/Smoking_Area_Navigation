package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class CommunityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        val items = mutableListOf<CommunityRVModel>()

        val rv = findViewById<RecyclerView>(R.id.community_rv)
        val rvAdapter = CommunityRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)

        val database = Firebase.database
        val myRef = database.getReference("community")

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataModel in snapshot.children){
                    Log.d("data2",dataModel.toString())
                    items.add(dataModel.getValue(CommunityRVModel::class.java)!!)
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("datamodel",items.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })




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