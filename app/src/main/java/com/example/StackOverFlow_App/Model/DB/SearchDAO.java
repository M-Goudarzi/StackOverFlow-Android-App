package com.example.StackOverFlow_App.Model.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.StackOverFlow_App.Model.DB.Entity.Search;

import java.util.List;

@Dao
public interface SearchDAO {

    @Query("SELECT * FROM Search")
    LiveData<List<Search>> getAll();

    @Insert
    void insert(Search search);

   @Query("DELETE FROM Search")
    void deleteAll();

}
