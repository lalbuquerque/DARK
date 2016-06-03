package br.com.lucasalbuquerque.presenter

import android.content.Intent
import br.com.lucasalbuquerque.ui.view.CharactersView
import javax.inject.Inject

class CharactersPresenterImpl @Inject constructor() : CharactersPresenter{


    override fun attachView(charactersView: CharactersView, intent: Intent) {
        throw UnsupportedOperationException()
    }
}