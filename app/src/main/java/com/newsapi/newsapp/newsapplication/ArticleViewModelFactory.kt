package com.newsapi.newsapp.newsapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel1
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel2

class ArticleViewModelFactory(private val newsManagerValue: NewsManager): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ArticleViewModel2::class.java)){
            return  ArticleViewModel2(newsManager = newsManagerValue) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}