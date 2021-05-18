package com.example.foottraffic.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MultipleResourceActivity {

    @SerializedName("analysis")
    public List<AnalysisData> analysis = null;
    @SerializedName("status")
    public String status;
    @SerializedName("venue_info")
    public List<VenueInfoData> venue_info = null;

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
        public Date venue_current_gmttime;
        @SerializedName("venue_current_localtime")
        public Date venue_current_localtime;
        @SerializedName("venue_id")
        public String venue_id;
        @SerializedName("venue_name")
        public String venue_name;
        @SerializedName("venue_timezone")
        public String venue_timezone;

    }
}
