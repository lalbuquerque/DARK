package br.com.lucasalbuquerque.presenter

import android.content.Intent
import br.com.lucasalbuquerque.ui.view.CharactersView

interface CharactersPresenter {
    fun attachView(charactersView: CharactersView, intent: Intent)
}