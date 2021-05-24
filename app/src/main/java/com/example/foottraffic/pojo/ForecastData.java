package com.example.foottraffic.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ForecastData {
    @SerializedName("analysis")
    @Expose
    private List<Analysis> analysis = null;
    @SerializedName("epoch_analysis")
    @Expose
    private String epochAnalysis;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("venue_info")
    @Expose
    private VenueInfo venueInfo;

    public List<Analysis> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(List<Analysis> analysis) {
        this.analysis = analysis;
    }

    public String getEpochAnalysis() {
        return epochAnalysis;
    }

    public void setEpochAnalysis(String epochAnalysis) {
        this.epochAnalysis = epochAnalysis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VenueInfo getVenueInfo() {
        return venueInfo;
    }

    public void setVenueInfo(VenueInfo venueInfo) {
        this.venueInfo = venueInfo;
    }

    public class Analysis {

        @SerializedName("busy_hours")
        @Expose
        private List<Integer> busyHours = null;
        @SerializedName("change_hours")
        @Expose
        private ChangeHours changeHours;
        @SerializedName("day_info")
        @Expose
        private DayInfo dayInfo;
        @SerializedName("hour_analysis")
        @Expose
        private List<HourAnalysi> hourAnalysis = null;
        @SerializedName("peak_hours")
        @Expose
        private List<PeakHour> peakHours = null;
        @SerializedName("quiet_hours")
        @Expose
        private List<Integer> quietHours = null;
        @SerializedName("day_raw")
        @Expose
        private List<Integer> dayRaw = null;

        public List<Integer> getDayRaw() {
            return dayRaw;
        }

        public void setDayRaw(List<Integer> dayRaw) {
            this.dayRaw = dayRaw;
        }

        public List<Integer> getBusyHours() {
            return busyHours;
        }

        public void setBusyHours(List<Integer> busyHours) {
            this.busyHours = busyHours;
        }

        public ChangeHours getChangeHours() {
            return changeHours;
        }

        public void setChangeHours(ChangeHours changeHours) {
            this.changeHours = changeHours;
        }

        public DayInfo getDayInfo() {
            return dayInfo;
        }

        public void setDayInfo(DayInfo dayInfo) {
            this.dayInfo = dayInfo;
        }

        public List<HourAnalysi> getHourAnalysis() {
            return hourAnalysis;
        }

        public void setHourAnalysis(List<HourAnalysi> hourAnalysis) {
            this.hourAnalysis = hourAnalysis;
        }

        public List<PeakHour> getPeakHours() {
            return peakHours;
        }

        public void setPeakHours(List<PeakHour> peakHours) {
            this.peakHours = peakHours;
        }

        public List<Integer> getQuietHours() {
            return quietHours;
        }

        public void setQuietHours(List<Integer> quietHours) {
            this.quietHours = quietHours;
        }

    }

    public class ChangeHours {

        @SerializedName("most_people_come")
        @Expose
        private Integer mostPeopleCome;
        @SerializedName("most_people_leave")
        @Expose
        private Integer mostPeopleLeave;

        public Integer getMostPeopleCome() {
            return mostPeopleCome;
        }

        public void setMostPeopleCome(Integer mostPeopleCome) {
            this.mostPeopleCome = mostPeopleCome;
        }

        public Integer getMostPeopleLeave() {
            return mostPeopleLeave;
        }

        public void setMostPeopleLeave(Integer mostPeopleLeave) {
            this.mostPeopleLeave = mostPeopleLeave;
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

    public class PeakHour {

        @SerializedName("peak_delta_mean_week")
        @Expose
        private Integer peakDeltaMeanWeek;
        @SerializedName("peak_end")
        @Expose
        private Integer peakEnd;
        @SerializedName("peak_intensity")
        @Expose
        private Integer peakIntensity;
        @SerializedName("peak_max")
        @Expose
        private Integer peakMax;
        @SerializedName("peak_start")
        @Expose
        private Integer peakStart;

        public Integer getPeakDeltaMeanWeek() {
            return peakDeltaMeanWeek;
        }

        public void setPeakDeltaMeanWeek(Integer peakDeltaMeanWeek) {
            this.peakDeltaMeanWeek = peakDeltaMeanWeek;
        }

        public Integer getPeakEnd() {
            return peakEnd;
        }

        public void setPeakEnd(Integer peakEnd) {
            this.peakEnd = peakEnd;
        }

        public Integer getPeakIntensity() {
            return peakIntensity;
        }

        public void setPeakIntensity(Integer peakIntensity) {
            this.peakIntensity = peakIntensity;
        }

        public Integer getPeakMax() {
            return peakMax;
        }

        public void setPeakMax(Integer peakMax) {
            this.peakMax = peakMax;
        }

        public Integer getPeakStart() {
            return peakStart;
        }

        public void setPeakStart(Integer peakStart) {
            this.peakStart = peakStart;
        }

    }

    public class VenueInfo {

        @SerializedName("venue_address")
        @Expose
        private String venueAddress;
        @SerializedName("venue_id")
        @Expose
        private String venueId;
        @SerializedName("venue_name")
        @Expose
        private String venueName;
        @SerializedName("venue_timezone")
        @Expose
        private String venueTimezone;

        public String getVenueAddress() {
            return venueAddress;
        }

        public void setVenueAddress(String venueAddress) {
            this.venueAddress = venueAddress;
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