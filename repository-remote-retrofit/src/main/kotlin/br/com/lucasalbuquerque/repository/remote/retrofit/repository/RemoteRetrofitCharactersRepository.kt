package br.com.lucasalbuquerque.repository.remote.retrofit.repository

import br.com.lucasalbuquerque.domain.MarvelCharacter
import br.com.lucasalbuquerque.domain.RemoteCharactersRepository
import br.com.lucasalbuquerque.repository.remote.retrofit.CharactersApi
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRetrofitCharactersRepository @Inject constructor(val charactersApi: CharactersApi) : RemoteCharactersRepository {

    override fun retrieveAllCharacters(): Observable<List<MarvelCharacter>> {
        return charactersApi.getCharacters()
                            .map{ it.data?.results }
    }
}