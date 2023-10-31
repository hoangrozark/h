package com.ass.diaryfoodapp

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ass.diaryfoodapp.models.Food
import com.ass.diaryfoodapp.responsitory.FoodResponsitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFoodViewModel @Inject constructor(
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
            _foods.postValue(repository.getFoods())
        }
    }

    val id = ObservableField<Int>()
    val title = ObservableField<String>("")
    val nameFood = ObservableField<String>("")
    val ratting = ObservableField<String>("")
    val stepString = ObservableField<String>("")
    val bikipNauAn = ObservableField<String>("")

    fun setInitData(food: Food?) {
        id.set(food!!.idFood)
        nameFood.set(food.tenMonAn)
        ratting.set("Đánh giá :"+food.ratting.toString()+"/10")
        stepString.set(food.stepMade)
        bikipNauAn.set(food.bikipNauAn)
    }





}