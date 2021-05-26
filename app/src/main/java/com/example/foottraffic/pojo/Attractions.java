package com.example.foottraffic.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attractions {
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("bounding_box")
    @Expose
    private BoundingBox boundingBox;
    @SerializedName("collection_id")
    @Expose
    private String collectionId;
    @SerializedName("count_completed")
    @Expose
    private Integer countCompleted;
    @SerializedName("count_failure")
    @Expose
    private Integer countFailure;
    @SerializedName("count_forecast")
    @Expose
    private Integer countForecast;
    @SerializedName("count_live")
    @Expose
    private Integer countLive;
    @SerializedName("count_total")
    @Expose
    private Integer countTotal;
    @SerializedName("job_finished")
    @Expose
    private Boolean jobFinished;
    @SerializedName("job_id")
    @Expose
    private String jobId;
    @SerializedName("radar_circles")
    @Expose
    private List<Object> radarCircles = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("venues")
    @Expose
    private List<Venue> venues = null;
    @SerializedName("venues_n")
    @Expose
    private Integer venuesN;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCountCompleted() {
        return countCompleted;
    }

    public void setCountCompleted(Integer countCompleted) {
        this.countCompleted = countCompleted;
    }

    public Integer getCountFailure() {
        return countFailure;
    }

    public void setCountFailure(Integer countFailure) {
        this.countFailure = countFailure;
    }

    public Integer getCountForecast() {
        return countForecast;
    }

    public void setCountForecast(Integer countForecast) {
        this.countForecast = countForecast;
    }

    public Integer getCountLive() {
        return countLive;
    }

    public void setCountLive(Integer countLive) {
        this.countLive = countLive;
    }

    public Integer getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Integer countTotal) {
        this.countTotal = countTotal;
    }

    public Boolean getJobFinished() {
        return jobFinished;
    }

    public void setJobFinished(Boolean jobFinished) {
        this.jobFinished = jobFinished;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public List<Object> getRadarCircles() {
        return radarCircles;
    }

    public void setRadarCircles(List<Object> radarCircles) {
        this.radarCircles = radarCircles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public Integer getVenuesN() {
        return venuesN;
    }

    public void setVenuesN(Integer venuesN) {
        this.venuesN = venuesN;
    }

    public class BoundingBox {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lat_max")
        @Expose
        private Double latMax;
        @SerializedName("lat_min")
        @Expose
        private Double latMin;
        @SerializedName("lng")
        @Expose
        private Double lng;
        @SerializedName("lng_max")
        @Expose
        private Double lngMax;
        @SerializedName("lng_min")
        @Expose
        private Double lngMin;
        @SerializedName("map_zoom")
        @Expose
        private Integer mapZoom;
        @SerializedName("radius")
        @Expose
        private Integer radius;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLatMax() {
            return latMax;
        }

        public void setLatMax(Double latMax) {
            this.latMax = latMax;
        }

        public Double getLatMin() {
            return latMin;
        }

        public void setLatMin(Double latMin) {
            this.latMin = latMin;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Double getLngMax() {
            return lngMax;
        }

        public void setLngMax(Double lngMax) {
            this.lngMax = lngMax;
        }

        public Double getLngMin() {
            return lngMin;
        }

        public void setLngMin(Double lngMin) {
            this.lngMin = lngMin;
        }

        public Integer getMapZoom() {
            return mapZoom;
        }

        public void setMapZoom(Integer mapZoom) {
            this.mapZoom = mapZoom;
        }

        public Integer getRadius() {
            return radius;
        }

        public void setRadius(Integer radius) {
            this.radius = radius;
        }

    }

    public class Links {

        @SerializedName("background_progress_api")
        @Expose
        private String backgroundProgressApi;
        @SerializedName("background_progress_tool")
        @Expose
        private String backgroundProgressTool;
        @SerializedName("job_id")
        @Expose
        private String jobId;
        @SerializedName("radar_tool")
        @Expose
        private String radarTool;
        @SerializedName("venue_filter_api")
        @Expose
        private String venueFilterApi;

        public String getBackgroundProgressApi() {
            return backgroundProgressApi;
        }

        public void setBackgroundProgressApi(String backgroundProgressApi) {
            this.backgroundProgressApi = backgroundProgressApi;
        }

        public String getBackgroundProgressTool() {
            return backgroundProgressTool;
        }

        public void setBackgroundProgressTool(String backgroundProgressTool) {
            this.backgroundProgressTool = backgroundProgressTool;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public String getRadarTool() {
            return radarTool;
        }

        public void setRadarTool(String radarTool) {
            this.radarTool = radarTool;
        }

        public String getVenueFilterApi() {
            return venueFilterApi;
        }

        public void setVenueFilterApi(String venueFilterApi) {
            this.venueFilterApi = venueFilterApi;
        }

    }

    @Entity(tableName = "venue")
    public static class Venue implements Serializable {
        @SerializedName("forecast")
        @Expose
        private Boolean forecast;
        @SerializedName("processed")
        @Expose
        private Boolean processed;
        @SerializedName("venue_address")
        @Expose
        private String venueAddress;
        @SerializedName("venue_lat")
        @Expose
        private Double venueLat;
        @SerializedName("venue_lon")
        @Expose
        private Double venueLon;
        @SerializedName("venue_name")
        @PrimaryKey
        @NonNull
        @Expose
        private String venueName;

        public Boolean getForecast() {
            return forecast;
        }

        public void setForecast(Boolean forecast) {
            this.forecast = forecast;
        }

        public Boolean getProcessed() {
            return processed;
        }

        public void setProcessed(Boolean processed) {
            this.processed = processed;
        }

        public String getVenueAddress() {
            return venueAddress;
        }

        public void setVenueAddress(String venueAddress) {
            this.venueAddress = venueAddress;
        }

        public Double getVenueLat() {
            return venueLat;
        }

        public void setVenueLat(Double venueLat) {
            this.venueLat = venueLat;
        }

        public Double getVenueLon() {
            return venueLon;
        }

        public void setVenueLon(Double venueLon) {
            this.venueLon = venueLon;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }

        @Override
        public String toString() {
            return "venueName=" + venueName +
                    ", venueAddress=" + venueAddress ;
        }
    }

}