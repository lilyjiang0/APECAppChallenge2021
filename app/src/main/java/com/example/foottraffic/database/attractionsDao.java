package com.example.foottraffic.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.foottraffic.database.AttractionDb;
import com.example.foottraffic.pojo.Attractions;

@Dao
public interface attractionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Attractions.Venue venue);
}
