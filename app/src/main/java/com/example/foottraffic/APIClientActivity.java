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

class APIClientActivity {

    private static Retrofit retrofit = null;
    public static String api_key_private = "pri_e435dec0a2aa4b8e8b4ef42bc990f596";
//    private static List<String> list = new ArrayList<String>();

    static String displayResponse = "";

    public static String getClient(String venue_name, String venue_address) {
//        list.add("pri_e435dec0a2aa4b8e8b4ef42bc990f596");
//        list.add("pub_06ed6fd8e8144be0a8cf5b5113944559");
        venue_name = "McDonalds";
        venue_address = "Ocean Ave, San Fransisco";
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://besttime.app/api/v1/")
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .client(clientBuilder.build())
                .build();

        APIInterfaceActivity apiInterface = retrofit.create(APIInterfaceActivity.class);
        Map<String, String> params = new HashMap<>();
//        Map<String, Object> params = new HashMap<>();
//        params.put("api_key_private", list);
//        params.put("api_key_private", api_key_private);
//        params.put("venue_name", venue_name);
//        params.put("venue_address", venue_address);
//        String strRequestBody = new Gson().toJson(params);
        Call<MultipleResourceActivity> call = apiInterface.doCreateInformationWithField(getApi_key_private(), venue_address);
//        Call<MultipleResourceActivity> call = apiInterface.doCreateInformationWithField(getApi_key_private(), venue_name, venue_address);
//        Call<MultipleResourceActivity> call = apiInterface.doCreateInformationWithField(params);

        call.enqueue(new Callback<MultipleResourceActivity>() {
            @Override
            public void onResponse(Call<MultipleResourceActivity> call, Response<MultipleResourceActivity> response) {

                if (response.code() == 400) {
                    try {
                        Log.v("Error code 400",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (!response.isSuccessful()) {
                    Log.d("Tag", "Code: " + response.code());
                    return;
                }
                Log.d("Tag", "Code: " + response.code());
                MultipleResourceActivity resource = response.body();
                MultipleResourceActivity.AnalysisData analysisData = resource.analysis;
                String status = resource.getStatus();
                MultipleResourceActivity.VenueInfoData venueInfoData = resource.venue_info;
                displayResponse += analysisData.venue_forecasted_busyness + " " + analysisData.venue_live_busyness + " " + analysisData.venue_live_busyness_available + " " + analysisData.venue_live_forecasted_delta + "\n";

                displayResponse += " Status is: " + resource.status + "\n";
                displayResponse += venueInfoData.venue_current_gmttime + " " + venueInfoData.venue_current_localtime + " " + venueInfoData.venue_id + " " + venueInfoData.venue_name + venueInfoData.venue_timezone + "\n";

            }

            @Override
            public void onFailure(Call<MultipleResourceActivity> call, Throwable t) {
                call.cancel();
            }
        });

//        HashMap<String, String> params = new HashMap<>();
//        params.put("api_key_private", "pri_e435dec0a2aa4b8e8b4ef42bc990f596");
////        // venue name
//        params.put("venue_name", venue_name);
////        // venue address
//        params.put("venue_address", venue_address);
//        String strRequestBody = new Gson().toJson(params);
//
//        //create requestbody
//        final RequestBody requestBody = RequestBody.create(MediaType.
//                parse("application/json"),strRequestBody);
//
//        apiInterface.doCreateInformationWithField(requestBody).enqueue(new Callback<MultipleResourceActivity>() {
//            @Override
//            public void onResponse(Call<MultipleResourceActivity> call, Response<MultipleResourceActivity> response) {
//
//                if (!response.isSuccessful()) {
//                    Log.d("Tag", "Code: " + response.code());
//                    return;
//                }
//
//                MultipleResourceActivity resource = response.body();
//                MultipleResourceActivity.AnalysisData analysisData = resource.analysis;
//                String status = resource.getStatus();
//                MultipleResourceActivity.VenueInfoData venueInfoData = resource.venue_info;
//                displayResponse += analysisData.venue_forecasted_busyness + " " + analysisData.venue_live_busyness + " " + analysisData.venue_live_busyness_available + " " + analysisData.venue_live_forecasted_delta + "\n";
//
//                displayResponse += " Status is: " + resource.status + "\n";
//                displayResponse += venueInfoData.venue_current_gmttime + " " + venueInfoData.venue_current_localtime + " " + venueInfoData.venue_id + " " + venueInfoData.venue_name + venueInfoData.venue_timezone + "\n";
//
//            }
//
//            @Override
//            public void onFailure(Call<MultipleResourceActivity> call, Throwable t) {
//                Log.e("Pritish", "Unable to submit post to API.");
//            }
//        });
        Log.d("Tag", displayResponse);

        return displayResponse;
    }

    public static String getApi_key_private() {
        return api_key_private;
    }
}
