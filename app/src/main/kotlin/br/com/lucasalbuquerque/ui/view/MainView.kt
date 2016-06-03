package br.com.lucasalbuquerque.ui.view

import br.com.lucasalbuquerque.domain.MarvelCharacter

interface MainView {
    fun showLoadingLayout()
    fun hideLoadingLayout()
    fun goToCharactersActivity(characters: List<MarvelCharacter>)
    fun showErrorLayout()
}