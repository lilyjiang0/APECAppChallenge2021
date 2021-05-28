package com.example.foottraffic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.ForecastData;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

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
    private CalendarView calendarView;

    private ArrayList<String> venueNames = new ArrayList<>(MainActivity.mName);
    private ArrayList<String> venueAddresses = new ArrayList<>(MainActivity.mAddress);
    ArrayAdapter<String> adapter;

    List<Integer> myDataList = new ArrayList<>();
    List<Map<String, String>> details = new ArrayList<Map<String, String>>();
    Map<String, String> detailParams = new HashMap<String, String>();
    private ArrayList<String> venueNameParams = new ArrayList<>();
    private ArrayList<String> venueAddressParams = new ArrayList<>();
    private ArrayList<String> venueOpenParams = new ArrayList<>();
    private ArrayList<String> venueClosedParams = new ArrayList<>();
    private List<List<Integer>> quietHourParams = new ArrayList<List<Integer>>();
    private Integer dayOfWeek;
    private Button btnGenerate;

    private Map<String, String> vNames = new HashMap<>();
    private Map<String, Integer> vOpen = new HashMap<>();
    private Map<String, Integer> vClose = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> n = new ArrayList<>(MainActivity.mName);
//        venueList = (List<Attractions.Venue>) getIntent().getSerializableExtra("venue_list");
        setContentView(R.layout.activity_generate_trip);
        TextView backBtn3 = findViewById(R.id.backBtn4);
        ImageView backBtn4 = findViewById(R.id.backBtn3);

        backBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        backBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

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
                    if (!vNames.containsKey("place1")) {
                        vNames.put("place1", place1.getText().toString());
                    }
                    else if (vNames.containsKey("place1")) {
                        vNames.replace("place1", place1.getText().toString());
                    }
//                    venueAddressParams.add(item.get(place1.getText().toString()));
                    quietHourParams.add(myDataList);
//                    detailParams.put("venue_name1", place1.getText().toString());
//                    detailParams.put("quiet_hours1", Arrays.toString(myDataList.toArray()));
//                    details.add(detailParams);
//                    System.out.println("The venue name params are: " + Arrays.toString(venueNameParams.toArray()));
                }
            }

        });

        place2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (item.containsKey(place2.getText().toString())) {
                    getHourDataFromApi(place2.getText().toString(), item.get(place2.getText().toString()));
                    System.out.println(place2.getText().toString());
                    if (!vNames.containsKey("place2")) {
                        vNames.put("place2", place2.getText().toString());
                    }
                    else if (vNames.containsKey("place2")) {
                        vNames.replace("place2", place2.getText().toString());
                    }
//                    venueAddressParams.add(item.get(place2.getText().toString()));
                    quietHourParams.add(myDataList);
                    System.out.println("Queit hours are: " + Arrays.toString(quietHourParams.toArray()));
//                    detailParams.put("venue_name2", place2.getText().toString());
//                    detailParams.put("quiet_hours2", Arrays.toString(myDataList.toArray()));
//                    details.add(detailParams);
                }
            }
        });


        // date
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2;
                if (dayOfWeek < 0) {
                    dayOfWeek = 6;
                }
//                detailParams.put("dayInt", String.valueOf(dayOfWeek));
                System.out.println("Day int is: " + dayOfWeek);
//                details.add(detailParams);
            }
        });


        btnGenerate = findViewById(R.id.btnGenerate);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vNames.containsKey("place1")) {
                    System.out.println("Must fill in Address 1 filed");
                    return;
                }else if (vNames.containsKey("place1")) {
                    venueNameParams.add(vNames.get("place1"));
                    if (vNames.containsKey("place2")) {
                        venueNameParams.add(vNames.get("place2"));
                    }
                }
                System.out.println("It is: " + Arrays.toString(venueNameParams.toArray()));
                Intent intent = new Intent(GenerateTripActivity.this, GenerateResultActivity.class);
//                intent.putExtra("list", (Serializable) detailParams);
                intent.putStringArrayListExtra("venueNameParams", venueNameParams);
                intent.putStringArrayListExtra("venueAddressParams", venueAddressParams);
                intent.putStringArrayListExtra("venue_open", venueOpenParams);
                intent.putStringArrayListExtra("venue_closed", venueClosedParams);
                intent.putExtra("quietHourParams", (Serializable) quietHourParams);
                intent.putExtra("dayOfWeek", dayOfWeek);
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
                ForecastData result = response.body();
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(result.getVenueInfo().getVenueTimezone()));
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2;
                if (result.getAnalysis().get(dayOfWeek).getQuietHours() == null) {
                    System.out.println("This venue is closed.");
                    Log.d("The Code is: " , String.valueOf(response.code()));
                }
                if (response.code() == 200) {
                    if (dayOfWeek < 0) {
                        dayOfWeek = 6;
                    }
                    myDataList = result.getAnalysis().get(dayOfWeek).getQuietHours();
                    System.out.println(Arrays.toString(myDataList.toArray()));
                    venueOpenParams.add(String.valueOf(vOpen.put("venue_open", result.getAnalysis().get(dayOfWeek).getDayInfo().getVenueOpen())));
                    venueClosedParams.add(String.valueOf(vClose.put("venue_closed", result.getAnalysis().get(dayOfWeek).getDayInfo().getVenueClosed())));
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