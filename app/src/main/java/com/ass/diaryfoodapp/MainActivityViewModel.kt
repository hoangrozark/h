package com.ass.diaryfoodapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ass.diaryfoodapp.models.Food
import com.ass.diaryfoodapp.responsitory.FoodResponsitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainActivityViewModel  @Inject constructor(
    private val repository: FoodResponsitory
) : ViewModel() {
    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>>
        get() = _foods


    init {
        getFoods()
    }

    fun getFoods() {
        viewModelScope.launch {
            var a =async { repository.getFoods() }
            _foods.postValue(a.await())
        }
    }


}