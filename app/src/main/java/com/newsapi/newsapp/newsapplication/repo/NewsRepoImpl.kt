package com.newsapi.newsapp.newsapplication.repo

import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.network.APIServices
import com.newsapi.newsapp.newsapplication.network.APIServices.Companion.API_KEY
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NewsRepoImpl(
        private val networkClient: APIServices
        //,private val db:NewsAppDataBase
) : NewsRepo {

    override fun getArticlesFromSourceName(newsPaperNameId: String): Single<NewsPaperList> = networkClient.newsService.getArticleListViaSingle(newsPaperNameId, API_KEY)

    override fun getNewsFeedFromSources() = networkClient.newsService.getNewsPaperSourcesViaSingle(API_KEY)

}

//    private fun putNewsInDb(newsPaperList: NewsPaperList){
//        db.configNewsList().insert(newsPaperList.toNewsPaperListEntity())
//    }
//
//    private fun getNewsFromDb() = db.configNewsList().getAll().map { it.toNewsPaperList() }