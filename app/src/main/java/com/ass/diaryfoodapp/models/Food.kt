package com.ass.diaryfoodapp.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Food(
    @PrimaryKey(autoGenerate = true)
    var idFood: Int? = null,
    val tenMonAn : String,
    val image: Bitmap?,
    var ratting: Int?,
    val stepMade : String,
    val bikipNauAn : String,
) :Serializable