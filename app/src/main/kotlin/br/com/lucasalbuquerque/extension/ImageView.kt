package br.com.lucasalbuquerque.extension

import android.content.Context
import android.widget.ImageView
import br.com.lucasalbuquerque.R
import com.bumptech.glide.Glide

fun ImageView.loadListItemImage(context: Context, imagePath: String) {
    Glide.with(context)
            .load(imagePath)
            .centerCrop()
            .override(100, 100)
            .placeholder(R.drawable.progress_animation)
            .into(this)
}