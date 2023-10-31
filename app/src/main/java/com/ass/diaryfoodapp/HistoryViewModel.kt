package com.ass.diaryfoodapp

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
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HistoryViewModel  @Inject constructor(
    private val repository: DiaryFoodResponsitory
) : ViewModel() {

    val id = ObservableField<Int>()
    private val _diaryFoods = MutableLiveData<List<DiaryFood>>()
    val diaryFoods: LiveData<List<DiaryFood>>
        get() = _diaryFoods



     fun getDiaryFoods(id : Int) {
        viewModelScope.launch {
            var a = async { repository.getDiaryFoods(id) }
            _diaryFoods.postValue(a.await())
        }
    }


}