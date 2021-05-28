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
    private RecyclerView discoverRv;


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


        initGenerateResultRecyclerView();
    }

    private void initGenerateResultRecyclerView() {
        // initialise recyclerview for browse all list
        RecyclerView recyclerView = findViewById(R.id.discoverRv);
        generateAdapter generateAdapter = new generateAdapter(venueNameParams, venueOpenParams, venueClosedParams, quietHourParams, dayOfWeek, GenerateResultActivity.this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generateAdapter);
    }

}