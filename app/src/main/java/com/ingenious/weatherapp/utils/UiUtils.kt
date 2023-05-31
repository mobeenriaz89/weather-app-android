package com.ingenious.weatherapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.ingenious.weatherapp.R


object UiUtils {

    fun loadImage(context: Context,path: String, view: ImageView){
        Glide
            .with(context)
            .load(path)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(view)
    }
}