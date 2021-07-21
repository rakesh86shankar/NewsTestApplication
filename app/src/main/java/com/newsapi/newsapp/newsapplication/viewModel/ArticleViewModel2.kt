package com.newsapi.newsapp.newsapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ArticleViewModel2(private val newsManager: NewsManager)  : ViewModel() {
    val sourceListResponse =  MutableLiveData<SourceList>()
    val newsListResponse =  MutableLiveData<NewsPaperList>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO);


    init {
        Log.v("Test Check>>>ArticleViewModel2",">>>>")
        getNewsFeedFromSourcesViaFlow()
    }

    private fun getNewsFeedFromSourcesViaFlow() {
        coroutineScope.launch {
            newsManager.getNewsFeedFromSourcesViaFlow().apply {
                sourceListResponse.postValue(this.first())
            }
        }
    }

    fun getNewsArticleViaNewsPaperNameIdViaFlow(newsPaperId: String) {
        coroutineScope.launch {
            newsManager.getArticlesFromSourceNameViaFlow(newsPaperId).apply {
                newsListResponse.postValue(this.first())
            }
        }
    }




    override fun onCleared() {
        super.onCleared()
    }
}