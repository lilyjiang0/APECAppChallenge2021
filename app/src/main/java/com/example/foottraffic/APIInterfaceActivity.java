package com.example.foottraffic;

import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.MultipleResourceActivity;
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

    @FormUrlEncoded
    @POST("forecasts/live?api_key_private=pri_e435dec0a2aa4b8e8b4ef42bc990f596&venue_id=ven_51387131543761435650505241346a394a6432395362654a496843")
    Call<MultipleResourceActivity> doCreateInformationWithField(@Field("api_key_private") String api_key_private, @Field("venue_address") String venue_address);

    // Things to do in Sydney
    // 20 results
    // @GET("venues/progress?job_id=d753cbd3-fdb6-498c-a430-59f3a036f573&collection_id=col_a9f974716aba4995a4627538b5a2cad2&ven=False")
    // 1 results
    @GET("venues/progress?job_id=fa8383a7-26f2-4ef6-97c2-ecf8111a40b5&collection_id=col_f299d036ea6f4ef584a9ccb458da2851&ven=False")
    Call<Attractions> getAttraction();
}