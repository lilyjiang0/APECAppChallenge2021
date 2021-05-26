package com.example.foottraffic.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeekForecastData {

    @SerializedName("analysis")
    @Expose
    private Analysis analysis;
    @SerializedName("forecast_updated_on")
    @Expose
    private String forecastUpdatedOn;
    @SerializedName("status")
    @Expose
    private String status;

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public String getForecastUpdatedOn() {
        return forecastUpdatedOn;
    }

    public void setForecastUpdatedOn(String forecastUpdatedOn) {
        this.forecastUpdatedOn = forecastUpdatedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Analysis {

        @SerializedName("week_overview")
        @Expose
        private List<WeekOverview> weekOverview = null;

        public List<WeekOverview> getWeekOverview() {
            return weekOverview;
        }

        public void setWeekOverview(List<WeekOverview> weekOverview) {
            this.weekOverview = weekOverview;
        }

    }

    public class WeekOverview {

        @SerializedName("day_int")
        @Expose
        private Integer dayInt;
        @SerializedName("day_max")
        @Expose
        private Integer dayMax;
        @SerializedName("day_mean")
        @Expose
        private Integer dayMean;
        @SerializedName("day_rank_max")
        @Expose
        private Integer dayRankMax;
        @SerializedName("day_rank_mean")
        @Expose
        private Integer dayRankMean;
        @SerializedName("day_text")
        @Expose
        private String dayText;
        @SerializedName("venue_closed")
        @Expose
        private Integer venueClosed;
        @SerializedName("venue_open")
        @Expose
        private Integer venueOpen;

        public Integer getDayInt() {
            return dayInt;
        }

        public void setDayInt(Integer dayInt) {
            this.dayInt = dayInt;
        }

        public Integer getDayMax() {
            return dayMax;
        }

        public void setDayMax(Integer dayMax) {
            this.dayMax = dayMax;
        }

        public Integer getDayMean() {
            return dayMean;
        }

        public void setDayMean(Integer dayMean) {
            this.dayMean = dayMean;
        }

        public Integer getDayRankMax() {
            return dayRankMax;
        }

        public void setDayRankMax(Integer dayRankMax) {
            this.dayRankMax = dayRankMax;
        }

        public Integer getDayRankMean() {
            return dayRankMean;
        }

        public void setDayRankMean(Integer dayRankMean) {
            this.dayRankMean = dayRankMean;
        }

        public String getDayText() {
            return dayText;
        }

        public void setDayText(String dayText) {
            this.dayText = dayText;
        }

        public Integer getVenueClosed() {
            return venueClosed;
        }

        public void setVenueClosed(Integer venueClosed) {
            this.venueClosed = venueClosed;
        }

        public Integer getVenueOpen() {
            return venueOpen;
        }

        public void setVenueOpen(Integer venueOpen) {
            this.venueOpen = venueOpen;
        }

    }
}