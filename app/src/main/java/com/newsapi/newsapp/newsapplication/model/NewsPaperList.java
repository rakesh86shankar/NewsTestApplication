package com.newsapi.newsapp.newsapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by rakesh sankar on 9/18/2017.
 */

public class NewsPaperList {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<NewsPaperObject> sources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewsPaperObject> getSources() {
        return sources;
    }

    public void setSources(List<NewsPaperObject> sources) {
        this.sources = sources;
    }

}
