package com.example.smoku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAdapter (val context:Context, val items: MutableList<RVModel>):RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return ViewHolder(view)
    }

    interface ItemClick{
        fun onClick(view:View, position:Int)
    }
    var itemClick:ItemClick? = null

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        if(itemClick != null){
            holder.itemView.setOnClickListener{v ->
                itemClick?.onClick(v,position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : RVModel){
            val rv_text = itemView.findViewById<TextView>(R.id.smokingZoneName)
            rv_text.text = item.title
            val rv_image = itemView.findViewById<ImageView>(R.id.smokingZoneItemImage)
            Glide.with(context)
                .load(item.image)
                .into(rv_image)
        }
    }
}