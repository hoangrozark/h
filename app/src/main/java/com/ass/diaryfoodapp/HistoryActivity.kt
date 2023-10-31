package com.ass.diaryfoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ass.diaryfoodapp.adapter.DiaryFoodAdapter
import com.ass.diaryfoodapp.adapter.FoodAdapter
import com.ass.diaryfoodapp.databinding.ActivityHistoryMainBinding
import com.ass.diaryfoodapp.databinding.ActivityMainBinding
import com.ass.diaryfoodapp.models.Food
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@Suppress("DEPRECATION")
@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {


    lateinit var viewModel: HistoryViewModel
    private lateinit var diaryFoodAdapter: DiaryFoodAdapter
    private lateinit var binding : ActivityHistoryMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        binding.viewmodel = viewModel
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getSerializable(MainActivity.FOODDATA)
        viewModel.id.set(id as Int?);
        viewModel.getDiaryFoods(id as Int)

        binding.apply {
            rvFood.layoutManager = LinearLayoutManager(applicationContext)
            rvFood.hasFixedSize()
        }

       viewModel.diaryFoods.observe(this) {
           diaryFoodAdapter = DiaryFoodAdapter(this, it)
           binding.rvFood.adapter = diaryFoodAdapter
       }


        binding.btnAddFood.setOnClickListener {
            val intent = Intent(this,AddFoodDiaray::class.java)
            val bundle = Bundle()
            bundle.putInt(MainActivity.FOODDATA, viewModel.id.get()!!)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.back.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getDiaryFoods(viewModel.id.get()!!)
    }


}