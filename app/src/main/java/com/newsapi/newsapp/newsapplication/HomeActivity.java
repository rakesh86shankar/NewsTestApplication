package com.newsapi.newsapp.newsapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.newsapi.newsapp.newsapplication.Listeners.RecyclerViewClickListener;
import com.newsapi.newsapp.newsapplication.fragments.HomeFragment;
import com.newsapi.newsapp.newsapplication.model.Article;
import com.newsapi.newsapp.newsapplication.model.ArticleList;
import com.newsapi.newsapp.newsapplication.model.NewsPaperArray;
import com.newsapi.newsapp.newsapplication.model.NewsPaperGenre;
import com.newsapi.newsapp.newsapplication.model.NewsPaperObject;
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,RecyclerViewClickListener {
    NavigationView navigationView;
    List<NewsPaperGenre> genres = new ArrayList<>();
    NewsPaperArray newsPaperArray = null;
    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle b = getIntent().getExtras();
        newsPaperArray = b.getParcelable("NewsPaperSources");
        genres = parseGenreData(newsPaperArray.getNewsPaperObjects());

        Menu menu = navigationView.getMenu();
        for (int i = 0; i < genres.size(); i++) {
            menu.add(0, i, 0, genres.get(i).getTitle());
        }
        try {
            homeFragment = new HomeFragment();
            homeFragment.setNewsPaperObjects( genres.get(0).getPaperObjects());

           // homeFragment.setActivityCallBack(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentHolder, homeFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.home, menu);
        for (int i = 0; i < genres.size(); i++) {
            menu.add(0, i, 0, genres.get(i).getTitle());
        }
        // menu.add(0, genres., 0, "Option1")

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("Curremt MenuItem Selected",""+item.getItemId());
        NewsPaperGenre genre =  genres.get(item.getItemId());
        Log.v("Selected Genre",genre.getTitle());
        return super.onOptionsItemSelected(item);
    }*/

    public void loadArticleFeed(String newsPaper) {
        try {

            //ViewModelProviders.of(this).get(ClickCounterViewModel.class);
            ArticleViewModel articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
            articleViewModel.getArticleList(newsPaper).observe(HomeActivity.this, new Observer<ArticleList>() {
                @Override
                public void onChanged(@Nullable ArticleList articleList) {
                    List<Article> articles = articleList.getArticles();

                    Log.v("articles value", "" + articles.size());

                }
            });
           /* articleViewModel.getNewsPaperSources().observe(HomeActivity.this, new Observer<NewsPaperList>() {
                @Override
                public void onChanged(@Nullable NewsPaperList newsPaperList) {
                    // List<NewsPaperObject>  newsPaperObjects = newsPaperList.getSources();
                    List<NewsPaperGenre> newsPaperGenres = parseGenreData(newsPaperList);
                    Log.v("newsPaperObjects value", "" + newsPaperGenres.size());

                    for (NewsPaperGenre genre : newsPaperGenres) {

                    }

                }
            });*/

        } catch (Exception e) {
            Log.v("Exception in downloading from model", e.getMessage());
        }
    }


    public List<NewsPaperGenre> parseGenreData(List<NewsPaperObject> newsPaperList) {
        List<NewsPaperGenre> genres = new ArrayList<>();
        HashMap<String, NewsPaperGenre> newsPaperCollection = new HashMap<>();
        List<NewsPaperObject> newsPaperList1 = newsPaperList;

        for (int i = 0; i < newsPaperList1.size(); i++) {
            NewsPaperObject newsPaperObject = newsPaperList1.get(i);
            NewsPaperGenre value = newsPaperCollection.get(newsPaperObject.getCategory());
            if (value != null) {
                value.getPaperObjects().add(newsPaperObject);
            } else {
                List<NewsPaperObject> newsPaperType = new ArrayList<>();
                newsPaperType.add(newsPaperObject);
                NewsPaperGenre newGenre = new NewsPaperGenre(newsPaperObject.getCategory(), newsPaperType);
                newsPaperCollection.put(newsPaperObject.getCategory(), newGenre);
            }
        }
        for (Map.Entry<String, NewsPaperGenre> entry : newsPaperCollection.entrySet()) {
            String key = entry.getKey();
            NewsPaperGenre genre = entry.getValue();
            genres.add(genre);
        }
        return genres;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        Log.v("Curremt MenuItem Selected", "" + item.getItemId());
        NewsPaperGenre genre = genres.get(item.getItemId());
        Log.v("Selected Genre", genre.getTitle());
        homeFragment.setNewsPaperObjects( genres.get(0).getPaperObjects());
        //homeFragment.setActivityCallBack(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Log.v("Recycler Item Clicked",""+position);
    }
}
