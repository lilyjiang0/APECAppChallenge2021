package com.example.foottraffic;

public class DiscoverListData{
    private Double km;
    private String name;
    private String image;
    private String address;
    private Integer busy;
    private String id;


    public DiscoverListData(Double km, String name, String image, String address, Integer busy, String id) {
        this.km = km;
        this.name = name;
        this.image = image;
        this.address = address;
        this.busy = busy;
        this.id = id;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBusy() {
        return busy;
    }

    public void setBusy(Integer busy) {
        this.busy = busy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
