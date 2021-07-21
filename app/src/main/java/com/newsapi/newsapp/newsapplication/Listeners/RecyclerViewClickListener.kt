package com.newsapi.newsapp.newsapplication.Listeners

import android.view.View

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v: View, position: Int)
}