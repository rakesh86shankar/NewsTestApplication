package com.newsapi.newsapp.newsapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ArticleListArray implements Parcelable {
    public static final Creator<ArticleListArray> CREATOR = new Creator<ArticleListArray>() {
        @Override
        public ArticleListArray createFromParcel(Parcel in) {
            return new ArticleListArray(in);
        }

        @Override
        public ArticleListArray[] newArray(int size) {
            return new ArticleListArray[size];
        }
    };
    List<ArticleOld> articlesList = new ArrayList<ArticleOld>();


    public ArticleListArray() {
        // initialization
        articlesList = new ArrayList<ArticleOld>();
    }

    protected ArticleListArray(Parcel in) {
        articlesList = in.createTypedArrayList(ArticleOld.CREATOR);
    }

    public List<ArticleOld> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<ArticleOld> articlesList) {
        this.articlesList = articlesList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(articlesList);
    }
}
