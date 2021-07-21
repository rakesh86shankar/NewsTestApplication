package com.newsapi.newsapp.newsapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.network.apiResult
import io.reactivex.Single
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

class ArticleViewModel1 constructor(private val newsManager: NewsManager) : ViewModel() {
    val sourceListResponse =  MutableLiveData<SourceList>()
    val newsListResponse =  MutableLiveData<NewsPaperList>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO);

    init {
        Log.v("Test Check>>>",">>>>")
        getNewsFeedFromSourcesViaFlow()
    }

    fun getNewsFeedFromSources(): Single<SourceList> {
        return newsManager.getNewsFeedFromSources()
    }

    fun getNewsFeedFromSources1() {
        coroutineScope.launch {
            newsManager.getNewsFeedFromSourcesViaFlow1().apply {
                sourceListResponse.postValue(this)
            }
        }
    }

    fun getNewsArticleViaNewsPaperNameId(newsPaperId: String ): Single<NewsPaperList> {
        return newsManager.getArticlesFromSourceName(newsPaperId)
    }

    private fun getNewsFeedFromSourcesViaFlow() {
        coroutineScope.launch {
            newsManager.getNewsFeedFromSourcesViaFlow().apply {
                sourceListResponse.postValue(this.first())
            }
        }
    }

//    private fun getNewsFeedFromSourcesViaFlow() {
//        coroutineScope.launch {
//            newsManager.getNewsFeedFromSourcesViaFlow()
//                    .catch { exception -> sourceListResponse.postValue(null) }
//                    .collect {
//                        sourceListResponse.postValue(it)
//                    }
//        }
//    }


    fun getNewsArticleViaNewsPaperNameIdViaFlow(newsPaperId: String) {
        coroutineScope.launch {
            newsManager.getArticlesFromSourceNameViaFlow(newsPaperId).apply {
                newsListResponse.postValue(this.first())
            }
        }
    }

//    fun getNewsArticleViaNewsPaperNameIdViaFlow(newsPaperId: String) {
//        coroutineScope.launch {
//            newsManager.getArticlesFromSourceNameViaFlow(newsPaperId)
//                    .catch { exception -> newsListResponse.postValue(null) }
//                    .collect { newsListResponse.postValue(it) }
//        }
//    }

    fun getNewsArticleViaNewsPaperNameIdViaFlow1(newsPaperId: String): apiResult<NewsPaperList>? {
        var apiresult: apiResult<NewsPaperList>? = null
        coroutineScope.launch {
            newsManager.getArticlesFromSourceNameViaFlow(newsPaperId).
                 collect{
                     apiresult = if(!it.articles.isNullOrEmpty()){
                         apiResult.Success(it)
                     } else {
                         apiResult.Error(Exception("Error in downloading"))
                     }
                }
            }
        return apiresult
    }

    private fun getNewsFeedFromSourcesViaFlow1() {
        viewModelScope.launch {
             val result = newsManager.getNewsFeedFromSourcesViaFlow1()
             if(!result.sources.isNullOrEmpty()){
                 sourceListResponse.postValue(result)
             }
        }
    }

    fun onDetach() {
        coroutineScope.cancel()
    }
}