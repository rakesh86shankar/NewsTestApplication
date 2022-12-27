package com.newsapi.newsapp.newsapplication.di

import com.newsapi.newsapp.newsapplication.fragments.HomeFragment
import dagger.Component

@Component(modules = [AppModule::class, NewsModule::class, DataModule::class])
interface AppComponent {

    fun inject(fragment: HomeFragment)

}