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
    private List<List<Integer>> quietHourParams = new ArrayList<List<Integer>>();
    private Integer dayOfWeek;
    private RecyclerView discoverRv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_result);
//        e.add("qqq");
//        e.add("222");
        venueNameParams = getIntent().getStringArrayListExtra("venueNameParams");
        System.out.println("It is: in another page: " + Arrays.toString(venueNameParams.toArray()));
        venueAddressParams = getIntent().getStringArrayListExtra("venueAddressParams");
        quietHourParams = (List<List<Integer>>) getIntent().getSerializableExtra("quietHourParams");
        dayOfWeek = getIntent().getIntExtra("dayOfWeek", 0);
//        System.out.println("name: " + venueNameParams.size());
//        System.out.println("address: " + venueAddressParams.size());
//        System.out.println("hour: " + quietHourParams.size());
//        System.out.println("day: " + dayOfWeek);
        initGenerateResultRecyclerView();
    }

    private void initGenerateResultRecyclerView() {
        // initialise recyclerview for browse all list
        RecyclerView recyclerView = findViewById(R.id.discoverRv);
        generateAdapter generateAdapter = new generateAdapter(venueNameParams, quietHourParams, dayOfWeek, GenerateResultActivity.this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generateAdapter);
    }

}