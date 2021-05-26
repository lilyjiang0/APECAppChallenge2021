package com.example.foottraffic.database;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "attractions")
public class AttractionDb {
    @PrimaryKey (autoGenerate = true)
    private int id;
//    @ColumnInfo(name = "name")
    private String name;

    public AttractionDb(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
