package com.newsapi.newsapp.newsapplication.manager

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Single

interface NewsManager {

    fun getNewsFeedFromSources(): Single<SourceList>

    fun getArticlesFromSourceName(newsPaperNameId: String): Single<NewsPaperList>

}