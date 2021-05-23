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

        public Integer getVenue_forecasted_busyness() {
            return venue_forecasted_busyness;
        }

        public void setVenue_forecasted_busyness(Integer venue_forecasted_busyness) {
            this.venue_forecasted_busyness = venue_forecasted_busyness;
        }

        public Integer getVenue_live_busyness() {
            return venue_live_busyness;
        }

        public void setVenue_live_busyness(Integer venue_live_busyness) {
            this.venue_live_busyness = venue_live_busyness;
        }

        public Boolean getVenue_live_busyness_available() {
            return venue_live_busyness_available;
        }

        public void setVenue_live_busyness_available(Boolean venue_live_busyness_available) {
            this.venue_live_busyness_available = venue_live_busyness_available;
        }

        public Integer getVenue_live_forecasted_delta() {
            return venue_live_forecasted_delta;
        }

        public void setVenue_live_forecasted_delta(Integer venue_live_forecasted_delta) {
            this.venue_live_forecasted_delta = venue_live_forecasted_delta;
        }
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

        public String getVenue_current_gmttime() {
            return venue_current_gmttime;
        }

        public void setVenue_current_gmttime(String venue_current_gmttime) {
            this.venue_current_gmttime = venue_current_gmttime;
        }

        public String getVenue_current_localtime() {
            return venue_current_localtime;
        }

        public void setVenue_current_localtime(String venue_current_localtime) {
            this.venue_current_localtime = venue_current_localtime;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getVenue_timezone() {
            return venue_timezone;
        }

        public void setVenue_timezone(String venue_timezone) {
            this.venue_timezone = venue_timezone;
        }
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