package com.example.foottraffic.api;

import com.example.foottraffic.APIInterfaceActivity;
import com.example.foottraffic.api.NullOnEmptyConverterFactory;
import com.example.foottraffic.pojo.MultipleResourceActivity;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientActivity {
    private static Retrofit retrofit = null;
    private static APIClientActivity instance;
    private APIInterfaceActivity apiInterface;

    public static Retrofit getClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://besttime.app/api/v1/")
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        return retrofit;
    }

    public static APIClientActivity getInstance() {
        if (instance == null) {
            instance = new APIClientActivity();
        }
        return instance;
    }

    public Observable<MultipleResourceActivity> getForecastData(String api_key_private, String venue_name, String venue_address) {
        return apiInterface.getForecast(api_key_private, venue_name, venue_address);
    }

}
