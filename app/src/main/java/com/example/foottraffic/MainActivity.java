package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
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
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private int NUM_COLUMNS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(), AttractionsDatabase.class, "attraction-database").allowMainThreadQueries().build();

        setUserCity();
        getAttractionsFromApi();
        mImage.add("https://images.unsplash.com/photo-1595644112441-039eebee38a5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=967&q=80");
        mImage.add("https://images.unsplash.com/photo-1620943694949-b438574c75a8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1489&q=80");
        mImage.add("https://images.unsplash.com/photo-1558949315-d484360311fc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=967&q=80");
        mImage.add("https://images.unsplash.com/photo-1556435880-9f3fe3aae5bc?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
        mImage.add("https://images.unsplash.com/photo-1615717591219-51be24923084?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80");
        mImage.add("https://images.unsplash.com/photo-1513157060608-f5117117a051?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80");
        mImage.add("https://images.unsplash.com/photo-1563536702-02895c461678?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1489&q=80");
        mImage.add("https://images.unsplash.com/photo-1580420832496-de48689556ee?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80");
        mImage.add("https://images.unsplash.com/photo-1595750376349-54363e64af36?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
        mImage.add("https://images.unsplash.com/photo-1567309676325-2b237f42861f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80");


    }

    private void setUserCity() {
        TextView addressTv = findViewById(R.id.addressTv);
        TextView responseText = findViewById(R.id.responseText);
        // set user's city
        String city = getIntent().getStringExtra("USER_CITY");
        String inputedCity = getIntent().getStringExtra("CITY");
        if (city != null) {
            addressTv.setText(city);
        } else if (city == null && inputedCity == null) {
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
    }

    private void getAttractionsFromApi() {
        // call api to get attrations
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        Map<String, String> params = new HashMap<>();
        Call<Attractions> call = apiInterface.getAttraction();
        call.enqueue(new Callback<Attractions>() {
            @Override
            public void onResponse(Call<Attractions> call, Response<Attractions> response) {
                Log.d("Tag", "Code: " + response.code());

                if (response.code() == 200) {
                    attractionList = response.body().getVenues();
                    for (int i = 0; i < attractionList.size(); i++) {
                        if (attractionList.get(i).getForecast() == true) {
                            mName.add(attractionList.get(i).getVenueName());
                        }
                    }
                    Log.d("---------------------------", String.valueOf(mName.size()));
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
                    RecyclerView attraction = findViewById(R.id.browseAllRv);
                    initRecyclerView();

                }
            }

            @Override
            public void onFailure(Call<Attractions> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Api call failed");
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.browseAllRv);
        browseAllAdapter browseAllAdapter = new browseAllAdapter(mName, mImage, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(browseAllAdapter);
    }
}