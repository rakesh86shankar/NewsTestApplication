package com.newsapi.newsapp.newsapplication.manager

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.repo.NewsRepo

class NewsManagerImpl(
        private val _newsRepo: NewsRepo
) : NewsManager {

    override fun getNewsFeedFromSources() = _newsRepo.getNewsFeedFromSources().onErrorReturn { (SourceList.empty()) }

    override fun getArticlesFromSourceName(newsPaperNameId: String) = _newsRepo.getArticlesFromSourceName(newsPaperNameId).onErrorReturn { (NewsPaperList.empty()) }

}