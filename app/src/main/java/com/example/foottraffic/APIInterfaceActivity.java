package com.example.foottraffic;

import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.MultipleResourceActivity;
import com.example.foottraffic.pojo.ResultDistanceMatrix;
import com.example.foottraffic.pojo.ResultDistanceMatrix;
//import com.journaldev.retrofitintro.pojo.User;
//import com.journaldev.retrofitintro.pojo.UserList;

import java.util.Map;

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

    // besttime
    @FormUrlEncoded
    @POST("forecasts/live?api_key_private=pri_1008d36a82b4452393139213da2109c5&venue_id=ven_51387131543761435650505241346a394a6432395362654a496843")
    Call<MultipleResourceActivity> doCreateInformationWithField(@Field("api_key_private") String api_key_private, @Field("venue_address") String venue_address);

    @GET("venues/progress?job_id=93db21d2-0168-441f-b1ad-f87815df025f&collection_id=col_c879e1e459f44b32965684febd87b434&ven=False")
    Call<Attractions> getAttraction();

    // google map
    @GET("distancematrix/json") // origins/destinations:  LatLng as string
    Call<ResultDistanceMatrix> getDistance(@Query("origins") String origins, @Query("destinations") String destinations, @Query("key") String key);
}