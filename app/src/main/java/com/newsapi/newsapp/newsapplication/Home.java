package com.newsapi.newsapp.newsapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.newsapi.newsapp.newsapplication.model.Article;
import com.newsapi.newsapp.newsapplication.model.ArticleList;
import com.newsapi.newsapp.newsapplication.model.NewsPaperList;
import com.newsapi.newsapp.newsapplication.model.NewsPaperObject;
import com.newsapi.newsapp.newsapplication.network.APIClient;
import com.newsapi.newsapp.newsapplication.network.APIInterface;
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            articleViewModel.getArticleList("xxx").observe(Home.this, new Observer<ArticleList>() {
                @Override
                public void onChanged(@Nullable ArticleList articleList) {
                    List<Article>  articles = articleList.getArticles();
                    Log.v("articles value",""+articles.size());

                }
            });
            articleViewModel.getNewsPaperSources().observe(Home.this, new Observer<NewsPaperList>() {
                @Override
                public void onChanged(@Nullable NewsPaperList newsPaperList) {
                    List<NewsPaperObject>  newsPaperObjects = newsPaperList.getSources();
                    Log.v("newsPaperObjects value",""+newsPaperObjects.size());

                }
            });

        }catch (Exception e){
            Log.v("Exception in downloading from model",e.getMessage());
        }
    }


}
