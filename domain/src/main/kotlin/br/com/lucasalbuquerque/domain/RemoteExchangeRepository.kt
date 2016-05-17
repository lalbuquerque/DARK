package br.com.lucasalbuquerque.domain

import rx.Observable

interface RemoteExchangeRepository {
    fun retrieveAllTickers() : Observable<List<Ticker>>
}