package com.newsapi.newsapp.newsapplication.network;

/**
 * Created by rakesh sankar on 9/12/2017.
 */

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newsapi.newsapp.newsapplication.model.Article;
import com.newsapi.newsapp.newsapplication.model.ArticleList;
import com.newsapi.newsapp.newsapplication.model.NewsPaperList;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String ROOT_URL  = "https://newsapi.org/v2/";
    public static String APIKey = "a3b94d1e4cab435d8096cc0f20060b96";//"a3b94d1e4cab435d8096cc0f20060b96";//a3b94d1e4cab435d8096cc0f20060b96
    private static Retrofit retrofit = null;
    private static MutableLiveData<ArticleList > articleListMutableLiveData = new MutableLiveData<ArticleList >();
    private static MutableLiveData<NewsPaperList > newsPaperListMutableLiveData = new MutableLiveData<NewsPaperList >();

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();



        return retrofit;
    }

    public MutableLiveData<ArticleList> downloadArticleList(String paperName) {
        try {
            APIInterface apiService =
                    APIClient.getClient().create(APIInterface.class);
            Call<ArticleList> call = apiService.getArticleList("associated-press",APIClient.APIKey);
            call.enqueue(new Callback<ArticleList>() {
                @Override
                public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                    ArticleList articleList = response.body();
                    articleListMutableLiveData.setValue(articleList);
                }

                @Override
                public void onFailure(Call<ArticleList> call, Throwable t) {
                    Log.v("OnFailure Issues>>>.",t.getMessage());
                }
            });

        } catch (Exception e) {
            Log.v("Exception in getting Article Information",e.getMessage());
        }
    return articleListMutableLiveData;
    }

    public  MutableLiveData<NewsPaperList > getNewsPaperSources() {
        try {
            APIInterface apiService =
                    APIClient.getClient().create(APIInterface.class);

            Call<NewsPaperList> call = apiService.getNewsPaperSources(APIClient.APIKey);
            call.enqueue(new Callback<NewsPaperList>() {
                @Override
                public void onResponse(Call<NewsPaperList> call, Response<NewsPaperList> response) {
                    Log.v("API Response", response.body().toString());
                    NewsPaperList newsPaperList = response.body();
                    newsPaperListMutableLiveData.setValue(newsPaperList);
                }

                @Override
                public void onFailure(Call<NewsPaperList> call, Throwable t) {
                    HttpUrl url = call.request().url();

                    Log.v("API Response Url", url.toString());
                    Log.v("API Response Error", t.toString());
                }
            });
        }catch (Exception e){
            Log.v("Exception in getting newsPaper Information",e.getMessage());
        }
        return newsPaperListMutableLiveData;
    }

}
