package com.newsapi.newsapp.newsapplication.model;

/**
 * Created by RA283478 on 1/11/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Source {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
