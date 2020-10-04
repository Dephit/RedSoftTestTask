package com.sergeenko.alexey.redsofttesttask.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sergeenko.alexey.redsofttesttask.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, v: String?): Unit {
    Picasso
        .with(imageView.context)
        .load(v)
        .fit()
        .placeholder(R.drawable.image_search)
        .into(imageView)
}