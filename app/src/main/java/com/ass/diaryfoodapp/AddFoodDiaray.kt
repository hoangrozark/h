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
import com.ass.diaryfoodapp.databinding.ActivityAddFoodDiaryBinding
import com.ass.diaryfoodapp.databinding.ActivityMainBinding
import com.ass.diaryfoodapp.models.Food
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AddFoodDiaray : AppCompatActivity() {
    val PICK_IMAGE_REQUEST = 1
    lateinit var viewModel: AddFoodDiaryViewModel
    private var check : Boolean = false
    private lateinit var binding : ActivityAddFoodDiaryBinding
    var list : List<Food> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodDiaryBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(AddFoodDiaryViewModel::class.java)
        binding.viewmodel = viewModel
        setContentView(binding.root)
        val bundle = intent.extras
        val id = bundle?.getSerializable(MainActivity.FOODDATA)
        viewModel.id.set(id as Int?)


        binding.btnSave.setOnClickListener {
            if (viewModel.saveFoodDiary()) {
                binding.btnSave.postDelayed({},0)
                finish()
            } else {
                Toast.makeText(this,"Thông tin nhập chưa đầy đủ hoặc không chính xác ",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.imageView2.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }


        binding.back.setOnClickListener {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, imageUri!!))
            } else {
                MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
            }

            viewModel.image.set(bitmap)
            binding.imageView2.setImageURI(imageUri)
        }
    }




}