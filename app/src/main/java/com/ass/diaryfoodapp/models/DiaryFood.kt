package com.ass.diaryfoodapp.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryFood(
    @PrimaryKey(autoGenerate = true)
    var idDiary: Int? = null,
    val idFood: Int,
    val comment : String,
    val date : String,
    val image: Bitmap?,
)