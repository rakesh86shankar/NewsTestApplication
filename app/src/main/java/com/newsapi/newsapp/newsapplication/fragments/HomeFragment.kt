package com.newsapi.newsapp.newsapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsapi.newsapp.newsapplication.ArticleViewModelFactory
import com.newsapi.newsapp.newsapplication.DataSingleton
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener
import com.newsapi.newsapp.newsapplication.R
import com.newsapi.newsapp.newsapplication.adapters.NewsPaperAdapter
import com.newsapi.newsapp.newsapplication.di.dataModule
import com.newsapi.newsapp.newsapplication.manager.NewsManager
import com.newsapi.newsapp.newsapplication.manager.NewsManagerImpl
import com.newsapi.newsapp.newsapplication.model.Source
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.network.APIServices
import com.newsapi.newsapp.newsapplication.repo.NewsRepoImpl
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel1
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel2
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), RecyclerViewClickListener {
    private lateinit var recyclerView: RecyclerView
    var layoutManager: LinearLayoutManager? = null
    var newsPaperList: List<Source>? = emptyList()
    private val mViewModel: ArticleViewModel1 by  viewModel()
    private lateinit var fragmentView: View
    private lateinit var navHostFragment: NavController
    private lateinit var articleViewModelFactory: ArticleViewModelFactory
    private val newsManager:NewsManager  = NewsManagerImpl(NewsRepoImpl(APIServices("https://newsapi.org/")))
    private lateinit var articleViewModel2: ArticleViewModel2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentView = inflater.inflate(R.layout.layout_news_paper, container, false)
        initializeViewModel()
        navHostFragment = NavHostFragment.findNavController(this)
        return fragmentView
    }


    private fun initializeViewModel(){
        articleViewModelFactory = ArticleViewModelFactory(newsManager)
        articleViewModel2 =  ViewModelProvider(this, articleViewModelFactory).get(ArticleViewModel2::class.java)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        //newsPaperList = DataSingleton.getInstance().newsPaperSources
        recyclerView = view.findViewById(R.id.list_item_View) as RecyclerView
        recyclerView.addItemDecoration( DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        listenToViewModel()
    }


    private fun listenToViewModel() {
      /*  mViewModel.getNewsFeedFromSources().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t1: SourceList?, t2: Throwable? ->
            if (t1 != null) {
                newsPaperList = t1.sources
                updateView()
            }
            Log.v("Error", "" + t2)
        }*/
        articleViewModel2.sourceListResponse.observe(viewLifecycleOwner,Observer<SourceList>{
            if (it != null) {
                Log.v("Success>>>", "null")
                newsPaperList = it.sources
                updateView()
                DataSingleton.getInstance().setNewsPaperSources(it.sources?: emptyList())
            } else {
                Log.v("Error>>>", "null")
            }

        })
    }

    fun updateView() {
        val newsPaperAdapter =
            activity?.applicationContext?.let { NewsPaperAdapter(newsPaperList?: emptyList(), it, this) }
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = newsPaperAdapter
    }

    fun setNewsPaperObjects(newsPaperObjects: List<Source>?) {
        newsPaperList = newsPaperObjects
    }

    override fun recyclerViewListClicked(v: View, position: Int) {
        Log.v("ListView Recyclered>>>", "" + position)
//        val detailedListFragment = DetailedListFragment()
//        detailedListFragment.setNewsPaperSources(position)
//        val fragmentTransaction =
//            parentFragmentManager.beginTransaction()
//        fragmentTransaction.setCustomAnimations(
//            android.R.anim.fade_in,
//            android.R.anim.fade_out
//        )
//        fragmentTransaction.replace(R.id.frame_layout, detailedListFragment).addToBackStack(null)
//        fragmentTransaction.commitAllowingStateLoss()
        val bundle = bundleOf("position" to position)
        navHostFragment.navigate(R.id.action_main_fragment_to_second_fragment,bundle)
    }
}