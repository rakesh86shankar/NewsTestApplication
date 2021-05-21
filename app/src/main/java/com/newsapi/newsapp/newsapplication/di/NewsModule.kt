package com.newsapi.newsapp.newsapplication.di

import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.manager.NewsManagerImpl
import org.koin.dsl.module

val newsModule = module{
    single { NewsManagerImpl(get()) as NewsManager }
}