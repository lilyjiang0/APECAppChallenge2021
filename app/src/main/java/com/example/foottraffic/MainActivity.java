package com.example.foottraffic;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.example.foottraffic.pojo.AttractionQuietHours;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.MultipleResourceActivity;
import com.example.foottraffic.pojo.ResultDistanceMatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ImageView generateTripIv;
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
    List<StoreModel> storeModels = new ArrayList<>();

    private ArrayList<String> mDImage = new ArrayList<>();
    private ArrayList<String> mDName = new ArrayList<>();
    private ArrayList<String> mDKm = new ArrayList<>();
    private ArrayList<Integer> mDKmInt = new ArrayList<>();
    private String apiKey = "pri_f9cc4722a147468a85e2696073b71b4f";
    private Integer number = 0;
    private String userLocation;
    private String addressInputForApi = "";
    List<DiscoverListData> discoverListData = new ArrayList<DiscoverListData>();

    private String api_key_public_besttime = "pub_e6af9cfdcffc4b5da127d98fec9a9b89";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateTripIv = findViewById(R.id.generateTripIv);
        generateTripIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button 1 was clicked!
                Intent intent = new Intent(MainActivity.this, GenerateTripActivity.class);
                startActivity(intent);
            }
        });
        db = Room.databaseBuilder(getApplicationContext(), AttractionsDatabase.class, "attraction-database").allowMainThreadQueries().build();

//        getAttractionQuietHours("ven_63546a446179304b43514352736d45756530573374614c4a496843", 0);
//
        String start = "Washington,DC";
        String end = "New York City,NY";
        fetchDistance(start, end);


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
//        TextView responseText = findViewById(R.id.responseText);
        // set user's city
        String city = getIntent().getStringExtra("USER_CITY");
        String address = getIntent().getStringExtra("USER_ADDRESS");
        String inputedCity = getIntent().getStringExtra("CITY");
        if (city != null) {
            addressTv.setText(city);
            userLocation = address;
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
            userLocation = inputedCity;

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
//        for(String s : mDKm) {
//            mDKmInt.add(Integer.valueOf(s));
//        }
//        for (int i = 0; i < mDKmInt.size(); i++) {
//            discoverListData.add(new DiscoverListData(mDKmInt.get(i), mName.get(i), mImage.get(i)));
//        }
//        Collections.sort(discoverListData, Comparator.comparingInt(DiscoverListData ::getKm));
//
//        for (int i = 0; i < )

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

                    for(int i = 0; i < attractionList.size(); i++) {
//                        System.out.println(attractionList.get(i).getVenueName() + attractionList.get(i).getVenueAddress());
                        addressInputForApi += attractionList.get(i).getVenueLat() + "," + attractionList.get(i).getVenueLon();
                        if (i != attractionList.size() - 1) {
                            addressInputForApi += "|";
                        }
                    }
                    Log.d("---------------------------", addressInputForApi);


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
//                    getDistance(userLocation, addressInputForApi);
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
                                if (data.getAnalysis().getVenue_live_busyness() != null) {
                                    mBusy.add(data.getAnalysis().getVenue_live_busyness());
                                } else {
                                    mBusy.add(0);
                                }
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
                    for (int i = 0; i < resultDistance.getRows().size(); i = i + 1) {
                        ResultDistanceMatrix.Row infoDistanceMatrix = resultDistance.getRows().get(i);
                        for (int j = 0; j < resultDistance.getRows().size(); j = j + 1) {
                            ResultDistanceMatrix.Element distanceElement = infoDistanceMatrix.getElements().get(j);
                            if ("OK".equalsIgnoreCase(distanceElement.getStatus())) {
                                ResultDistanceMatrix.Duration itemDuration = distanceElement.getDuration();
                                ResultDistanceMatrix.Distance itemDistance = distanceElement.getDistance();
                                String totalDistance = itemDistance.getText();
                                String totalDuration = itemDuration.getText();
                                for (int x = 0; x < resultDistance.getOriginAddresses().size(); x = x + 1) {
                                    String originAddress = resultDistance.getOriginAddresses().get(x);
                                    for (int y = 0; y < resultDistance.getDestinationAddresses().size(); y = y + 1) {
                                        String destinationAddress = resultDistance.getDestinationAddresses().get(y);
                                        storeModels.add(new StoreModel(originAddress, destinationAddress, totalDistance, totalDuration));
                                        System.out.println(y + ": " + storeModels.get(y).getOriginAddress() + " + " + storeModels.get(y).getDestinationAddress() + " + " + storeModels.get(y).getDistance() + storeModels.get(y).getDuration() + "\n");
                                    }
                                }
                            }
                        }
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

    private void getDistance(String start_latLngString, String dest_latLngString) {
        apiInterface = GoogleMapAPIClientActivity.getClient().create(APIInterfaceActivity.class);
        Call<ResultDistanceMatrix> call = apiInterface.getDistance(start_latLngString, dest_latLngString, GoogleMapAPIClientActivity.GOOGLE_PLACE_API_KEY);
        call.enqueue(new Callback<ResultDistanceMatrix>() {
            @Override
            public void onResponse(Call<ResultDistanceMatrix> call, Response<ResultDistanceMatrix> response) {
                ResultDistanceMatrix resultDistance = response.body();
                if ("OK".equalsIgnoreCase(resultDistance.getStatus())) {
                    for (int i = 0; i < resultDistance.getRows().size(); i = i + 1) {
                        mDKm.add(resultDistance.getRows().get(i).getElements().get(0).getDistance().getText());
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

//    private void getAttractionQuietHours(String venue_id, Integer day_step) {
//        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
//        Call<AttractionQuietHours> call = apiInterface.getQuietHours(api_key_public_besttime, venue_id, day_step);
//        call.enqueue(new Callback<AttractionQuietHours>() {
//            @Override
//            public void onResponse(Call<AttractionQuietHours> call, Response<AttractionQuietHours> response) {
////                AttractionQuietHours quietHoursLists = response.body();
//                System.out.println("response code is: " + response.code());
//                try {
//                    System.out.println("response error is: " + response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                List<Integer> quietHours = quietHoursLists.getAnalysis().getQuietHoursList();
////                for (int i = 0; i < quietHours.size() - 1; i++) {
//////                    System.out.println("old: " + quietHours.get(i));
////                    if (quietHours.get(i) >= quietHours.get(i + 1)) {
////                        for (int j = i + 1; j < quietHours.size(); j++) {
////                            Integer newVal = 12 + quietHours.get(j);
////                            quietHours.set(j, newVal);
////                        }
////                    }
//////                    System.out.println("new: " + quietHours.get(i));
////                }
////                System.out.println("Code is: " + response.code());
//            }
//
//            @Override
//            public void onFailure(Call<AttractionQuietHours> call, Throwable t) {
//                call.cancel();
//            }
//        });
//
//    }
}