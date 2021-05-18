package com.example.foottraffic;

import android.util.Log;

import com.example.foottraffic.pojo.MultipleResourceActivity;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
                .baseUrl("https://besttime.app/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build();

        APIInterfaceActivity apiInterface = retrofit.create(APIInterfaceActivity.class);
        Call<MultipleResourceActivity> call = apiInterface.doCreateInformationWithField(api_key_private, venue_name, venue_address);
        call.enqueue(new Callback<MultipleResourceActivity>() {
            @Override
            public void onResponse(Call<MultipleResourceActivity> call, Response<MultipleResourceActivity> response) {

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
        return displayResponse;
    }

}
