package com.example.foottraffic.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttractionQuietHours {

    @SerializedName("analysis")
    @Expose
    private Analysis analysis;
    @SerializedName("epoch_analysis")
    @Expose
    private Integer epochAnalysis;
    @SerializedName("forecast_updated_on")
    @Expose
    private String forecastUpdatedOn;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("venue_id")
    @Expose
    private String venueId;
    @SerializedName("venue_info")
    @Expose
    private VenueInfo venueInfo;
    @SerializedName("venue_name")
    @Expose
    private String venueName;

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public Integer getEpochAnalysis() {
        return epochAnalysis;
    }

    public void setEpochAnalysis(Integer epochAnalysis) {
        this.epochAnalysis = epochAnalysis;
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

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public VenueInfo getVenueInfo() {
        return venueInfo;
    }

    public void setVenueInfo(VenueInfo venueInfo) {
        this.venueInfo = venueInfo;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public class Analysis {

        @SerializedName("day_info")
        @Expose
        private DayInfo dayInfo;
        @SerializedName("quiet_hours")
        @Expose
        private List<QuietHour> quietHours = null;
        @SerializedName("quiet_hours_list")
        @Expose
        private List<Integer> quietHoursList = null;
        @SerializedName("quiet_hours_list_12h")
        @Expose
        private List<String> quietHoursList12h = null;
        @SerializedName("quiet_hours_list_coming")
        @Expose
        private List<Integer> quietHoursListComing = null;
        @SerializedName("quiet_hours_list_coming_12h")
        @Expose
        private List<String> quietHoursListComing12h = null;

        public DayInfo getDayInfo() {
            return dayInfo;
        }

        public void setDayInfo(DayInfo dayInfo) {
            this.dayInfo = dayInfo;
        }

        public List<QuietHour> getQuietHours() {
            return quietHours;
        }

        public void setQuietHours(List<QuietHour> quietHours) {
            this.quietHours = quietHours;
        }

        public List<Integer> getQuietHoursList() {
            return quietHoursList;
        }

        public void setQuietHoursList(List<Integer> quietHoursList) {
            this.quietHoursList = quietHoursList;
        }

        public List<String> getQuietHoursList12h() {
            return quietHoursList12h;
        }

        public void setQuietHoursList12h(List<String> quietHoursList12h) {
            this.quietHoursList12h = quietHoursList12h;
        }

        public List<Integer> getQuietHoursListComing() {
            return quietHoursListComing;
        }

        public void setQuietHoursListComing(List<Integer> quietHoursListComing) {
            this.quietHoursListComing = quietHoursListComing;
        }

        public List<String> getQuietHoursListComing12h() {
            return quietHoursListComing12h;
        }

        public void setQuietHoursListComing12h(List<String> quietHoursListComing12h) {
            this.quietHoursListComing12h = quietHoursListComing12h;
        }

    }

    public class DayInfo {

        @SerializedName("day_int")
        @Expose
        private Integer dayInt;
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

    public class QuietHour {

        @SerializedName("quiet_end")
        @Expose
        private Integer quietEnd;
        @SerializedName("quiet_end_12")
        @Expose
        private String quietEnd12;
        @SerializedName("quiet_end_in")
        @Expose
        private String quietEndIn;
        @SerializedName("quiet_end_in_sec")
        @Expose
        private Integer quietEndInSec;
        @SerializedName("quiet_end_passed")
        @Expose
        private Integer quietEndPassed;
        @SerializedName("quiet_period_duration")
        @Expose
        private Integer quietPeriodDuration;
        @SerializedName("quiet_start")
        @Expose
        private Integer quietStart;
        @SerializedName("quiet_start_12")
        @Expose
        private String quietStart12;
        @SerializedName("quiet_start_in")
        @Expose
        private String quietStartIn;
        @SerializedName("quiet_start_in_sec")
        @Expose
        private Integer quietStartInSec;
        @SerializedName("quiet_start_passed")
        @Expose
        private Integer quietStartPassed;

        public Integer getQuietEnd() {
            return quietEnd;
        }

        public void setQuietEnd(Integer quietEnd) {
            this.quietEnd = quietEnd;
        }

        public String getQuietEnd12() {
            return quietEnd12;
        }

        public void setQuietEnd12(String quietEnd12) {
            this.quietEnd12 = quietEnd12;
        }

        public String getQuietEndIn() {
            return quietEndIn;
        }

        public void setQuietEndIn(String quietEndIn) {
            this.quietEndIn = quietEndIn;
        }

        public Integer getQuietEndInSec() {
            return quietEndInSec;
        }

        public void setQuietEndInSec(Integer quietEndInSec) {
            this.quietEndInSec = quietEndInSec;
        }

        public Integer getQuietEndPassed() {
            return quietEndPassed;
        }

        public void setQuietEndPassed(Integer quietEndPassed) {
            this.quietEndPassed = quietEndPassed;
        }

        public Integer getQuietPeriodDuration() {
            return quietPeriodDuration;
        }

        public void setQuietPeriodDuration(Integer quietPeriodDuration) {
            this.quietPeriodDuration = quietPeriodDuration;
        }

        public Integer getQuietStart() {
            return quietStart;
        }

        public void setQuietStart(Integer quietStart) {
            this.quietStart = quietStart;
        }

        public String getQuietStart12() {
            return quietStart12;
        }

        public void setQuietStart12(String quietStart12) {
            this.quietStart12 = quietStart12;
        }

        public String getQuietStartIn() {
            return quietStartIn;
        }

        public void setQuietStartIn(String quietStartIn) {
            this.quietStartIn = quietStartIn;
        }

        public Integer getQuietStartInSec() {
            return quietStartInSec;
        }

        public void setQuietStartInSec(Integer quietStartInSec) {
            this.quietStartInSec = quietStartInSec;
        }

        public Integer getQuietStartPassed() {
            return quietStartPassed;
        }

        public void setQuietStartPassed(Integer quietStartPassed) {
            this.quietStartPassed = quietStartPassed;
        }

    }

    public class VenueInfo {

        @SerializedName("venue_current_gmttime")
        @Expose
        private String venueCurrentGmttime;
        @SerializedName("venue_current_localtime_iso")
        @Expose
        private String venueCurrentLocaltimeIso;
        @SerializedName("venue_id")
        @Expose
        private String venueId;
        @SerializedName("venue_name")
        @Expose
        private String venueName;
        @SerializedName("venue_timezone")
        @Expose
        private String venueTimezone;

        public String getVenueCurrentGmttime() {
            return venueCurrentGmttime;
        }

        public void setVenueCurrentGmttime(String venueCurrentGmttime) {
            this.venueCurrentGmttime = venueCurrentGmttime;
        }

        public String getVenueCurrentLocaltimeIso() {
            return venueCurrentLocaltimeIso;
        }

        public void setVenueCurrentLocaltimeIso(String venueCurrentLocaltimeIso) {
            this.venueCurrentLocaltimeIso = venueCurrentLocaltimeIso;
        }

        public String getVenueId() {
            return venueId;
        }

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }

        public String getVenueTimezone() {
            return venueTimezone;
        }

        public void setVenueTimezone(String venueTimezone) {
            this.venueTimezone = venueTimezone;
        }

    }
}