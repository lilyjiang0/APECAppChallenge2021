package com.example.foottraffic;

public class DiscoverListData{
    private Integer km;
    private String name;
    private String image;

    public DiscoverListData(Integer km, String name, String image) {
        this.km = km;
        this.name = name;
        this.image = image;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
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
}
