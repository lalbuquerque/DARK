package br.com.lucasalbuquerque.ui.layout

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.lucasalbuquerque.R
import br.com.lucasalbuquerque.domain.MarvelCharacter
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class CharacterListItem(context: Context) : CardView(context) {
    var name: TextView? = null
    var image: ImageView? = null

    init {
        this.apply {
            background = context.obtainStyledAttributes(arrayOf(R.attr.selectableItemBackground).toIntArray()).getDrawable(0)
            isClickable = true
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            relativeLayout {
                minimumHeight = dip(64)

                image = imageView {
                    id = 1
                }
                image?.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)
                    addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
                    leftMargin = dip(8)
                    topMargin = dip(8)
                }

                name = textView {
                    id = 2
                    textColor = Color.DKGRAY
                }
                name?.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    addRule(RelativeLayout.RIGHT_OF, 1)
                    addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
                    leftMargin = dip(8)
                    topMargin = dip(8)
                }
            }

        }
    }

    fun bind(character: MarvelCharacter) {
        name?.text = character.name
        image?.scaleType = ImageView.ScaleType.FIT_CENTER
        val charImg = character.thumbnail!!

        Glide.with(context)
                .load(charImg.path.plus(".").plus(charImg.extension))
                .centerCrop()
                .override(100, 100)
                .placeholder(R.drawable.progress_animation)
                .into(image)
    }
}