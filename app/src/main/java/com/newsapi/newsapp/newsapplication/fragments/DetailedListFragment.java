package com.newsapi.newsapp.newsapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapi.newsapp.newsapplication.Home;
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener;
import com.newsapi.newsapp.newsapplication.R;
import com.newsapi.newsapp.newsapplication.adapters.NewsPaperAdapter;
import com.newsapi.newsapp.newsapplication.adapters.NewsPaperListAdaper;
import com.newsapi.newsapp.newsapplication.model.Article;
import com.newsapi.newsapp.newsapplication.model.ArticleList;
import com.newsapi.newsapp.newsapplication.model.DividerItemDecoration;
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel;

import java.util.List;

public class DetailedListFragment extends Fragment implements RecyclerViewClickListener, LifecycleOwner {
    View view;
    RecyclerView recyclerView;
    String newsPaperSources;
    LinearLayoutManager layoutManager;
    ArticleList articleListObjects;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_news_paper, container, false);
        recyclerView =  view.findViewById(R.id.list_item_View);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        return view;
    }

    public void setNewsPaperSources(String sources){
        newsPaperSources = sources;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArticleViewModel articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        articleViewModel.getArticleList(newsPaperSources).observe(this, new Observer<ArticleList>() {
            @Override
            public void onChanged(@Nullable ArticleList articleList) {
                List<Article> articles = articleList.getArticles();
                Log.v("articles value",""+articles.size());
                articleListObjects = articleList;
                updateView();
            }
        });

    }

    public void updateView() {
        NewsPaperListAdaper newsPaperAdapter = new NewsPaperListAdaper(articleListObjects.getArticles(), getActivity(), this);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsPaperAdapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}
