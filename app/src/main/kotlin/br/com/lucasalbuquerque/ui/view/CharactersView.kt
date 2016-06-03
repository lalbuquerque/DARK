package br.com.lucasalbuquerque.ui.view

import br.com.lucasalbuquerque.domain.MarvelCharacter

interface CharactersView {
    fun populateCharactersList(characters: List<MarvelCharacter>)
}