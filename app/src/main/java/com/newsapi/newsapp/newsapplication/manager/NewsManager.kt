package com.newsapi.newsapp.newsapplication.manager

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface NewsManager {

    fun getNewsFeedFromSources(): Single<SourceList>

    fun getArticlesFromSourceName(newsPaperNameId: String): Single<NewsPaperList>


    fun getNewsFeedFromSourcesViaFlow(): Flow<SourceList>

    fun getArticlesFromSourceNameViaFlow(newsPaperNameId: String): Flow<NewsPaperList>

}