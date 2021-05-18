package com.example.foottraffic;

import android.util.Log;

import com.example.foottraffic.pojo.MultipleResourceActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class APIClientActivity {

    private static Retrofit retrofit = null;
    private static String api_key_private = "pri_e435dec0a2aa4b8e8b4ef42bc990f596";
    static String displayResponse = "";

    public static String getClient(String venue_name, String venue_address) {

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://besttime.app")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build();

        APIInterfaceActivity apiInterface = retrofit.create(APIInterfaceActivity.class);
        Call<MultipleResourceActivity> call = apiInterface.doCreateInformationWithField(api_key_private, venue_name, venue_address);
        call.enqueue(new Callback<MultipleResourceActivity>() {
            @Override
            public void onResponse(Call<MultipleResourceActivity> call, Response<MultipleResourceActivity> response) {


                Log.d("TAG",response.code()+"");

                String displayResponse = "";

                MultipleResourceActivity resource = response.body();
                List<MultipleResourceActivity.AnalysisData> analysisDataList = resource.analysis;
                String status = resource.status;
                List<MultipleResourceActivity.VenueInfoData> venueInfoDataList = resource.venue_info;

                for (MultipleResourceActivity.AnalysisData analysisData : analysisDataList) {
                    displayResponse += analysisData.venue_forecasted_busyness + " " + analysisData.venue_live_busyness + " " + analysisData.venue_live_busyness_available + " " + analysisData.venue_live_forecasted_delta + "\n";
                }

                displayResponse += " Status is: " + status + "\n";

                for (MultipleResourceActivity.VenueInfoData venueInfoData : venueInfoDataList) {
                    displayResponse += venueInfoData.venue_current_gmttime + " " + venueInfoData.venue_current_localtime + " " + venueInfoData.venue_id + " " + venueInfoData.venue_name + venueInfoData.venue_timezone + "\n";
                }
            }

            @Override
            public void onFailure(Call<MultipleResourceActivity> call, Throwable t) {
                call.cancel();
            }
        });

        return displayResponse;
    }

}
