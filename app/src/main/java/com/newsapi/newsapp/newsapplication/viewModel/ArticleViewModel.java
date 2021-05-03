package com.newsapi.newsapp.newsapplication.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.newsapi.newsapp.newsapplication.model.ArticleList;
import com.newsapi.newsapp.newsapplication.model.NewsPaperList;
import com.newsapi.newsapp.newsapplication.network.APIClient;

public class ArticleViewModel extends AndroidViewModel {

    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<ArticleList> articleListLiveData;
    private MutableLiveData<NewsPaperList> newsPaperListMutableLiveData;
    private APIClient apiClient = new APIClient();

    public MutableLiveData<ArticleList> getArticleList(String newsPaperName) {
        if(articleListLiveData == null){
            articleListLiveData = apiClient.downloadArticleList(newsPaperName);
        }
        return articleListLiveData;
    }

    public MutableLiveData<NewsPaperList> getNewsPaperSources() {
        if(newsPaperListMutableLiveData == null){
            newsPaperListMutableLiveData = apiClient.getNewsPaperSources();
        }
        return newsPaperListMutableLiveData;
    }

}
