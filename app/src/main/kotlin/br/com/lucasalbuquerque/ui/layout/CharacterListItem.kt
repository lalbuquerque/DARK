package br.com.lucasalbuquerque.ui.layout

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.lucasalbuquerque.domain.MarvelCharacter
import br.com.lucasalbuquerque.extension.loadListItemImage
import org.jetbrains.anko.*

class CharacterListItem(context: Context) : CardView(context) {
    var name: TextView? = null
    var image: ImageView? = null

    init {
        this.apply {
//            background = context.obtainStyledAttributes(arrayOf(android.R.attr.selectableItemBackground).toIntArray()).getDrawable(0)
            cardElevation = dip(8).toFloat()
            radius = dip(4).toFloat()
            isClickable = true
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setCardBackgroundColor(Color.WHITE)

            relativeLayout {
                minimumHeight = dip(64)
                background = context.obtainStyledAttributes(arrayOf(android.R.attr.selectableItemBackground).toIntArray()).getDrawable(0)

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
        val characterImg = character.thumbnail

        image?.loadListItemImage(context, characterImg?.path.plus(".").plus(characterImg?.extension))
    }
}