package com.example.smoku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class CommunityRVAdapter (val context:Context, val items: MutableList<CommunityRVModel>):RecyclerView.Adapter<CommunityRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_community,parent,false)
        return ViewHolder(view)
    }

    interface ItemClick{
        fun onClick(view:View, position:Int)
    }
    var itemClick:ItemClick? = null

    override fun onBindViewHolder(holder: CommunityRVAdapter.ViewHolder, position: Int) {
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
        fun bindItems(item : CommunityRVModel){
            val rv_title = itemView.findViewById<TextView>(R.id.community_title)
            val rv_textArea = itemView.findViewById<TextView>(R.id.community_textArea)
            val rv_passed_days = itemView.findViewById<TextView>(R.id.passed_days)

            rv_title.text = item.title
            rv_textArea.text = item.textArea
            rv_passed_days.text = item.passed_days


        }
    }
}