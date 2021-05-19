package com.newsapi.newsapp.newsapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener
import com.newsapi.newsapp.newsapplication.R
import com.newsapi.newsapp.newsapplication.model.Source

class NewsPaperAdapter(
    var newsPaperObjects: List<Source>,
    var context: Context,
    var clickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<NewsPaperAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(
            parent.context
        )
        val v = inflater.inflate(R.layout.layout_list_newspaper, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val source = newsPaperObjects[position]
        holder.itemView.setOnClickListener { v ->
            clickListener.recyclerViewListClicked(v, position)
        }
        holder.txtNewsPaperTitle.text = source.name
    }

    override fun getItemCount(): Int {
        return newsPaperObjects.size
    }

    inner class ViewHolder(var layout: View) : RecyclerView.ViewHolder(layout) {
        // each data item is just a string in this case
        var txtNewsPaperTitle: TextView

        init {
            txtNewsPaperTitle =
                layout.findViewById<View>(R.id.textViewNewsPaper) as TextView
        }
    }

}