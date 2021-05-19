package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.room.Room;

import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.database.AttractionsDatabase;
import com.example.foottraffic.pojo.Attractions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private APIInterfaceActivity apiInterface;
    public AttractionsDatabase db;
    List<Attractions.Venue> attractionList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(), AttractionsDatabase.class, "attraction-database").allowMainThreadQueries().build();
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

        // call api to get attrations
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        Map<String, String> params = new HashMap<>();
        Call<Attractions> call = apiInterface.getAttraction();
        call.enqueue(new Callback<Attractions>() {
            @Override
            public void onResponse(Call<Attractions> call, Response<Attractions> response) {
                Log.d("Tag", "Code: " + response.code());

                if (response.code() == 200) {
                    String venue = response.body().getVenues().get(0).getVenueName();
//
                    Log.d("===================", venue);
                    responseText.setText(venue);

                    attractionList = response.body().getVenues();

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < attractionList.size(); i++) {
                                if (attractionList.get(i).getForecast() == true) {
                                    db.attractionsDao().insert(attractionList.get(i));
                                }
                            }
                        }
                    });


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