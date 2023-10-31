package com.ass.diaryfoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ass.diaryfoodapp.adapter.FoodAdapter
import com.ass.diaryfoodapp.databinding.ActivityMainBinding
import com.ass.diaryfoodapp.models.Food
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        val FOODDATA: String = "FOODDATA"
    }

    lateinit var viewModel: MainActivityViewModel
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewmodel = viewModel
        setContentView(binding.root)

        binding.apply {
            rvFood.layoutManager = LinearLayoutManager(applicationContext)
            rvFood.hasFixedSize()
        }

       viewModel.foods.observe(this) {
           foodAdapter = FoodAdapter(this, it, onLongClick = { food ->
               val intent = Intent(this,AddFood::class.java)
               val bundle = Bundle()
               bundle.putInt(FOODDATA,food.idFood!!)
               intent.putExtras(bundle)
               startActivity(intent)
           }, onClick =  {  food->
               val intent = Intent(this,DetailFood::class.java)
               val bundle = Bundle()
               bundle.putInt(FOODDATA,food.idFood!!)
               intent.putExtras(bundle)
               startActivity(intent)
           })
           binding.rvFood.adapter = foodAdapter
       }


        binding.btnAddFood.setOnClickListener {
            val intent = Intent(this,AddFood::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFoods()
    }


}