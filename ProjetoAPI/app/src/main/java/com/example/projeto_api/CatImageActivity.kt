package com.example.projeto_api

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CatImageActivity : AppCompatActivity() {

    private lateinit var catImageView: ImageView
    private lateinit var newImageButton: Button

    private val catApiUrl = "https://cataas.com/cat?timestamp="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_image)

        catImageView = findViewById(R.id.catImageView)
        newImageButton = findViewById(R.id.newImageButton)

        loadCatImage()

        newImageButton.setOnClickListener {
            loadCatImage()
        }
    }

    private fun loadCatImage() {
        val url = catApiUrl + System.currentTimeMillis()
        Glide.with(this)
            .load(url)
            .into(catImageView)
    }
}
