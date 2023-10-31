package com.ass.diaryfoodapp.responsitory

import com.ass.diaryfoodapp.db.AppFoodDB
import com.ass.diaryfoodapp.models.DiaryFood
import javax.inject.Inject

class DiaryFoodResponsitory @Inject constructor(
    private val appFoodDB: AppFoodDB
) {
    suspend fun getDiaryFoods(id : Int): List<DiaryFood> {
        return appFoodDB.getDiaryFoodDB().getDiaryFoodByIDFood(id)
    }

    suspend fun insertDiaryFood(diaryFood: DiaryFood) {
        return appFoodDB.getDiaryFoodDB().addDiaryFood(diaryFood)
    }
}