package br.com.lucasalbuquerque.repository.remote.retrofit.repository

import br.com.lucasalbuquerque.domain.RemoteExchangeRepository
import br.com.lucasalbuquerque.domain.Ticker
import br.com.lucasalbuquerque.repository.remote.retrofit.PublicApi
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRetrofitExchangeRepository @Inject constructor(publicApi: PublicApi) : RemoteExchangeRepository {

    override fun retrieveAllTickers(): Observable<List<Ticker>> {
        throw UnsupportedOperationException()
    }

}