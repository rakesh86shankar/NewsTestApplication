package com.newsapi.newsapp.newsapplication.network

import com.newsapi.newsapp.newsapplication.services.NewsServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*


class APIServices(
        baseUrl: String
){
    companion object {
        public const val API_KEY = "a3b94d1e4cab435d8096cc0f20060b96"
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

   // private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val okHttpClient = createOkHttpClient()

    private val retrofit : Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build()

    val newsService: NewsServices = retrofit.create(NewsServices::class.java)

    private fun createOkHttpClient(): OkHttpClient? {
        return try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true }).build()
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }

}

sealed class apiResult<out R> {
    data class Success<out T>(val data: T) : apiResult<T>()
    data class Error(val exception: Exception) : apiResult<Nothing>()
}
