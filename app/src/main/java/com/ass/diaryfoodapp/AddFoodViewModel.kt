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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFoodViewModel @Inject constructor(
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
    val ratting = ObservableField<String>("1")
    val stepString = ObservableField<String>("")
    val bikipNauAn = ObservableField<String>("")
    val image = ObservableField<Bitmap>()

    fun setInitData(food: Food?) {
        id.set(food!!.idFood)
        nameFood.set(food.tenMonAn)
        ratting.set(food.ratting.toString())
        stepString.set(food.stepMade)
        bikipNauAn.set(food.bikipNauAn)
        image.set(food.image)
    }

    fun saveFood(check: Boolean): Boolean {
        if (nameFood.get()!!.isBlank()
            ||ratting.get()!!.isBlank()
            ||stepString.get()!!.isBlank()
            || bikipNauAn.get()!!.isBlank()
        ) {
            return false;
        }

        val food = Food(
            idFood = id.get(),
            tenMonAn = nameFood.get().toString(),
            image = image.get(),
            ratting = ratting.get().toString().toInt(),
            bikipNauAn = bikipNauAn.get().toString(),
            stepMade = stepString.get().toString())

        if (check) {
            CoroutineScope(Dispatchers.IO).launch {
                async {  repository.updateFood(food) }.await()
            }
        } else {

            CoroutineScope(Dispatchers.IO).launch {
                async {  repository.insertFood(food) }.await()
            }

        }

        return true;
    }



}