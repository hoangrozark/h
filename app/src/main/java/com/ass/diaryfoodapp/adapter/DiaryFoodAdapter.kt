package com.ass.diaryfoodapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ass.diaryfoodapp.R
import com.ass.diaryfoodapp.models.DiaryFood
import com.ass.diaryfoodapp.models.Food
import de.hdodenhof.circleimageview.CircleImageView

class DiaryFoodAdapter (
    private val con : Context
    , private val list : List<DiaryFood>) : RecyclerView.Adapter<DiaryFoodAdapter.ViewHolder>() {

        inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)
        {
            var tvComment = itemview.findViewById<TextView>(R.id.tvComment)
            var tvdate = itemview.findViewById<TextView>(R.id.tvDate)
            var imageFood = itemview.findViewById<ImageView>(R.id.imageFood)


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(con).inflate(R.layout.item_history,parent,false)
            return ViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvComment.text = list[position].comment.toString()
            holder.tvdate.text = list[position].date.toString()
            holder.imageFood.load(list[position].image) {
                crossfade(true)
                crossfade(1000)
            }
        }

        override fun getItemCount(): Int {
            return list.count()
        }
}