package com.example.smoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class AllOpinionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_opinion)

        val getIntent = getIntent()
        val title = getIntent.getStringExtra("smokingZoneTitle")

        val items = ArrayList<OpinionRVModel>()



        val rv = findViewById<RecyclerView>(R.id.allOpinionList)
        val rvAdapter = OpinionRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)

        val database = Firebase.database
        val myRef = database.getReference(title.toString())

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                items.clear()
                for (dataModel in snapshot.children){
                    Log.d("data2",dataModel.toString())
                    items.add(dataModel.getValue(OpinionRVModel::class.java)!!)
                    Log.d("datakey",dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("datamodel",items.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val intent = Intent(this, WriteOpinionActivity::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
            intent.putExtra("smokingZoneTitle",title)
            startActivity(intent)
        }



    }
}