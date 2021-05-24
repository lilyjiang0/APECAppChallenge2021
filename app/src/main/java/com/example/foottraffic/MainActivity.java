package com.example.foottraffic;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.api.GoogleMapAPIClientActivity;
import com.example.foottraffic.database.AttractionsDatabase;
import com.example.foottraffic.database.StoreModel;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.MultipleResourceActivity;
import com.example.foottraffic.pojo.ResultDistanceMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private APIInterfaceActivity apiInterface;
    public AttractionsDatabase db;
    List<Attractions.Venue> attractionList = new ArrayList<>();
    List<Attractions.Venue> forecastableAttractionList = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<Integer> mBusy = new ArrayList<>();
    private int NUM_COLUMNS = 2;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 101;
    List<StoreModel> storeModels;

    private ArrayList<String> mDImage = new ArrayList<>();
    private ArrayList<String> mDName = new ArrayList<>();
    private ArrayList<String> mDKm = new ArrayList<>();
    private String apiKey = "pri_1008d36a82b4452393139213da2109c5";
    private Integer busy;
    private Integer number = 0;

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

        String start = "Washington,DC";
        String end = "New York City,NY";
        fetchDistance(start, end);
//        System.out.println("name is: " + storeModels + " total distance is: ");

        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");
        mDKm.add("1km");


    }

    private void setUserCity() {
        TextView addressTv = findViewById(R.id.addressTv);
//        TextView responseText = findViewById(R.id.responseText);
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

    private void initBrowseAllRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.browseAllRv);
        browseAllAdapter browseAllAdapter = new browseAllAdapter(mName, mImage, mBusy,this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(browseAllAdapter);
    }

    private void initDiscoverRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.discoverRv);
        DiscoverAdapter discoverAdapter = new DiscoverAdapter(mName, mImage, mDKm, this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(discoverAdapter);
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
                        if (attractionList.get(i).getForecast()) {
                            forecastableAttractionList.add(attractionList.get(i));
                            mName.add(attractionList.get(i).getVenueName());
                        }
                    }
                    Log.d("---------------------------", String.valueOf(mName.size()));
                    Log.d("---------------------------", String.valueOf(forecastableAttractionList.size()));

                    for(Attractions.Venue venue : forecastableAttractionList) {
                        System.out.println(venue.getVenueName() + venue.getVenueAddress());
                    }

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
                    getForecastFromApi();
                }
            }

            @Override
            public void onFailure(Call<Attractions> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Api call failed");
            }
        });
    }

    private void getForecastFromApi() {
        // call api to get forecast
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        if (number < forecastableAttractionList.size()) {
            Observable<MultipleResourceActivity> observable = apiInterface.getForecast(apiKey, forecastableAttractionList.get(number).getVenueName(), forecastableAttractionList.get(number).getVenueAddress());
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MultipleResourceActivity>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull MultipleResourceActivity data) {
                            try {
                                Log.d("getVenue_name", String.valueOf(number) + String.valueOf(data.getVenue_info().getVenue_name()));
                            } catch (Exception e) {
                                Log.d("----------------", "NO");
                            }

                            try {
                                mBusy.add(data.getAnalysis().getVenue_live_busyness());
                                Log.d("getVenue_live_busyness", String.valueOf(number) + String.valueOf(data.getAnalysis().getVenue_live_busyness()));
                            } catch (Exception e) {
                                Log.d("----------------", "NO");
                            }


                            if (number < forecastableAttractionList.size() - 1) {
                                number++;
                                getForecastFromApi();
                            } else {
                                initBrowseAllRecyclerView();

//                            Executors.newSingleThreadExecutor().execute(() -> {
//                                // insert success word;
//                                db.wordDao().insert(wordList.toArray(new Word[0]));
//                            });
//                            refreshData();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            Log.d("=======================", "API CALL ERROR");
                            mBusy.add(0);
                            number++;
                            getForecastFromApi();
                        }

                        @Override
                        public void onComplete() {
                            Log.d("======================", "complete");
                        }
                    });
        }
    }

    private void fetchDistance(String start_latLngString, String dest_latLngString) {

        // destination: "locationA.lat" + "," + "locationA.lng"
        apiInterface = GoogleMapAPIClientActivity.getClient().create(APIInterfaceActivity.class);
        Call<ResultDistanceMatrix> call = apiInterface.getDistance(start_latLngString, dest_latLngString, GoogleMapAPIClientActivity.GOOGLE_PLACE_API_KEY);
        call.enqueue(new Callback<ResultDistanceMatrix>() {
            @Override
            public void onResponse(Call<ResultDistanceMatrix> call, Response<ResultDistanceMatrix> response) {
                ResultDistanceMatrix resultDistance = response.body();
                if ("OK".equalsIgnoreCase(resultDistance.getStatus())) {
//                    ResultDistanceMatrix.InfoDistanceMatrix infoDistanceMatrix = resultDistance.rows.get(0);
//                    ResultDistanceMatrix.InfoDistanceMatrix.DistanceElement distanceElement = infoDistanceMatrix.elements.get(0);
                    ResultDistanceMatrix.Row infoDistanceMatrix = resultDistance.getRows().get(0);
                    ResultDistanceMatrix.Element distanceElement = infoDistanceMatrix.getElements().get(0);

                    if ("OK".equalsIgnoreCase(distanceElement.getStatus())) {
                        ResultDistanceMatrix.Duration itemDuration = distanceElement.getDuration();
                        ResultDistanceMatrix.Distance itemDistance = distanceElement.getDistance();
                        String totalDistance = itemDistance.getText();
                        String totalDuration = itemDuration.getText();
                        String name = resultDistance.getOriginAddresses().get(0);
//                        storeModels.add(new StoreModel(name, "vicinity", totalDistance, totalDuration));
//
                    }
                } else {
                    System.out.println("Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResultDistanceMatrix> call, Throwable t) {
                call.cancel();
            }
        });

    }
}