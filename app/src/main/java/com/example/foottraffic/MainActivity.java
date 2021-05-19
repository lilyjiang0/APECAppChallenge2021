package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.MultipleResourceActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.foottraffic.APIClientActivity.getApi_key_private;

public class MainActivity extends AppCompatActivity {
    private String api_key_private = "pri_e435dec0a2aa4b8e8b4ef42bc990f596";
    private APIInterfaceActivity apiInterface;
    private String venue_name = "McDonalds";
    private String venue_address = "Ocean Ave, San Fransisco";
    static String displayResponse = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView addressTv = findViewById(R.id.addressTv);
        TextView responseText = findViewById(R.id.responseText);

        // set user's city
        String city = getIntent().getStringExtra("USER_CITY");
        String inputedCity = getIntent().getStringExtra("CITY");
        if (city != null) {
            addressTv.setText(city);
        } else if (city == null && inputedCity == null){
            addressTv.setText("Set Location");
            addressTv.setTypeface(addressTv.getTypeface(), Typeface.BOLD_ITALIC);
            addressTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SetLocationActivity.class);
                    startActivity(intent);
                }
            });
        } else if (city == null && inputedCity != null) {
            addressTv.setText(inputedCity);
        }

        // API Part
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        Map<String, String> params = new HashMap<>();
//        Call<MultipleResourceActivity> call = apiInterface.doCreateInformationWithField(getApi_key_private(), venue_address);
//        call.enqueue(new Callback<MultipleResourceActivity>() {
//            @Override
//            public void onResponse(Call<MultipleResourceActivity> call, Response<MultipleResourceActivity> response) {
//                Log.d("Tag", "Code: " + response.code());
//
//                MultipleResourceActivity.AnalysisData analysisData = response.body().getAnalysis();
//                MultipleResourceActivity.VenueInfoData venueInfoData = response.body().getVenue_info();
//                String status = response.body().getStatus();
//
//                Log.d("===================", String.valueOf(analysisData.venue_forecasted_busyness));
//
//                responseText.setText(String.valueOf(analysisData.venue_forecasted_busyness));
//
//            }
//
//            @Override
//            public void onFailure(Call<MultipleResourceActivity> call, Throwable t) {
//                call.cancel();
//                Log.d("ERROR", "Api call failed");
//            }
//        });


        Call<Attractions> call = apiInterface.getAttraction();
        call.enqueue(new Callback<Attractions>() {
            @Override
            public void onResponse(Call<Attractions> call, Response<Attractions> response) {
                if (response.code() == 200) {
                    Log.d("Tag", "Code: " + response.code());

                    String venue = response.body().getVenues().get(0).getVenueName();

                    Log.d("===================", venue);

                    responseText.setText(venue);
                }


            }

            @Override
            public void onFailure(Call<Attractions> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Api call failed");
            }
        });



    }
}