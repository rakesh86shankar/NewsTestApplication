package com.newsapi.newsapp.newsapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener;
import com.newsapi.newsapp.newsapplication.R;
import com.newsapi.newsapp.newsapplication.model.NewsPaperObject;

import java.util.List;

public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperAdapter.ViewHolder> {
    List<NewsPaperObject> newsPaperObjects;
    RecyclerViewClickListener clickListener;
    Context context;

    public NewsPaperAdapter(List<NewsPaperObject> newsPaperObjects, Context appContext, RecyclerViewClickListener clickListener) {
        this.newsPaperObjects = newsPaperObjects;
        context = appContext;
        this.clickListener = clickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.layout_list_newspaper, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        NewsPaperObject newsPaperObject = newsPaperObjects.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.recyclerViewListClicked(v, position);
            }
        });
        holder.txtNewsPaperTitle.setText(newsPaperObject.getName());

    }

    @Override
    public int getItemCount() {
        return newsPaperObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtNewsPaperTitle;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtNewsPaperTitle = (TextView) v.findViewById(R.id.textViewNewsPaper);
        }
    }

}
