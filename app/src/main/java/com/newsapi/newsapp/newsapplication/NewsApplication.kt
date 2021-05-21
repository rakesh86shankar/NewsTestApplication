package com.newsapi.newsapp.newsapplication

import android.app.Application
import com.newsapi.newsapp.newsapplication.di.dataModule
import com.newsapi.newsapp.newsapplication.di.newsModule
import com.newsapi.newsapp.newsapplication.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            modules(newsModule)
            modules(dataModule)
            modules(viewModels)
        }
    }

}