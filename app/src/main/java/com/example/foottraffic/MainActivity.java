package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foottraffic.pojo.MultipleResourceActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private String api_key_private = "pri_e435dec0a2aa4b8e8b4ef42bc990f596";
    String venue_name = "McDonalds";
    String venue_address = "Ocean Ave, San Fransisco";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView addressTv = findViewById(R.id.addressTv);
        TextView responseText = findViewById(R.id.responseText);

        String city = getIntent().getStringExtra("USER_CITY");
        if (city != null) {
            addressTv.setText(city);
        } else {
            addressTv.setText("Set Location");
            addressTv.setTypeface(addressTv.getTypeface(), Typeface.BOLD_ITALIC);
            addressTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SetLocationActivity.class);
                    startActivity(intent);
                }
            });
        }
        // API Part
//        String result = APIClientActivity.getClient(venue_name, venue_address);
//        responseText.setText(result);
//        System.out.println(result.toString());
//        APIInterfaceActivity apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
//        Call<MultipleResourceActivity> call = apiInterface.doCreateInformationWithField(api_key_private, venue_name, venue_address);
//        call.enqueue(new Callback<MultipleResourceActivity>() {
//            @Override
//            public void onResponse(Call<MultipleResourceActivity> call, Response<MultipleResourceActivity> response) {
//
//
//                Log.d("TAG",response.code()+"");
//
//                String displayResponse = "";
//
//                MultipleResourceActivity resource = response.body();
//                List<MultipleResourceActivity.AnalysisData> analysisDataList = resource.analysis;
//                String status = resource.status;
//                List<MultipleResourceActivity.VenueInfoData> venueInfoDataList = resource.venue_info;
//
//                for (MultipleResourceActivity.AnalysisData analysisData : analysisDataList) {
//                    displayResponse += analysisData.venue_forecasted_busyness + " " + analysisData.venue_live_busyness + " " + analysisData.venue_live_busyness_available + " " + analysisData.venue_live_forecasted_delta + "\n";
//                }
//
//                displayResponse += " Status is: " + status + "\n";
//
//                for (MultipleResourceActivity.VenueInfoData venueInfoData : venueInfoDataList) {
//                    displayResponse += venueInfoData.venue_current_gmttime + " " + venueInfoData.venue_current_localtime + " " + venueInfoData.venue_id + " " + venueInfoData.venue_name + venueInfoData.venue_timezone + "\n";
//                }
//
//
//                responseText.setText(displayResponse);
//                System.out.println(displayResponse.toString());
//
//            }
//
//            @Override
//            public void onFailure(Call<MultipleResourceActivity> call, Throwable t) {
//                call.cancel();
//            }
//        });

    }
}