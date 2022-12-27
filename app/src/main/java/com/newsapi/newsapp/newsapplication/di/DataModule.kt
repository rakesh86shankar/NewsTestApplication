package com.newsapi.newsapp.newsapplication.di

import android.app.Application
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.network.APIServices
import com.newsapi.newsapp.newsapplication.repo.NewsRepo
import com.newsapi.newsapp.newsapplication.repo.NewsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Constructor
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

//val dataModule = module {
//    single {
//        APIServices("https://newsapi.org/")
//    }
//
//    single { NewsRepoImpl(get()/*, get()*/) as NewsRepo }
//
//    //single { NewsAppRoomDataBase.buildDatabase(get()) as NewsAppDataBase}
//}

@Module
class DataModule @Inject constructor(private val mBaseURL: String) {

    @Singleton
    @Provides
    @Named("apiServices")
    fun provideAPIServices(): APIServices = APIServices(mBaseURL)

    @Provides
    @Named("newsRepo")
    fun provideNewsRepo(): NewsRepo {
        return NewsRepoImpl(provideAPIServices())
    }
}