package com.example.smoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class CommunityViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_view)

        val database = Firebase.database
        val myRef = database.getReference("opinion")
        var items = ArrayList<OpinionRVModel>()
        myRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for (dataModel in snapshot.children){
//                    val auc = Integer.parseInt(dataModel.child("anonymous_user_count").getValue().toString())
//                    val textArea = dataModel.child("textArea").getValue().toString()
//                    val passed_days = Integer.parseInt(dataModel.child("passed_days").getValue().toString())
                    Log.e("data",dataModel.getValue().toString())
                    val item = dataModel.getValue(OpinionRVModel::class.java)!!
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val rv = findViewById<RecyclerView>(R.id.communityView_rv)
        val rvAdapter = OpinionRVAdapter(baseContext,items)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,1)


    }
}