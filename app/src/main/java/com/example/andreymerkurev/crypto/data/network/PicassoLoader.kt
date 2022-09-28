package com.example.andreymerkurev.crypto.data.network

import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoLoader {
    fun loadImage(url: String, imageView: ImageView) {
        Picasso
            .get()
            .load(url)
            .into(imageView)
    }
}