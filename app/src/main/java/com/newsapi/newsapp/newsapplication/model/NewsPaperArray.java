package com.newsapi.newsapp.newsapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class NewsPaperArray implements Parcelable {
    List<NewsPaperObject> newsPaperObjects;

    public NewsPaperArray() {
        // initialization
        newsPaperObjects = new ArrayList<NewsPaperObject>();
    }

    public List<NewsPaperObject> getNewsPaperObjects() {
        return newsPaperObjects;
    }

    public void setNewsPaperObjects(List<NewsPaperObject> newsPaperObjects) {
        this.newsPaperObjects = newsPaperObjects;
    }

    protected NewsPaperArray(Parcel in) {
        newsPaperObjects = in.createTypedArrayList(NewsPaperObject.CREATOR);
    }

    public static final Creator<NewsPaperArray> CREATOR = new Creator<NewsPaperArray>() {
        @Override
        public NewsPaperArray createFromParcel(Parcel in) {
            return new NewsPaperArray(in);
        }

        @Override
        public NewsPaperArray[] newArray(int size) {
            return new NewsPaperArray[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(newsPaperObjects);
    }
}
