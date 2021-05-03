package com.newsapi.newsapp.newsapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RA283478 on 1/11/2018.
 */

public class Article {

    @SerializedName("source")
    private Source source;
    @SerializedName("author")
    private Object author;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;
    @SerializedName("publishedAt")
    private String publishedAt;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Source getSource() {
        return source;
    }


    public void setSource(Source source) {
        this.source = source;
    }


    public Object getAuthor() {
        return author;
    }


    public void setAuthor(Object author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getUrlToImage() {
        return urlToImage;
    }


    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
