package br.com.lucasalbuquerque.ui.layout

import android.content.Context
import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import br.com.lucasalbuquerque.R
import br.com.lucasalbuquerque.domain.MarvelCharacter
import br.com.lucasalbuquerque.presenter.CharactersPresenter
import br.com.lucasalbuquerque.ui.activity.CharactersActivity
import br.com.lucasalbuquerque.ui.adapter.CharactersListAdapter
import br.com.lucasalbuquerque.ui.view.CharactersView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import java.util.*
import javax.inject.Inject

class CharactersActivityUI @Inject constructor(val charactersPresenter: CharactersPresenter, val context: Context) : AnkoComponent<CharactersActivity>, CharactersView {
    lateinit var ankoContext: AnkoContext<CharactersActivity>

    lateinit var contentLayout: RelativeLayout
    lateinit var charactersTitle: TextView
    lateinit var charactersList: RecyclerView
    lateinit var loadingLayout: ProgressBar

    init {
        charactersPresenter.attachView(this)
    }

    override fun createView(ui: AnkoContext<CharactersActivity>): View = with(ui) {
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

            contentLayout = relativeLayout {
                backgroundColor = Color.WHITE

                charactersTitle = textView(R.string.characters_title) {
                    id = 1
                    textSize = 16f
                    textColor = Color.BLACK
                    visibility = View.GONE
                }.lparams {
                    alignParentLeft()
                    leftMargin = dip(16)
                    topMargin = dip(12)
                    bottomMargin = dip(12)
                }

                charactersList = recyclerView {
                    id = 2
                    val orientation = LinearLayoutManager.VERTICAL
                    layoutManager = LinearLayoutManager(context, orientation, true)
                    overScrollMode = View.OVER_SCROLL_NEVER
                    visibility = View.GONE
                }.lparams(width = matchParent, height = wrapContent) {
                    below(1)
                    topMargin = dip(8)
                }

                loadingLayout = progressBar {
                    isIndeterminate = true
                    visibility = View.GONE
                }.lparams {
                    centerInParent()
                }
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            charactersPresenter.retrieveCharacters()
        }
    }

    override fun showLoadingLayout() {
        loadingLayout.visibility = View.VISIBLE
        charactersList.visibility = View.GONE
    }

    override fun hideLoadingLayout() {
        loadingLayout.visibility = View.GONE
        charactersTitle.visibility = View.VISIBLE
        charactersList.visibility = View.VISIBLE
    }

    override fun populateCharactersList(characters: List<MarvelCharacter>) {
        val listAdapter = CharactersListAdapter(context, characters as ArrayList<MarvelCharacter>)
        charactersList.adapter = listAdapter
    }

    override fun showErrorLayout() {
        Toast.makeText(ankoContext.owner, "Something went wrong", Toast.LENGTH_LONG).show()
        ankoContext.owner.finish()
    }
}