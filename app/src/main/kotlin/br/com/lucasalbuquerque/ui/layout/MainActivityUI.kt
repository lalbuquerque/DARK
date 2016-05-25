package br.com.lucasalbuquerque.ui.layout

import android.content.Context
import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.*
import br.com.lucasalbuquerque.R
import br.com.lucasalbuquerque.domain.MarvelCharacter
import br.com.lucasalbuquerque.presenter.MainPresenter
import br.com.lucasalbuquerque.ui.activity.MainActivity
import br.com.lucasalbuquerque.ui.adapter.CharactersListAdapter
import br.com.lucasalbuquerque.ui.view.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView
import java.util.*
import javax.inject.Inject

class MainActivityUI @Inject constructor(val mainPresenter: MainPresenter, val context: Context) : AnkoComponent<MainActivity>, MainView {
    lateinit var contentLayout: RelativeLayout
    lateinit var marvelIcon: ImageView
    lateinit var charactersTitle: TextView
    lateinit var charactersList: RecyclerView
    lateinit var loadingLayout: ProgressBar
    lateinit var retrieveCharactersButton: FloatingActionButton

    init {
        mainPresenter.attachView(this)
    }

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        return coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout {
                toolbar {
//                    id = R.id.toolbar
                    popupTheme = R.style.AppTheme_PopUpOverlay
                    backgroundResource = R.color.primary
                    title = "KARD"
                    setTitleTextColor(Color.WHITE)
                }.lparams(width = matchParent) {
                    val tv = TypedValue()
                    if (ui.owner.theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
                        height = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics);
                    }
                }
            }.lparams(width = matchParent)

            contentLayout = relativeLayout {
                backgroundColor = R.color.marvel_red

                marvelIcon = imageView {
                    setImageResource(R.drawable.ic_marvel)
                }. lparams{
                    centerInParent()
                }

                charactersTitle = textView(R.string.characters_title) {
                    id = 1
                    textSize = 16f
                    textColor = Color.BLACK
                    visibility = View.GONE
                }. lparams {
                    alignParentLeft()
                    leftMargin = dip(16)
                    topMargin = dip(12)
                    bottomMargin = dip(12)
                }

                charactersList = recyclerView {
                    val orientation = LinearLayoutManager.VERTICAL
                    layoutManager = LinearLayoutManager(context, orientation, true)
                    overScrollMode = View.OVER_SCROLL_NEVER
                    visibility = View.GONE
                }. lparams(width = matchParent, height = wrapContent) {
                    below(1)
                    topMargin = dip(8)
                }

                loadingLayout = progressBar {
                    isIndeterminate = true
                    setBackgroundColor(android.R.color.holo_blue_light)
                    visibility = View.GONE
                }.lparams {
                    centerInParent()
                }
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            retrieveCharactersButton = floatingActionButton {
                imageResource = android.R.drawable.ic_media_play
                backgroundColor = ContextCompat.getColor(ui.owner, R.color.accent)
                onClick {
                    mainPresenter.retrieveCharacters()
                }
            }.lparams {
                margin = dip(24)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
    }

    override fun showLoadingLayout() {
        loadingLayout.visibility = View.VISIBLE

        charactersTitle.visibility = View.GONE
        charactersList.visibility = View.GONE
        retrieveCharactersButton.visibility = View.GONE
        retrieveCharactersButton.visibility = View.GONE
        marvelIcon.visibility = View.GONE
    }

    override fun hideLoadingLayout() {
        loadingLayout.visibility = View.GONE

        charactersTitle.visibility = View.VISIBLE
        charactersList.visibility = View.VISIBLE
    }

    override fun populateCharactersList(characters: List<MarvelCharacter>) {
        retrieveCharactersButton.visibility = View.GONE
        marvelIcon.visibility = View.GONE
        contentLayout.backgroundColor = Color.WHITE

        val listAdapter = CharactersListAdapter(context, characters as ArrayList<MarvelCharacter>)
        charactersList.adapter = listAdapter
    }

    override fun showErrorLayout() {
        retrieveCharactersButton.visibility = View.VISIBLE
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
    }
}