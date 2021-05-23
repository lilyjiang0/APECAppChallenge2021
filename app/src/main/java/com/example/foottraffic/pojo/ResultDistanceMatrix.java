package com.example.foottraffic.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDistanceMatrix {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("origin_addresses")
    @Expose
    private List<String> originAddresses = null;
    @SerializedName("destination_addresses")
    @Expose
    private List<String> destinationAddresses = null;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getOriginAddresses() {
        return originAddresses;
    }

    public void setOriginAddresses(List<String> originAddresses) {
        this.originAddresses = originAddresses;
    }

    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    public void setDestinationAddresses(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public class Row {

        @SerializedName("elements")
        @Expose
        private List<Element> elements = null;

        public List<Element> getElements() {
            return elements;
        }

        public void setElements(List<Element> elements) {
            this.elements = elements;
        }
    }

    public class Distance {

        @SerializedName("value")
        @Expose
        private Integer value;
        @SerializedName("text")
        @Expose
        private String text;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public class Duration {

        @SerializedName("value")
        @Expose
        private Integer value;
        @SerializedName("text")
        @Expose
        private String text;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

    public class Element {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("duration")
        @Expose
        private Duration duration;
        @SerializedName("distance")
        @Expose
        private Distance distance;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Duration getDuration() {
            return duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Distance getDistance() {
            return distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }

    }

}