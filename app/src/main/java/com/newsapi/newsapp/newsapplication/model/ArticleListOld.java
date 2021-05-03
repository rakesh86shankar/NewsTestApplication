package com.newsapi.newsapp.newsapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rakesh sankar on 10/3/2017.
 */

public class ArticleListOld {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;
    @SerializedName("articleOlds")
    @Expose
    private List<ArticleOld> articleOlds = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<ArticleOld> getArticleOlds() {
        return articleOlds;
    }

    public void setArticleOlds(List<ArticleOld> articleOlds) {
        this.articleOlds = articleOlds;
    }

}
