package com.example.smoku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class OpinionRVAdapter (val context:Context, val items: ArrayList<OpinionRVModel>):RecyclerView.Adapter<OpinionRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpinionRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_opinion,parent,false)
        return ViewHolder(view)
    }

    interface ItemClick{
        fun onClick(view:View, position:Int)
    }
    var itemClick:ItemClick? = null

    override fun onBindViewHolder(holder: OpinionRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : OpinionRVModel){
            val rv_count = itemView.findViewById<TextView>(R.id.anonymous_user)
            rv_count.text = "익명" + Integer.toString(item.anonymous_user_count)
            val rv_opinion_area = itemView.findViewById<TextView>(R.id.opinion)
            rv_opinion_area.text = item.textArea
            val rv_passed_days = itemView.findViewById<TextView>(R.id.passed_days)
            rv_passed_days.text = Integer.toString(item.passed_days) + " Days ago"


        }
    }
}