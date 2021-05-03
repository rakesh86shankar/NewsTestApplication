package com.newsapi.newsapp.newsapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rakesh sankar on 9/9/2017.
 */

public class NewsPaper implements Parcelable {
    public static final Creator<NewsPaper> CREATOR = new Creator<NewsPaper>() {
        @Override
        public NewsPaper createFromParcel(Parcel in) {
            return new NewsPaper(in);
        }

        @Override
        public NewsPaper[] newArray(int size) {
            return new NewsPaper[size];
        }
    };
    private String name;
    private boolean isFavourite;

    protected NewsPaper(String paperName, boolean isPaperFavourite) {
        name = paperName;
        isFavourite = isPaperFavourite;
    }

    protected NewsPaper(Parcel in) {
        name = in.readString();
    }

    public String getPaperName() {
        return name;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }


    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsPaper)) return false;

        NewsPaper artist = (NewsPaper) o;

        if (isFavourite() != artist.isFavourite()) return false;
        return getPaperName() != null ? getPaperName().equals(artist.getPaperName()) : artist.getPaperName() == null;

    }*/
}
