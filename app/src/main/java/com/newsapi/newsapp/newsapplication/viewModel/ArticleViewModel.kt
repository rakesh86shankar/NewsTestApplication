package com.newsapi.newsapp.newsapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.newsapi.newsapp.newsapplication.model.Article
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.Source
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.network.APIClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ArticleViewModel(application: Application) : AndroidViewModel(application) {
    private var articleListLiveData: MutableLiveData<NewsPaperList?>? = null
    private var newsPaperListMutableLiveData:MutableLiveData<SourceList?>? = null

    private val apiClient = APIClient()

    fun getArticleList(newsPaperName: String?): MutableLiveData<NewsPaperList?>? {
        if (articleListLiveData == null) {
            return apiClient.downloadArticleList(newsPaperName)
        }
        articleListLiveData = MutableLiveData<NewsPaperList?>()
        return articleListLiveData
    }

    fun getNewsPaperSources() : MutableLiveData<SourceList?>? {
        if (newsPaperListMutableLiveData == null) {
            return apiClient.getNewsPaperResources()
        }
        newsPaperListMutableLiveData = MutableLiveData<SourceList?>()
        return newsPaperListMutableLiveData
    }

    fun getArticleListViaSingle(newsPaperName: String?): Single<NewsPaperList> {
        return apiClient.downloadArticleListViaSingle(newsPaperName).subscribeOn(Schedulers.io())
    }

    fun getNewsPaperSourcesSingle() : Single<SourceList> {
       return  apiClient.getNewsPaperResourcesViaSingle().subscribeOn(Schedulers.io())
    }
}