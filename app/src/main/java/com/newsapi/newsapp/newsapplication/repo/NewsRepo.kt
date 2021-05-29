package com.newsapi.newsapp.newsapplication.repo

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    fun getNewsFeedFromSources(): Single<SourceList>

    fun getArticlesFromSourceName(newsPaperNameId: String): Single<NewsPaperList>

    fun getNewsFeedFromSourcesViaFlow(): Flow<SourceList>

    fun getArticlesFromSourceNameViaFlow(newsPaperNameId: String): Flow<NewsPaperList>

    fun getNewsFeedFromSourcesViaFlow1(): SourceList

    fun getArticlesFromSourceNameViaFlow1(newsPaperNameId: String): NewsPaperList

}