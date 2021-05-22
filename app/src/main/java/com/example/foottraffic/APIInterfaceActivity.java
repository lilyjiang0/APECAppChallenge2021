package com.example.foottraffic;

import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.MultipleResourceActivity;
//import com.journaldev.retrofitintro.pojo.User;
//import com.journaldev.retrofitintro.pojo.UserList;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface APIInterfaceActivity {

    @POST("forecasts/live")
//    Call<MultipleResourceActivity> getForecast(@Query("api_key_private") String api_key_private, @Query("venue_name") String venue_name, @Query("venue_address") String venue_address);
    Observable<MultipleResourceActivity> getForecast(@Query("api_key_private") String api_key_private, @Query("venue_name") String venue_name, @Query("venue_address") String venue_address);


    @GET("venues/progress?job_id=93db21d2-0168-441f-b1ad-f87815df025f&collection_id=col_c879e1e459f44b32965684febd87b434&ven=False")
    Call<Attractions> getAttraction();

}