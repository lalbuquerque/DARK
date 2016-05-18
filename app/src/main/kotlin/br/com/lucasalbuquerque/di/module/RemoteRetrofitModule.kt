package br.com.lucasalbuquerque.di.module

import android.app.Application
import br.com.lucasalbuquerque.domain.RemoteExchangeRepository
import br.com.lucasalbuquerque.repository.remote.di.annotations.RemoteUri
import br.com.lucasalbuquerque.repository.remote.retrofit.PublicApi
import br.com.lucasalbuquerque.repository.remote.retrofit.repository.RemoteRetrofitExchangeRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RemoteRetrofitModule(private val application: Application) {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @RemoteUri
    fun provideRemoteUri(): String {
        return "https://poloniex.com/"
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Provides
    fun providePublicApi(@RemoteUri remoteUri: String, okHttpClient: OkHttpClient, converterFactory: Converter.Factory): PublicApi {
        return Retrofit.Builder().baseUrl(remoteUri).client(okHttpClient).addConverterFactory(converterFactory).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(PublicApi::class.java)
    }

    @Provides
    @br.com.lucasalbuquerque.repository.remote.di.annotations.Retrofit
    fun provideRemoteRepository(publicApi: PublicApi): RemoteExchangeRepository {
        return RemoteRetrofitExchangeRepository(publicApi)
    }
}