package com.example.foottraffic;

import com.example.foottraffic.pojo.MultipleResourceActivity;
//import com.journaldev.retrofitintro.pojo.User;
//import com.journaldev.retrofitintro.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface APIInterfaceActivity {

    @FormUrlEncoded
    @POST("/api/v1/forecasts/live?")
    Call<MultipleResourceActivity> doCreateInformationWithField(@Field("api_key_private") String api_key_private, @Field("venue_name") String venue_name, @Field("venue_address") String venue_address);

//    @GET("/api/unknown")
//    Call<MultipleResource> doGetListResources();
//
//    @POST("/api/users")
//    Call<User> createUser(@Body User user);
//
//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
