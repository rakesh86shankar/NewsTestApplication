package com.newsapi.newsapp.newsapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapi.newsapp.newsapplication.DataSingleton;
import com.newsapi.newsapp.newsapplication.HomeActivity;
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener;
import com.newsapi.newsapp.newsapplication.R;
import com.newsapi.newsapp.newsapplication.adapters.NewsPaperAdapter;
import com.newsapi.newsapp.newsapplication.model.DividerItemDecoration;
import com.newsapi.newsapp.newsapplication.model.NewsPaperObject;


import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewClickListener {
    RecyclerView recyclerView;
    View view;
    LinearLayoutManager layoutManager;
    List<NewsPaperObject> newsPaperList;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_news_paper, container, false);
        recyclerView =  (RecyclerView)view.findViewById(R.id.list_item_View);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsPaperList  = DataSingleton.getInstance().getNewsPaperSources().getNewsPaperObjects();
        updateView();
    }

    public void updateView() {
        NewsPaperAdapter newsPaperAdapter = new NewsPaperAdapter(newsPaperList, getActivity(), this);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsPaperAdapter);
    }

    public void setNewsPaperObjects(List<NewsPaperObject> newsPaperObjects) {
        newsPaperList = newsPaperObjects;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Log.v("ListView Recyclered>>>"  ,""+position);
        DetailedListFragment detailedListFragment = new DetailedListFragment();
        detailedListFragment.setNewsPaperSources(newsPaperList.get(position).getName());
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame_layout, detailedListFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
