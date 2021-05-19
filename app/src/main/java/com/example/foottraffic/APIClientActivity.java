package com.example.foottraffic;

import android.util.Log;

import androidx.collection.ArraySet;

import com.example.foottraffic.pojo.MultipleResourceActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientActivity {
    private static Retrofit retrofit = null;
    public static String api_key_private = "pri_e435dec0a2aa4b8e8b4ef42bc990f596";

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
