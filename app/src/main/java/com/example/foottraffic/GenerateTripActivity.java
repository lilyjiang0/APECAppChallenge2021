package com.example.foottraffic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.ForecastData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

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

    private ArrayList<String> venueNames = new ArrayList<>(MainActivity.mName);
    private ArrayList<String> venueAddresses = new ArrayList<>(MainActivity.mAddress);
    ArrayAdapter<String> adapter;

    List<Integer> myDataList = new ArrayList<>();
    List<Map<String, String>> details = new ArrayList<Map<String, String>>();

    private Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> n = new ArrayList<>(MainActivity.mName);
//        venueList = (List<Attractions.Venue>) getIntent().getSerializableExtra("venue_list");
        setContentView(R.layout.activity_generate_trip);
        Map<String, String> item = new HashMap<String, String>();
        for (int i = 0; i < venueNames.size(); i++) {
            item.put(venueNames.get(i), venueAddresses.get(i));
        }
        // Initialise adapter
//        adapter = new ArrayAdapter<List<Map<String, String>>>(this, android.R.layout.simple_dropdown_item_1line, strings);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, venueNames);

        // destination addresses
        // place 1
        place1 = findViewById(R.id.place1AutoCompleteTv);
        place1.setThreshold(1);
        place1.setAdapter(adapter);
        // place 2
        place2 = findViewById(R.id.place2AutoCompleteTv);
        place2.setThreshold(1);
        place2.setAdapter(adapter);
        place1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (item.containsKey(place1.getText().toString())) {
                    getHourDataFromApi(place1.getText().toString(), item.get(place1.getText().toString()));
                    System.out.println(place1.getText().toString());
                    Map<String, String> element = new HashMap<String, String>();
                    element.put("venue_name", place1.getText().toString());
                    element.put("quiet_hours", Arrays.toString(myDataList.toArray()));
                    details.add(element);
                }
            }
        });
        place2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (item.containsKey(place2.getText().toString())) {
                    getHourDataFromApi(place2.getText().toString(), item.get(place2.getText().toString()));
                    System.out.println(place2.getText().toString());
                    Map<String, String> element = new HashMap<String, String>();
                    element.put("venue_name", place2.getText().toString());
                    element.put("quiet_hours", Arrays.toString(myDataList.toArray()));
                    details.add(element);
                }
            }
        });


        btnGenerate = findViewById(R.id.btnGenerate);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenerateTripActivity.this, GenerateBtnActivity.class);
                intent.putExtra("list", (Serializable) details);
                startActivity(intent);
            }
        });

    }

    private void getHourDataFromApi(String name, String address) {
        // call api to get hour data
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        Call<ForecastData> call = apiInterface.getForecast(apiKey, name, address);
        call.enqueue(new Callback<ForecastData>() {
            @Override
            public void onResponse(Call<ForecastData> call, Response<ForecastData> response) {
                // check api status
                Log.d("HEY", String.valueOf(response.body()));
                ForecastData result = response.body();
                Log.d("HEY2", String.valueOf(response.body()));
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(result.getVenueInfo().getVenueTimezone()));
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                Log.d("HEY3", String.valueOf(response.body()));
                if (result.getAnalysis().get(dayOfWeek).getQuietHours() == null) {
                    System.out.println("This venue is closed.");
                    Log.d("The Code is: " , String.valueOf(response.code()));
                }
                if (response.code() == 200) {
                    myDataList = result.getAnalysis().get(dayOfWeek).getQuietHours();
                    System.out.println(Arrays.toString(myDataList.toArray()));
                }
            }
            @Override
            public void onFailure(Call<ForecastData> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Api call failed. Message is: " + t.getMessage() + " Cause is: " + t.getCause());
    //            System.out.println(call.);
            }
            });
    }
}