package com.newsapi.newsapp.newsapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener
import com.newsapi.newsapp.newsapplication.R
import com.newsapi.newsapp.newsapplication.adapters.NewsPaperListAdaper
import com.newsapi.newsapp.newsapplication.model.Article
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.Source
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel

class DetailedListFragment : Fragment(),
    RecyclerViewClickListener, LifecycleOwner {
    private lateinit var recyclerView: RecyclerView
    var newsPaperItemPositon: Int? = 0
    var layoutManager: LinearLayoutManager? = null
    var articleListObjects: List<Article>? = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_news_paper, container, false)
        return view
    }

    fun setNewsPaperSources(position: Int) {
        newsPaperItemPositon = position
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list_item_View) as RecyclerView
        recyclerView.addItemDecoration( DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        val articleViewModel = ViewModelProviders.of(this).get(
            ArticleViewModel::class.java
        )
//        articleViewModel.getArticleList("Hindu")
//            .observe(requireActivity(), object : Observer<NewsPaperList?> {
//                fun onChanged(articleList: NewsPaperList?) {
//                    val articles: List<Article> = articleList.getArticles()
//                    Log.v("articles value", "" + articles.size)
//                    articleListObjects = articleList
//                    updateView()
//                }
//            })
//
        articleViewModel.getArticleList("Hindu")?.observe(viewLifecycleOwner, Observer {
                    it.let {
                        if(it.articles.isNullOrEmpty() == false){
                            articleListObjects = it.articles
                            updateView()
                        }
                    }
        } )
    }

    fun updateView() {
        activity?.applicationContext?.let {
            val newsPaperAdapter =
                NewsPaperListAdaper(articleListObjects?: emptyList(), it, this)
            layoutManager = LinearLayoutManager(activity)
            recyclerView!!.layoutManager = layoutManager
            recyclerView!!.adapter = newsPaperAdapter
        }


    }

    override fun recyclerViewListClicked(
        v: View?,
        position: Int
    ) {
    }
}