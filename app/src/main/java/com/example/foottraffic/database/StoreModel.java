package com.example.foottraffic.database;

public class StoreModel {


    public String originAddress, destinationAddress, distance, duration;

    public StoreModel(String originAddress, String destinationAddress, String distance, String duration) {

        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.distance = distance;
        this.duration = duration;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }
}