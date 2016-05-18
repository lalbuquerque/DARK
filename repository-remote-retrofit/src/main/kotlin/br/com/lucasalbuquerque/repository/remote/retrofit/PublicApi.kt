package br.com.lucasalbuquerque.repository.remote.retrofit

import br.com.lucasalbuquerque.domain.Ticker
import retrofit2.http.GET
import rx.Observable

interface PublicApi {

    @GET("/public?command=returnTicker")
    fun getTickers() : Observable<List<Ticker>>
}