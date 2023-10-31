package com.ass.diaryfoodapp.responsitory

import com.ass.diaryfoodapp.db.AppFoodDB
import com.ass.diaryfoodapp.models.Food
import javax.inject.Inject

class FoodResponsitory @Inject constructor(
    private val appFoodDB: AppFoodDB
) {
    suspend fun getFoods(): List<Food> {
        return appFoodDB.getFoodDB().getFoods()
    }

    suspend fun insertFood(food: Food) {
        return appFoodDB.getFoodDB().addFood(food)
    }

    suspend fun updateFood(food: Food) {
        return appFoodDB.getFoodDB().updateFood(food)
    }
}