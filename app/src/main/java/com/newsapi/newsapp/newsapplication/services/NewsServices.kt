package com.newsapi.newsapp.newsapplication.services

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("/v2/everything")
    fun getArticleListViaSingle(
            @Query("sources") newspaperName: String?,
            @Query("apikey") apiKey: String): Single<NewsPaperList>

    @GET("/v2/sources")
    fun getNewsPaperSourcesViaSingle(@Query("apikey") apiKey: String): Single<SourceList>
}
