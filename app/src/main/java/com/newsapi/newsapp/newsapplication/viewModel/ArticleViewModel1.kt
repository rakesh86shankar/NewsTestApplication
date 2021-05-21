package com.newsapi.newsapp.newsapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class ArticleViewModel1 constructor(private val newsManager: NewsManager) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val newsApiResponse = MutableLiveData<NewsPaperList>()

    init {
        Log.v("Test Check>>>",">>>>")
    }

    fun getNewsFeedFromSources(): Single<SourceList> {
        return newsManager.getNewsFeedFromSources()
    }

    fun getNewsArticleViaNewsPaperNameId(newsPaperId: String ): Single<NewsPaperList> {
        return newsManager.getArticlesFromSourceName(newsPaperId)
    }

    fun onDetach() {
        compositeDisposable.dispose()
    }
}