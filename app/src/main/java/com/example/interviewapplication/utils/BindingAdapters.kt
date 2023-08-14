package com.example.interviewapplication.utils

import android.view.View
import android.widget.ImageView
import com.google.android.material.textview.MaterialTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.interviewapplication.data.Image

@BindingAdapter("load")
fun loadImage(view: ImageView, images: List<Image>){
    if(images.isNotEmpty()){
        view.visibility = View.VISIBLE
        val firstImage = images[0]
        val imageUrl = firstImage.src
        Glide.with(view).load(imageUrl).into(view)
    }else{
        view.visibility = View.GONE
    }
}