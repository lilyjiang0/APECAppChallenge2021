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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private ArrayList<String> mAddress = new ArrayList<>();
    private ArrayList<String> mID = new ArrayList<>();
    private int NUM_COLUMNS = 2;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private final static int ALL_PERMISSIONS_RESULT = 101;
    List<StoreModel> storeModels = new ArrayList<>();

    private ArrayList<String> mDImage = new ArrayList<>();
    private ArrayList<String> mDName = new ArrayList<>();
    private ArrayList<String> mDKm = new ArrayList<>();
    private ArrayList<Double> mDKmDou = new ArrayList<>();
    private ArrayList<Integer> mDBusy = new ArrayList<>();
    private ArrayList<String> mDID = new ArrayList<>();
    private ArrayList<String> mDAddress = new ArrayList<>();
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
        db = Room.databaseBuilder(getApplicationContext(), AttractionsDatabase.class, "attraction-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        generateTripIv = findViewById(R.id.generateTripIv);
        generateTripIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button 1 was clicked!
                Intent intent = new Intent(MainActivity.this, GenerateTripActivity.class);
                ArrayList<String> venueNames = new ArrayList<String>();
                ArrayList<String> venueAddresses = new ArrayList<String>();
                List<Attractions.Venue> venueList = db.attractionsDao().getAllVenues();
                for (int i = 0; i < venueList.size(); i++) {
                    venueNames.add(venueList.get(i).getVenueName());
                    venueAddresses.add(venueList.get(i).getVenueAddress());
                }
                intent.putStringArrayListExtra("venue_name", venueNames);
                intent.putStringArrayListExtra("venue_address", venueAddresses);

                intent.putExtra("venue_list", (Serializable) db.attractionsDao().getAllVenues());
                startActivity(intent);
            }
        });

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
        // get info from other activities
        String city = getIntent().getStringExtra("USER_CITY");
        String address = getIntent().getStringExtra("USER_ADDRESS");
        String inputedCity = getIntent().getStringExtra("CITY");
        // set user's city
        if (city != null) {
            // if info come from open screen
            addressTv.setText(city);
            userLocation = address;
        } else if (city == null && inputedCity == null) {
            // if no info
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
            // if info from set location
            addressTv.setText(inputedCity);
            userLocation = inputedCity;

        }
    }

    private void initBrowseAllRecyclerView() {
        // initialise recyclerview for browse all list
        RecyclerView recyclerView = findViewById(R.id.browseAllRv);
        browseAllAdapter browseAllAdapter = new browseAllAdapter(mName, mImage, mBusy, mAddress, mID, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(browseAllAdapter);
    }

    private void initDiscoverRecyclerView() {
        // convert km list from string to integer
        for(String s : mDKm) {
            s = s.replaceAll(" km", "");
            mDKmDou.add(Double.valueOf(s));
        }
        // get data into an object list
        for (int i = 0; i < mDKmDou.size(); i++) {
            discoverListData.add(new DiscoverListData(mDKmDou.get(i), mName.get(i), mImage.get(i), mAddress.get(i), mBusy.get(i), mID.get(i)));
        }
        // sort data by km
        Collections.sort(discoverListData, Comparator.comparingDouble(DiscoverListData ::getKm));
        // remove any attractions busyness that is null or >30%
        discoverListData.removeIf(n -> (n.getBusy() > 30));
        discoverListData.removeIf(n -> (n.getBusy() < 0));
        // clear old km list
        mDKmDou.clear();
        // get sorted data and add them into list
        for (int i = 0; i < discoverListData.size(); i++) {
            mDName.add(discoverListData.get(i).getName());
            mDImage.add(discoverListData.get(i).getImage());
            mDKmDou.add(discoverListData.get(i).getKm());
            mDBusy.add(discoverListData.get(i).getBusy());
            mDID.add(discoverListData.get(i).getId());
            mDAddress.add(discoverListData.get(i).getAddress());
        }

        // put processed data into recyclerview
        RecyclerView recyclerView = findViewById(R.id.discoverRv);
        DiscoverAdapter discoverAdapter = new DiscoverAdapter(mDName, mDImage, mAddress, mDKmDou, mDID,this);
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
                // check api status
                if (response.code() == 200) {
                    attractionList = response.body().getVenues();
                    for (int i = 0; i < attractionList.size(); i++) {
                        // store data into list if the attractions can be forecast
                        if (attractionList.get(i).getForecast()) {
                            forecastableAttractionList.add(attractionList.get(i));
                            mName.add(attractionList.get(i).getVenueName());
                            mAddress.add(attractionList.get(i).getVenueAddress());
                        }
                    }

                    // make string of latitude and longitude input for the distance api
                    for(int i = 0; i < forecastableAttractionList.size(); i++) {
                        addressInputForApi += forecastableAttractionList.get(i).getVenueLat() + "," + forecastableAttractionList.get(i).getVenueLon();
                        if (i != forecastableAttractionList.size() - 1) {
                            addressInputForApi += "|";
                        }
                    }
                    // database work
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
                    // get distance between user and attractions
                    getDistance(userLocation, addressInputForApi);
                    // get current forecast data for each attraction from api
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
        // restrict number of calls needed to make
        if (number < forecastableAttractionList.size()) {
            Observable<MultipleResourceActivity> observable = apiInterface.getForecastLive(apiKey, forecastableAttractionList.get(number).getVenueName(), forecastableAttractionList.get(number).getVenueAddress());
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MultipleResourceActivity>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull MultipleResourceActivity data) {
                            try {
                                if (data.getVenue_info().getVenue_id() != null) {
                                    // add existing data to the list
                                    mID.add(data.getVenue_info().getVenue_id());
                                } else {
                                    mID.add("null");
                                }
                            } catch (Exception e) {
                                Log.d("----------------", "NO");
                            }
                            try {
                                // check if api return null
                                if (data.getAnalysis().getVenue_live_busyness() != null) {
                                    // add existing data to the list
                                    mBusy.add(data.getAnalysis().getVenue_live_busyness());
                                } else {
                                    // otherwise add -100 to the list to present null
                                    mBusy.add(-100);
                                }
                            } catch (Exception e) {
                                Log.d("----------------", "NO");
                            }

                            if (number < forecastableAttractionList.size() - 1) {
                                // continue the call
                                number++;
                                getForecastFromApi();
                            } else {
                                // otherwise set up ui
                                initBrowseAllRecyclerView();
                                initDiscoverRecyclerView();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            // add -100 for error as well
                            mBusy.add(-100);
                            mID.add("null");
                            // continue the call
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
                // check api status
                if ("OK".equalsIgnoreCase(resultDistance.getStatus())) {
                    for (int i = 0; i < resultDistance.getRows().get(0).getElements().size(); i = i + 1) {
                        // add all distances between user and attractions to the list
                        mDKm.add(resultDistance.getRows().get(0).getElements().get(i).getDistance().getText());
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