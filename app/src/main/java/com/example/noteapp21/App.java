package com.example.noteapp21;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.noteapp21.room.AppDataBase;

public class App extends Application {
    public static App instance;
    public static Share share;


    private static AppDataBase dataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room
                .databaseBuilder(this,AppDataBase.class,"database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDataBase getDataBase() {
        return dataBase;
    }
}
