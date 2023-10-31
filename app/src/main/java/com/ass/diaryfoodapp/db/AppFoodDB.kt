package com.ass.diaryfoodapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ass.diaryfoodapp.models.DiaryFood
import com.ass.diaryfoodapp.models.Food
import com.ass.diaryfoodapp.utils.Converters

@Database(entities = [Food::class, DiaryFood::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppFoodDB : RoomDatabase() {
    abstract fun getFoodDB() : FoodDao
    abstract fun getDiaryFoodDB() : DiaryDao
}