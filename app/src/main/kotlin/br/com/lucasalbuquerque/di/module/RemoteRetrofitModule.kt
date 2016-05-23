package br.com.lucasalbuquerque.di.module

import android.app.Application
import br.com.lucasalbuquerque.domain.RemoteCharactersRepository
import br.com.lucasalbuquerque.repository.remote.di.annotations.RemoteUri
import br.com.lucasalbuquerque.repository.remote.retrofit.CharactersApi
import br.com.lucasalbuquerque.repository.remote.retrofit.interceptor.RequestInterceptor
import br.com.lucasalbuquerque.repository.remote.retrofit.repository.RemoteRetrofitCharactersRepository
import br.com.lucasalbuquerque.repository.remote.util.MD5Util
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteRetrofitModule(private val application: Application) {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideRequestInterceptor(md5Util: MD5Util): RequestInterceptor {
        val interceptor = RequestInterceptor(md5Util, "MARVEL_API_PUBLIC_KEY", "MARVEL_API_PRIVATE_KEY")
        return interceptor;
    }

    @Provides
    @RemoteUri
    fun provideRemoteUri(): String {
        return "http://gateway.marvel.com"
    }

    @Provides
    fun provideMD5Util(): MD5Util {
        return MD5Util()
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, requestInterceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Provides
    fun provideRetrofit(@RemoteUri remoteUri: String, okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder().baseUrl(remoteUri)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    fun provideCharactersApi(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    @Provides
    @Singleton
    @br.com.lucasalbuquerque.repository.remote.di.annotations.Retrofit
    fun provideRemoteCharactersRepository(charactersApi: CharactersApi): RemoteCharactersRepository {
        val remoteCharactersRepository = RemoteRetrofitCharactersRepository(charactersApi)
        return remoteCharactersRepository
    }

}