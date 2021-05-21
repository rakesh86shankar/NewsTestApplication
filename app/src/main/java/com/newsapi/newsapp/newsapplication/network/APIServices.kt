package com.newsapi.newsapp.newsapplication.network

import com.newsapi.newsapp.newsapplication.services.NewsServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIServices (
        baseUrl: String
){
    companion object {
        public const val API_KEY = "a3b94d1e4cab435d8096cc0f20060b96"
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val retrofit : Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build()

    val newsService: NewsServices = retrofit.create(NewsServices::class.java)

}