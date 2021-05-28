package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateResultActivity extends AppCompatActivity {

    private ArrayList<String> venueNameParams = new ArrayList<>();
    private ArrayList<String> venueAddressParams = new ArrayList<>();
    private ArrayList<String> venueOpenParams = new ArrayList<>();
    private ArrayList<String> venueClosedParams = new ArrayList<>();
    private List<List<Integer>> quietHourParams = new ArrayList<List<Integer>>();
    private Integer dayOfWeek;
    private ArrayList<String> suggestion = new ArrayList<>();
    private String no = "No suggestion";
    private ArrayList<List<Integer>> qH = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_result);
        venueNameParams = getIntent().getStringArrayListExtra("venueNameParams");
        venueAddressParams = getIntent().getStringArrayListExtra("venueAddressParams");
        venueOpenParams = getIntent().getStringArrayListExtra("venue_open");
        venueClosedParams = getIntent().getStringArrayListExtra("venue_closed");
        quietHourParams = (List<List<Integer>>) getIntent().getSerializableExtra("quietHourParams");
        dayOfWeek = getIntent().getIntExtra("dayOfWeek", 0);
        System.out.println("result: " + venueOpenParams.size());;
        for (int i = 0; i < quietHourParams.size(); i++) {
            if (quietHourParams.get(i).size() == 0) {
                suggestion.add(no);
            } else {
                Integer start = quietHourParams.get(i).get(0);
                for (int j = 0; j < quietHourParams.get(i).size()-1; j++) {
                    if (quietHourParams.get(i).get(j) >= quietHourParams.get(i).get(j+1)) {
                        String s;
                        String e;
                        if (start > 12) {
                            start = start - 12;
                            s = start + "pm - ";
                        } else {
                            s = start + "am - ";
                        }
                        int end = quietHourParams.get(i).get(j);
                        if (end > 12) {
                            end = end - 12;
                            e = end + "pm";
                        } else {
                            e = end + "am";
                        }
                        suggestion.add(s + e);
                    }
                }
            }
        }



        initGenerateResultRecyclerView();
    }

    private void initGenerateResultRecyclerView() {
        // initialise recyclerview for browse all list
        RecyclerView recyclerView = findViewById(R.id.discoverRv);
        generateAdapter generateAdapter = new generateAdapter(venueNameParams, venueOpenParams, venueClosedParams, quietHourParams, dayOfWeek, suggestion, GenerateResultActivity.this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generateAdapter);
    }

}