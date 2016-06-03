package br.com.lucasalbuquerque.ui.view

import br.com.lucasalbuquerque.domain.MarvelCharacter

interface CharactersView {
    fun showLoadingLayout()
    fun hideLoadingLayout()
    fun populateCharactersList(characters: List<MarvelCharacter>)
    fun showErrorLayout()
}