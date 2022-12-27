package com.newsapi.newsapp.newsapplication.di

import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.manager.NewsManagerImpl
import com.newsapi.newsapp.newsapplication.network.APIServices
import com.newsapi.newsapp.newsapplication.repo.NewsRepo
import com.newsapi.newsapp.newsapplication.repo.NewsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.koin.dsl.module
import javax.inject.Named

//val newsModule = module{
//    single { NewsManagerImpl(get()) as NewsManager }
//}

@Module
class NewsModule {

    @Provides
    @Named("newsManager")
    fun provideNewsManager(apiServices: APIServices): NewsManager {
        return NewsRepoImpl(apiServices) as NewsManager
    }
}
