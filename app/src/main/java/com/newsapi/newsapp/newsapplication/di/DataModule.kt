package com.newsapi.newsapp.newsapplication.di

import com.newsapi.newsapp.newsapplication.network.APIServices
import com.newsapi.newsapp.newsapplication.repo.NewsRepo
import com.newsapi.newsapp.newsapplication.repo.NewsRepoImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        APIServices("https://newsapi.org/")
    }

    single { NewsRepoImpl(get()/*, get()*/) as NewsRepo }

    //single { NewsAppRoomDataBase.buildDatabase(get()) as NewsAppDataBase}
}