package com.newsapi.newsapp.newsapplication.repo

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Single

interface NewsRepo {

    fun getNewsFeedFromSources(): Single<SourceList>

    fun getArticlesFromSourceName(newsPaperNameId: String): Single<NewsPaperList>

}