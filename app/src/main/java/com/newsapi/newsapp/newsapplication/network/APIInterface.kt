package com.newsapi.newsapp.newsapplication.network

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.Source
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rakesh sankar on 9/12/2017.
 */
interface APIInterface {
    //https://newsapi.org/v1/sources
    //String url = "https://newsapi.org/v1/articles?source=bbc-sport&sortBy=latest&apiKey=a3b94d1e4cab435d8096cc0f20060b96
    @GET("/?sources=techcrunch&apiKey=be9c63bd0fa94e8b85836fed535d73d0")
    fun doGetNewPaperResources(): Call<List<Source?>?>?

    @GET("/v1/sources")
    fun doGetNewPaperResourcesSource(): Call<List<Source?>?>? //List<Example>

    //https://newsapi.org/v2/sources?apiKey=a3b94d1e4cab435d8096cc0f20060b96
    @GET("sources??apiKey=")
    fun doGetNewPaperResourcesSource1(): Call<List<Source?>?>? //List<Example>

    @GET("/v2/sources")
    fun getNewsPaperSources(@Query("apikey") apiKey: String?): Call<SourceList>

    @GET("/v2/sources")
    fun fetchNewsPaperSources(@Query("apikey") apiKey: String?): Observable<SourceList?>?

    @GET("/v2/everything")
    fun getArticleList(
            @Query("sources") newspaperName: String?,
            @Query("apikey") apiKey: String?): Call<NewsPaperList>

    @GET("/v2/everything")
    fun getArticleListViaSingle(
            @Query("sources") newspaperName: String?,
            @Query("apikey") apiKey: String?): Single<NewsPaperList>

    @GET("/v2/sources")
    fun getNewsPaperSourcesViaSingle(@Query("apikey") apiKey: String?): Single<SourceList>
}