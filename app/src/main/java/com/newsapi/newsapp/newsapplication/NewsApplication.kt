package com.newsapi.newsapp.newsapplication

import android.app.Application
import com.newsapi.newsapp.newsapplication.di.AppComponent
import com.newsapi.newsapp.newsapplication.di.AppModule
import com.newsapi.newsapp.newsapplication.di.DataModule

class NewsApplication : Application() {

    private var mAppComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(this@NewsApplication)
//            modules(newsModule)
//            modules(dataModule)
//            modules(viewModels)
//        }
        DaggerAppComponent.builder()
            .apiModule(AppModule(this))
            .appModule(DataModule("https://newsapi.org/"))
            .build()
    }

}