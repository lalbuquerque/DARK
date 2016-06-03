package br.com.lucasalbuquerque.ui.layout

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import br.com.lucasalbuquerque.R
import br.com.lucasalbuquerque.presenter.MainPresenter
import br.com.lucasalbuquerque.ui.activity.CharactersActivity
import br.com.lucasalbuquerque.ui.activity.MainActivity
import br.com.lucasalbuquerque.ui.view.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import javax.inject.Inject

class MainActivityUI @Inject constructor(val mainPresenter: MainPresenter) : AnkoComponent<MainActivity>, MainView {

    lateinit var ankoContext: AnkoContext<MainActivity>

    init {
        mainPresenter.attachView(this)
    }

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        ankoContext = ui

        return coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout {
                toolbar {
                    popupTheme = R.style.AppTheme_PopUpOverlay
                    backgroundResource = R.color.primary
                    title = context.getString(R.string.app_name)
                    setTitleTextColor(Color.WHITE)
                }.lparams(width = matchParent) {
                    val tv = TypedValue()
                    if (ui.owner.theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
                        height = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics);
                    }
                }
            }.lparams(width = matchParent)

            relativeLayout {
                backgroundColor = Color.WHITE

                imageView {
                    setImageResource(R.drawable.ic_marvel)
                }. lparams{
                    centerInParent()
                }

            }.lparams(width = matchParent, height = matchParent)

            floatingActionButton {
                imageResource = android.R.drawable.ic_media_play
                backgroundColor = ContextCompat.getColor(ui.owner, R.color.accent)
                onClick {
                    mainPresenter.onCharactersButtonClick()
                }
            }.lparams {
                margin = dip(24)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
    }

    override fun goToCharactersActivity() {
        ankoContext.startActivity<CharactersActivity>()
    }
}