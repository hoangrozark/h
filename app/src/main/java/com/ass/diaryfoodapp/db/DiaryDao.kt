package com.ass.diaryfoodapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ass.diaryfoodapp.models.DiaryFood

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDiaryFood(diaryFood : DiaryFood)

    @Query("SELECT * FROM DiaryFood WHERE idFood=:id")
    suspend fun getDiaryFoodByIDFood(id: Int) : List<DiaryFood>

}