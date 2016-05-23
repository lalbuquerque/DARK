package br.com.lucasalbuquerque.repository.remote.retrofit.interceptor;

import br.com.lucasalbuquerque.repository.remote.util.MD5Util
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject


class RequestInterceptor @Inject constructor(val md5Util: MD5Util, val publicApiKey: String, val privateApiKey : String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) : Response {
        val request = chain.request()
        val url = request.url()

        val timestamp = Date().time.toString()

        val newUrl = url.newBuilder()
                .addEncodedQueryParameter("apikey", publicApiKey)
                .addEncodedQueryParameter("ts", timestamp)
                .addEncodedQueryParameter("hash", md5Util.hash(publicApiKey, privateApiKey, timestamp))
                .build()

        val newRequest = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .url(newUrl)
                .build()

        return chain.proceed(newRequest)
    }
}