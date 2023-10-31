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
import com.ass.diaryfoodapp.databinding.ActivityMainBinding
import com.ass.diaryfoodapp.models.Food
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AddFood : AppCompatActivity() {
    val PICK_IMAGE_REQUEST = 1
    lateinit var viewModel: AddFoodViewModel
    private var check : Boolean = false
    private lateinit var binding : ActivityAddFoodBinding
    var list : List<Food> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(AddFoodViewModel::class.java)
        binding.viewmodel = viewModel
        setContentView(binding.root)


        viewModel.foods.observe(this) {
            val bundle = intent.extras
            if (bundle == null) {
                check = false
                viewModel.title.set("Thêm món ăn")
            } else {
                check = true
                viewModel.title.set("Cập nhật món ăn")
                val id = bundle.getSerializable(MainActivity.FOODDATA)


                for (ob in it) {
                    if (ob.idFood == id) {
                        viewModel.setInitData(ob)
                        binding.imageFood.load(ob.image) {
                            crossfade(true)
                            crossfade(1000)
                        }

                    }

                }



            }
        }



        binding.btnSave.setOnClickListener {
            if (viewModel.saveFood(check)) {
                binding.btnSave.postDelayed({},0)
                finish()
            } else {
                Toast.makeText(this,"Thông tin nhập chưa đầy đủ hoặc không chính xác hoặc bạn chưa tính số calo tiêu thụ",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.imageFood.setOnClickListener{
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
            binding.imageFood.setImageURI(imageUri)
        }
    }




}