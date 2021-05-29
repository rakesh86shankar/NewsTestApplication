package com.newsapi.newsapp.newsapplication.repo

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.Source
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.network.APIServices
import com.newsapi.newsapp.newsapplication.network.APIServices.Companion.API_KEY
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepoImpl(
        private val networkClient: APIServices
        //,private val db:NewsAppDataBase
) : NewsRepo {

    override fun getArticlesFromSourceName(newsPaperNameId: String): Single<NewsPaperList> = networkClient.newsService.getArticleListViaSingle(newsPaperNameId, API_KEY)

    override fun getNewsFeedFromSources() = networkClient.newsService.getNewsPaperSourcesViaSingle(API_KEY)

    override fun getArticlesFromSourceNameViaFlow(newsPaperNameId: String): Flow<NewsPaperList> {
        return flow {
            while (true) {
                val latestNews = networkClient.newsService.getArticleListViaFlow(newsPaperNameId, API_KEY)
                emit(latestNews) // Emits the result of the request to the flow
            }
        }
    }

    override fun getNewsFeedFromSourcesViaFlow(): Flow<SourceList> {
        return flow {
            while (true) {
                val sourceList = networkClient.newsService.getNewsPaperSourcesViaFlow(API_KEY)
                emit(sourceList) // Emits the result of the request to the flow
            }
        }
    }

    override fun getArticlesFromSourceNameViaFlow1(newsPaperNameId: String): NewsPaperList =  runBlocking { withContext(Dispatchers.IO) {networkClient.newsService.getArticleListViaFlow(newsPaperNameId, API_KEY)} }

    override fun getNewsFeedFromSourcesViaFlow1():SourceList = runBlocking { withContext(Dispatchers.IO) {networkClient.newsService.getNewsPaperSourcesViaFlow(API_KEY)} }
}

//    private fun putNewsInDb(newsPaperList: NewsPaperList){
//        db.configNewsList().insert(newsPaperList.toNewsPaperListEntity())
//    }
//
//    private fun getNewsFromDb() = db.configNewsList().getAll().map { it.toNewsPaperList() }