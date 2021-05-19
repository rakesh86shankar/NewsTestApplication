package com.newsapi.newsapp.newsapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.newsapi.newsapp.newsapplication.model.NewsPaperList;
import com.newsapi.newsapp.newsapplication.model.Source;
import com.newsapi.newsapp.newsapplication.model.SourceList;
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel;

import java.util.List;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        testMethod();
        /*new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
        }
    }, SPLASH_TIME_OUT);*/
}

    public void testMethod(){
        try{

            //ViewModelProviders.of(this).get(ClickCounterViewModel.class);
            ArticleViewModel articleViewModel = ViewModelProviders.of(SplashScreen.this).get(ArticleViewModel.class);
            /*articleViewModel.getArticleList().observe(SplashScreen.this, new Observer<ArticleList>() {
                @Override
                public void onChanged(@Nullable ArticleList articleList) {
                    List<Article> articles = articleList.getArticles();
                    Log.v("articles value",""+articles.size());

                }
            });*/
            articleViewModel.getNewsPaperSources().observe(SplashScreen.this, new Observer<SourceList>() {

                @Override
                public void onChanged(SourceList sourceList) {
                    DataSingleton.getInstance().setNewsPaperSources(sourceList.getSources());
                    Intent i = new Intent(SplashScreen.this, NewAPIActivity.class);
                    startActivity(i);
                }
            });
        }catch (Exception e){
            Log.v("Exception in downloading from model",e.getMessage());
        }
    }
}
