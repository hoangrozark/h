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
import com.ass.diaryfoodapp.models.Food
import de.hdodenhof.circleimageview.CircleImageView

class FoodAdapter (
    private val con : Context
    , private val list : List<Food>
    , private val onLongClick:(Food) ->Unit
    , private val onClick:(Food) ->Unit) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

        inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview)
        {
            var tvNameFood = itemview.findViewById<TextView>(R.id.tvNameFood)
            var tvDanhGia = itemview.findViewById<TextView>(R.id.tvRatting)
            var image = itemview.findViewById<ImageView>(R.id.imageFood)

            fun onBind(food: Food) {
                itemView.setOnLongClickListener {
                    onLongClick(food)
                    true
                }

                itemView.setOnClickListener {
                    onClick(food)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(con).inflate(R.layout.item_food,parent,false)
            return ViewHolder(view)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvNameFood.text = list[position].tenMonAn.toString()
            holder.tvDanhGia.text = list[position].ratting.toString() + "/10"
            holder.image.load(list[position].image) {
                crossfade(true)
                crossfade(1000)
            }
            holder.onBind(list[position])
        }

        override fun getItemCount(): Int {
            return list.count()
        }
}