package com.newsapi.newsapp.newsapplication.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener;
import com.newsapi.newsapp.newsapplication.R;
import com.newsapi.newsapp.newsapplication.model.Article;

import java.util.List;

public class NewsPaperListAdaper extends RecyclerView.Adapter<NewsPaperListAdaper.ViewHolder> {
    Context context;
    RecyclerViewClickListener clickListener;
    List<Article> articlesList;

    public NewsPaperListAdaper(List<Article> articlesListObjects, Context appContext, RecyclerViewClickListener clickListener) {
        this.articlesList = articlesListObjects;
        context = appContext;
        this.clickListener = clickListener;
    }

    @Override
    public NewsPaperListAdaper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.layout_list_newspaper, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Article  articlesListObject= articlesList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.recyclerViewListClicked(v, position);
            }
        });
        holder.txtNewsPaperArticle.setText(articlesListObject.getTitle());
    }


    @Override
    public int getItemCount() {
        return articlesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNewsPaperArticle;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtNewsPaperArticle = (TextView) v.findViewById(R.id.textViewNewsPaper);
        }
    }
}
