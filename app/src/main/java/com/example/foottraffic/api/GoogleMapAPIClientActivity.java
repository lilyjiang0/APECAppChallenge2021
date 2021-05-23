package com.example.foottraffic.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleMapAPIClientActivity {
    private static Retrofit retrofit = null;
    public static final String GOOGLE_PLACE_API_KEY = "AIzaSyA9Lg-szsZdPGZGGD4lzuCPOjehw6h1qwk";
    public static String base_url = "https://maps.googleapis.com/maps/api/";

    public static Retrofit getClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();


        return retrofit;
    }
}
