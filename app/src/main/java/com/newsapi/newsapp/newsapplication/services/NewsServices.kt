package com.newsapi.newsapp.newsapplication.services

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("/v2/everything")
    fun getArticleListViaSingle(
            @Query("sources") newspaperName: String?,
            @Query("apikey") apiKey: String): Single<NewsPaperList>

    @GET("/v2/sources")
    fun getNewsPaperSourcesViaSingle(@Query("apikey") apiKey: String): Single<SourceList>


    @GET("/v2/everything")
    suspend fun getArticleListViaFlow(
        @Query("sources") newspaperName: String?,
        @Query("apikey") apiKey: String): NewsPaperList

    @GET("/v2/sources")
    suspend fun getNewsPaperSourcesViaFlow(@Query("apikey") apiKey: String): SourceList
}
