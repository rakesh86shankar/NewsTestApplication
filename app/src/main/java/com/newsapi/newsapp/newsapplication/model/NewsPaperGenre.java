package com.newsapi.newsapp.newsapplication.model;


import java.util.List;

/**
 * Created by rakesh sankar on 9/9/2017.
 */

public class NewsPaperGenre {
    private String title;

    public List<NewsPaperObject> getPaperObjects() {
        return paperObjects;
    }

    public void setPaperObjects(List<NewsPaperObject> paperObjects) {
        this.paperObjects = paperObjects;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private List<NewsPaperObject> paperObjects;

    public NewsPaperGenre(String papertitle, List<NewsPaperObject> items) {
        title = papertitle;
        paperObjects = items;
    }

}
