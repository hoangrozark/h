package com.ass.diaryfoodapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.ass.diaryfoodapp.adapter.FoodAdapter
import com.ass.diaryfoodapp.databinding.ActivityAddFoodBinding
import com.ass.diaryfoodapp.databinding.ActivityDetailFoodBinding
import com.ass.diaryfoodapp.databinding.ActivityMainBinding
import com.ass.diaryfoodapp.models.Food
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailFood : AppCompatActivity() {
    lateinit var viewModel: DetailFoodViewModel
    private lateinit var binding : ActivityDetailFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(DetailFoodViewModel::class.java)
        binding.viewmodel = viewModel
        setContentView(binding.root)


        viewModel.foods.observe(this) {
            val bundle = intent.extras
            if (bundle == null) {
                finish()
            } else {

                val id = bundle.getSerializable(MainActivity.FOODDATA)


                for (ob in it) {
                    if (ob.idFood == id) {
                        viewModel.setInitData(ob)
                        viewModel.title.set(ob.tenMonAn)
                        binding.imageView.load(ob.image) {
                            crossfade(true)
                            crossfade(1000)
                        }

                    }

                }



            }
        }



        binding.btnShow.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            val bundle = Bundle()
            bundle.putInt(MainActivity.FOODDATA,viewModel.id.get()!!)
            intent.putExtras(bundle)
            startActivity(intent)
        }



        binding.back.setOnClickListener {
            finish()
        }
    }






}