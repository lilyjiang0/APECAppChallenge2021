package com.example.foottraffic.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MultipleResourceActivity {

    @SerializedName("analysis")
    public AnalysisData analysis;
    @SerializedName("status")
    public String status;
    @SerializedName("venue_info")
    public VenueInfoData venue_info;

    public class AnalysisData {

        @SerializedName("venue_forecasted_busyness")
        public Integer venue_forecasted_busyness;
        @SerializedName("venue_live_busyness")
        public Integer venue_live_busyness;
        @SerializedName("venue_live_busyness_available")
        public Boolean venue_live_busyness_available;
        @SerializedName("venue_live_forecasted_delta")
        public Integer venue_live_forecasted_delta;

    }

    public class VenueInfoData {

        @SerializedName("venue_current_gmttime")
        public String venue_current_gmttime;
        @SerializedName("venue_current_localtime")
        public String venue_current_localtime;
        @SerializedName("venue_id")
        public String venue_id;
        @SerializedName("venue_name")
        public String venue_name;
        @SerializedName("venue_timezone")
        public String venue_timezone;

    }

    public AnalysisData getAnalysis() {
        return analysis;
    }

    public void setAnalysis(AnalysisData analysis) {
        this.analysis = analysis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VenueInfoData getVenue_info() {
        return venue_info;
    }

    public void setVenue_info(VenueInfoData venue_info) {
        this.venue_info = venue_info;
    }
}