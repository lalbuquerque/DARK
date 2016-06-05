package br.com.lucasalbuquerque.presenter

import br.com.lucasalbuquerque.ui.view.CharactersView

interface CharactersPresenter {
    fun attachView(charactersView: CharactersView)
    fun loadCharacters()
}