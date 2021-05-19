package com.newsapi.newsapp.newsapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.newsapi.newsapp.newsapplication.model.Article;
import com.newsapi.newsapp.newsapplication.model.NewsPaperList;
import com.newsapi.newsapp.newsapplication.model.Source;
import com.newsapi.newsapp.newsapplication.model.SourceList;
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel;

import java.util.List;

public class Home extends AppCompatActivity implements LifecycleOwner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        testMethod();
        //ViewModelProvider.
        //ViewModelProviders.of(this).get(ClickCounterViewModel.class);

    }

    public void testMethod(){
        try{

            //ViewModelProviders.of(this).get(ClickCounterViewModel.class);
            ArticleViewModel articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
            articleViewModel.getArticleList("Hindu").observe(Home.this, new Observer<NewsPaperList>() {
                @Override
                public void onChanged(@Nullable NewsPaperList articleList) {
                    List<Article>  articles = articleList.getArticles();
                    Log.v("articles value",""+articles.size());

                }
            });
            articleViewModel.getNewsPaperSources().observe(Home.this, new Observer<SourceList>() {
                @Override
                public void onChanged(@Nullable SourceList sourceList) {
                    SourceList  newsPaperObjects = sourceList;
                    Log.v("newsPaperObjects value",""+newsPaperObjects.getSources().size());

                }
            });

        }catch (Exception e){
            Log.v("Exception in downloading from model",e.getMessage());
        }
    }


}
