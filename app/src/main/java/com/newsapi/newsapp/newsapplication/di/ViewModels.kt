package com.newsapi.newsapp.newsapplication.di

import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel1
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { ArticleViewModel1(get()) }
}