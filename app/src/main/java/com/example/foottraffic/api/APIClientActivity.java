package com.example.foottraffic.api;

import com.example.foottraffic.api.NullOnEmptyConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientActivity {
    private static Retrofit retrofit = null;
    public static String api_key_private = "pri_1008d36a82b4452393139213da2109c5";

    public static Retrofit getClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://besttime.app/api/v1/")
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();


        return retrofit;
    }

    public static String getApi_key_private() {
        return api_key_private;
    }
}
