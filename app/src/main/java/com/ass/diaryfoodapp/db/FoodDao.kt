package com.ass.diaryfoodapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ass.diaryfoodapp.models.Food

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: Food)

    @Query("SELECT * FROM Food")
    suspend fun getFoods() : List<Food>

    @Update
    suspend fun updateFood(food: Food)

}