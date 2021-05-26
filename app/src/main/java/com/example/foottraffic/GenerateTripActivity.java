package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.ForecastData;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateTripActivity extends AppCompatActivity {

    private APIInterfaceActivity apiInterface;
    private String apiKey = "pri_f9cc4722a147468a85e2696073b71b4f";
    private AutoCompleteTextView place1;
    private AutoCompleteTextView place2;
    private AutoCompleteTextView place3;
    private AutoCompleteTextView place4;
    private AutoCompleteTextView place5;

    private List<Attractions.Venue> venueList;
//    ArrayAdapter<List<Map<String, String>>> adapter;
    ArrayAdapter<String> adapter;
    ArrayList<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        venueList = (List<Attractions.Venue>) getIntent().getSerializableExtra("venue_list");
        setContentView(R.layout.activity_generate_trip);
//        List<Map<String, String>> map = new ArrayList<Map<String, String>>();
        Map<String, String> item = new HashMap<String, String>();
        for (int i = 0; i < venueList.size(); i++) {
            item.put(venueList.get(i).getVenueAddress(), venueList.get(i).getVenueName());
//            map.add(item);
            strings.add(venueList.get(i).getVenueAddress());
        }
        // Initialise adapter
//        adapter = new ArrayAdapter<List<Map<String, String>>>(this, android.R.layout.simple_dropdown_item_1line, strings);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, strings);

        // destination addresses
        // place 1
        place1 = findViewById(R.id.place1AutoCompleteTv);
        place1.setThreshold(1);
        place1.setAdapter(adapter);
        // place 2
        place2 = findViewById(R.id.place2AutoCompleteTv);
        place2.setThreshold(1);
        place2.setAdapter(adapter);
        if (item.containsKey(place1.getText())) {
//            getHourDataFromApi(item.get(place1.getText().toString()), place1.getText().toString());
            System.out.println("yes1");
            System.out.println(place1.getText().toString());
        }
//        if(!place2.toString().isEmpty()) {
////            getHourDataFromApi(item.get(place2.getText().toString()), place2.getText().toString());
//            System.out.println("yes2");
//        }
    }

    private void getHourDataFromApi(String name, String address) {
    // call api to get hour data
    apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
    Call<ForecastData> call = apiInterface.getForecast(apiKey, name, address);
    call.enqueue(new Callback<ForecastData>() {
        @Override
        public void onResponse(Call<ForecastData> call, Response<ForecastData> response) {
            try {
                Log.d("---------", response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // check api status
            if (response.code() == 200) {
                List<Integer> myList = new ArrayList<>();
                myList = response.body().getAnalysis().get(0).getDayRaw();
                System.out.println(Arrays.toString(myList.toArray()));
            }
        }
        @Override
        public void onFailure(Call<ForecastData> call, Throwable t) {
            call.cancel();
            Log.d("ERROR", "Api call failed");
        }
        });
    }
}