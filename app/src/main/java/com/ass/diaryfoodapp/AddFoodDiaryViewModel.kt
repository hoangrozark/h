package com.ass.diaryfoodapp

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ass.diaryfoodapp.models.DiaryFood
import com.ass.diaryfoodapp.models.Food
import com.ass.diaryfoodapp.responsitory.DiaryFoodResponsitory
import com.ass.diaryfoodapp.responsitory.FoodResponsitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddFoodDiaryViewModel @Inject constructor(
    private val repository: DiaryFoodResponsitory
) : ViewModel() {


    val id = ObservableField<Int>()
    val comment = ObservableField<String>("")
    val image = ObservableField<Bitmap>()


    fun saveFoodDiary(): Boolean {
        if (comment.get()!!.isBlank()
        ) {
            return false;
        }

        val foodDiary = DiaryFood(
            idFood = id.get()!!,
            comment = comment.get().toString(),
            image = image.get(),
            date = Date().toString())

        CoroutineScope(Dispatchers.IO).launch {
            async { repository.insertDiaryFood(foodDiary) }.await()
        }

        return true
    }



}