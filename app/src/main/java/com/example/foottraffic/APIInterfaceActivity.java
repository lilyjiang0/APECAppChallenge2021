package com.example.foottraffic;

import com.example.foottraffic.pojo.AttractionQuietHours;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.ForecastData;
import com.example.foottraffic.pojo.MultipleResourceActivity;
import com.example.foottraffic.pojo.ResultDistanceMatrix;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterfaceActivity {

    @POST("forecasts/live")
    Observable<MultipleResourceActivity> getForecastLive(@Query("api_key_private") String api_key_private, @Query("venue_name") String venue_name, @Query("venue_address") String venue_address);

    @POST("forecasts")
    Call<ForecastData> getForecast(@Query("api_key_private") String api_key_private, @Query("venue_name") String venue_name, @Query("venue_address") String venue_address);

    @GET("venues/progress?job_id=93db21d2-0168-441f-b1ad-f87815df025f&collection_id=col_c879e1e459f44b32965684febd87b434&ven=False")
    Call<Attractions> getAttraction();

    @GET("forecasts/quiet")
    Call<AttractionQuietHours> getQuietHours(@Query("api_key_public") String api_key_public, @Query("venue_id") String venue_id);

    // google map
    @GET("distancematrix/json") // origins/destinations:  LatLng as string
    Call<ResultDistanceMatrix> getDistance(@Query("origins") String origins, @Query("destinations") String destinations, @Query("key") String key);
}