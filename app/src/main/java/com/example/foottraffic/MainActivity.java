package com.example.foottraffic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String venueName = "KFC";
        String venueAddress = "Ocean Ave, San Fransisco";
        String res = null;
        res = ConnectAPIActivity.result(venueName, venueAddress);
        // ConnectAPIActivity.main(venueName, venueAddress);
        System.out.println("======" + res + "+++++");

    }
}