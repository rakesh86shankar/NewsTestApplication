package com.newsapi.newsapp.newsapplication;

import com.newsapi.newsapp.newsapplication.model.NewsPaperArray;

public class DataSingleton {
    private  NewsPaperArray newsPaperArray;


    private static volatile DataSingleton sSoleInstance;

    //private constructor.
    private DataSingleton() {

        //Prevent form the reflection api.
        if (sSoleInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static DataSingleton getInstance() {
        if (sSoleInstance == null) { //if there is no instance available... create new one
            synchronized (DataSingleton.class) {
                if (sSoleInstance == null) sSoleInstance = new DataSingleton();
            }
        }

        return sSoleInstance;
    }

    //Make singleton from serialize and deserialize operation.
    protected DataSingleton readResolve() {
        return getInstance();
    }

    protected void setNewsPaperSources(NewsPaperArray newsPaperSources) {
        newsPaperArray = newsPaperSources;
    }

    public NewsPaperArray getNewsPaperSources() {
        return newsPaperArray;
    }
}
