package com.example.StackOverFlow_App.Model.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.StackOverFlow_App.Model.DB.Entity.Search;

@Database(entities = {Search.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract SearchDAO getDao();

    private static volatile AppDataBase Instance;

    public static AppDataBase getInstance(final Context context) {
        if (Instance == null) {
            synchronized (AppDataBase.class) {
                if (Instance == null) {
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "search_database")
                            .build();
                }
            }
        }
        return Instance;
    }
}
