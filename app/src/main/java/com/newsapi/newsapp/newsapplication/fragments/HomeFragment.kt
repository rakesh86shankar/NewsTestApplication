package com.newsapi.newsapp.newsapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsapi.newsapp.newsapplication.DataSingleton
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener
import com.newsapi.newsapp.newsapplication.R
import com.newsapi.newsapp.newsapplication.adapters.NewsPaperAdapter
import com.newsapi.newsapp.newsapplication.model.Source
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel1
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), RecyclerViewClickListener,LifecycleOwner {
    private lateinit var recyclerView: RecyclerView
    var layoutManager: LinearLayoutManager? = null
    var newsPaperList: List<Source>? = emptyList()
    private val mViewModel: ArticleViewModel1 by  viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_news_paper, container, false)
        return view
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
        mViewModel.sourceListResponse.observe(viewLifecycleOwner,Observer<SourceList>{
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

    override fun recyclerViewListClicked(v: View?, position: Int) {
        Log.v("ListView Recyclered>>>", "" + position)
        val detailedListFragment = DetailedListFragment()
        detailedListFragment.setNewsPaperSources(position)
        val fragmentTransaction =
            parentFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        fragmentTransaction.replace(R.id.frame_layout, detailedListFragment).addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }
}