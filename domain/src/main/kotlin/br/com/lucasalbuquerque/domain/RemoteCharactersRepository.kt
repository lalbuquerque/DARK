package br.com.lucasalbuquerque.domain

import rx.Observable

interface RemoteCharactersRepository {
    fun retrieveAllCharacters() : Observable<List<MarvelCharacter>>
}