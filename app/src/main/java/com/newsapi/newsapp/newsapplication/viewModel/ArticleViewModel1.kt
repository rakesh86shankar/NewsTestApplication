package com.newsapi.newsapp.newsapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.network.apiResult
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ArticleViewModel1 constructor(private val newsManager: NewsManager) : ViewModel() {
    val sourceListResponse =  MutableLiveData<SourceList>()
    val newsListResponse =  MutableLiveData<NewsPaperList>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO);
    val sourceListResponse1 =  MutableLiveData<SourceList>()

    init {
        Log.v("Test Check>>>",">>>>")
        getNewsFeedFromSourcesViaFlow()
    }

    fun getNewsFeedFromSources(): Single<SourceList> {
        return newsManager.getNewsFeedFromSources()
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

//    fun getNewsArticleViaNewsPaperNameIdViaFlow1(newsPaperId: String): apiResult<NewsPaperList>? {
//        var apiresult: apiResult<NewsPaperList>? = null
//        coroutineScope.launch {
//            newsManager.getArticlesFromSourceNameViaFlow(newsPaperId).apply {
//                 collect{
//                     apiresult = if(!it.articles.isNullOrEmpty()){
//                         apiResult.Success(it)
//                     } else {
//                         apiResult.Error(Exception("Error in downloading"))
//                     }
//
//                }
//            }
//        }
//        return apiresult
//    }

//    private fun getNewsFeedFromSourcesViaFlow1(): apiResult<SourceList>? {
//        var sourceResult: apiResult<SourceList>? = null
//        coroutineScope.launch {
//            newsManager.getNewsFeedFromSourcesViaFlow().apply {
//                collect{
//                    sourceResult = if(!it.sources.isNullOrEmpty()){
//                        apiResult.Success(it)
//                    } else {
//                        apiResult.Error(Exception("Error in downloading"))
//                    }
//
//                }
//            }
//        }
//        return sourceResult
//    }

    fun onDetach() {
        coroutineScope.cancel()
    }
}