package com.example.foottraffic.database;

public class StoreModel {


    public String name, address, distance, duration;

    public StoreModel(String name, String address, String distance, String duration) {

        this.name = name;
        this.address = address;
        this.distance = distance;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }
}