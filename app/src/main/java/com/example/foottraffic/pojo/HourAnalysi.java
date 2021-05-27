package com.example.foottraffic.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourAnalysi {

    @SerializedName("hour")
    @Expose
    private Integer hour;
    @SerializedName("intensity_nr")
    @Expose
    private Integer intensityNr;
    @SerializedName("intensity_txt")
    @Expose
    private String intensityTxt;

    public HourAnalysi(Integer hour, Integer intensityNr, String intensityTxt) {
        this.hour = hour;
        this.intensityNr = intensityNr;
        this.intensityTxt = intensityTxt;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getIntensityNr() {
        return intensityNr;
    }

    public void setIntensityNr(Integer intensityNr) {
        this.intensityNr = intensityNr;
    }

    public String getIntensityTxt() {
        return intensityTxt;
    }

    public void setIntensityTxt(String intensityTxt) {
        this.intensityTxt = intensityTxt;
    }

}
