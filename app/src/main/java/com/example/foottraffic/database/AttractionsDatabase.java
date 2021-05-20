package com.example.foottraffic.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.foottraffic.database.AttractionDb;
import com.example.foottraffic.database.attractionsDao;
import com.example.foottraffic.pojo.Attractions;

@Database(entities = {Attractions.Venue.class}, version = 1)
public abstract class AttractionsDatabase extends RoomDatabase {
    public abstract attractionsDao attractionsDao();
}

