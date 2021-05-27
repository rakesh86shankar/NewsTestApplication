package com.newsapi.newsapp.newsapplication.manager

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.repo.NewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class NewsManagerImpl(
        private val _newsRepo: NewsRepo
) : NewsManager {

    override fun getNewsFeedFromSources() = _newsRepo.getNewsFeedFromSources().onErrorReturn { (SourceList.empty()) }

    override fun getArticlesFromSourceName(newsPaperNameId: String) = _newsRepo.getArticlesFromSourceName(newsPaperNameId).onErrorReturn { (NewsPaperList.empty()) }

    override fun getNewsFeedFromSourcesViaFlow(): Flow<SourceList> = _newsRepo.getNewsFeedFromSourcesViaFlow()

    override fun getArticlesFromSourceNameViaFlow(newsPaperNameId: String) = _newsRepo.getArticlesFromSourceNameViaFlow(newsPaperNameId)



}