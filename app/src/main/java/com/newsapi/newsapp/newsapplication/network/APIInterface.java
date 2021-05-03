package com.newsapi.newsapp.newsapplication.network;

/**
 * Created by rakesh sankar on 9/12/2017.
 */



import com.newsapi.newsapp.newsapplication.model.ArticleList;
import com.newsapi.newsapp.newsapplication.model.NewsPaperList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    //https://newsapi.org/v1/sources
    //String url = "https://newsapi.org/v1/articles?source=bbc-sport&sortBy=latest&apiKey=a3b94d1e4cab435d8096cc0f20060b96

    @GET("/?sources=techcrunch&apiKey=be9c63bd0fa94e8b85836fed535d73d0")
    Call<NewsPaperList> doGetNewPaperResources();


    @GET("/v1/sources")
    public Call<NewsPaperList> doGetNewPaperResourcesSource();//List<Example>

    //https://newsapi.org/v2/sources?apiKey=a3b94d1e4cab435d8096cc0f20060b96
    @GET("sources??apiKey=")
    public Call<NewsPaperList> doGetNewPaperResourcesSource1();//List<Example>


    @GET("/v2/sources")
    public Call<NewsPaperList> getNewsPaperSources(@Query("apikey") String apiKey);


    @GET("/v2/sources")
    Observable<NewsPaperList> fetchNewsPaperSources(@Query("apikey") String apiKey);

    @GET("/v2/everything")
    public Call<ArticleList> getArticleList(
            @Query("sources") String newspaperName,
            @Query("apikey") String apiKey);

}
