package com.newsapi.newsapp.newsapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsapi.newsapp.newsapplication.DataSingleton
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener
import com.newsapi.newsapp.newsapplication.R
import com.newsapi.newsapp.newsapplication.adapters.NewsPaperListAdaper
import com.newsapi.newsapp.newsapplication.model.Article
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel1
import org.koin.android.viewmodel.ext.android.viewModel

class DetailedListFragment : Fragment(),
    RecyclerViewClickListener, LifecycleOwner {
    private lateinit var recyclerView: RecyclerView
    var newsPaperItemPositon: Int? = 0
    var layoutManager: LinearLayoutManager? = null
    var articleListObjects: List<Article>? = emptyList()
    private val mViewModel: ArticleViewModel1 by  viewModel()

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
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                getParentFragmentManager().popBackStack()
            }
        })
        val position = arguments?.getInt("position") ?: 0
        setNewsPaperSources(position)
        recyclerView = view.findViewById(R.id.list_item_View) as RecyclerView
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

        val newsSourceId = DataSingleton.getInstance().newsPaperSources[newsPaperItemPositon
                ?: 0].id
        Log.v("NewsSource>>>", ">>" + newsSourceId)
        lifecycleScope.launchWhenCreated {
            mViewModel.getNewsArticleViaNewsPaperNameIdViaFlow(newsSourceId)
        }

        /*
        val articleViewModel = ViewModelProviders.of(this).get(
                ArticleViewModel::class.java
        )


        articleViewModel.getArticleList("Hindu")?.observe(viewLifecycleOwner, Observer {
            it.let {
                if (!it?.articles.isNullOrEmpty()) {
                    articleListObjects = it?.articles
                    updateView()
                }
            }
        })*/

        /*
        articleViewModel.getArticleListViaSingle(newsSourceId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t1: NewsPaperList?, t2: Throwable? ->
                    if (t1 != null) {
                        articleListObjects = t1.articles
                        updateView()
                    }
                    Log.v("Error", "Ok Fine" + t2)
                }
        */
        mViewModel.newsListResponse.observe(viewLifecycleOwner, Observer<NewsPaperList> {
            if (it != null) {
                Log.v("Success>>>", "null")
                articleListObjects = it.articles
                updateView()
            } else {
                Log.v("Error>>>", "null")
            }

        })
    }

    private fun updateView() {
        activity?.applicationContext?.let {
            val newsPaperAdapter =
                NewsPaperListAdaper(articleListObjects ?: emptyList(), it, this)
            recyclerView.adapter = newsPaperAdapter
        }
    }

    override fun recyclerViewListClicked(
            v: View,
            position: Int
    ) {
    }
}