package com.example.foottraffic.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foottraffic.pojo.Attractions;

import java.util.List;

@Dao
public interface attractionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Attractions.Venue venue);

    @Query("SELECT * from venue")
    List<Attractions.Venue> getAllVenues();
}
