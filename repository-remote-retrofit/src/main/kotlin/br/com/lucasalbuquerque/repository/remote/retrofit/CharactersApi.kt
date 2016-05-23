package br.com.lucasalbuquerque.repository.remote.retrofit

import br.com.lucasalbuquerque.domain.DataWrapper
import br.com.lucasalbuquerque.domain.MarvelCharacter
import retrofit2.http.GET
import rx.Observable

interface CharactersApi {

    @GET("/v1/public/characters")
    fun getCharacters() : Observable<DataWrapper<MarvelCharacter>>
}