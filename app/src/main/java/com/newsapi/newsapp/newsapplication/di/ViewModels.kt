package com.newsapi.newsapp.newsapplication.di

import androidx.lifecycle.ViewModel
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.repo.NewsRepo
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel1
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import javax.inject.Named

//val viewModels = module {
//    viewModel { ArticleViewModel1(get()) }
//}

@Module
class ViewModels {

    @Provides
    @Named("articleViewModel")
    fun provideArticleViewModel1(newsManager: NewsManager): ViewModel {
        return ArticleViewModel1(newsManager)
    }
}