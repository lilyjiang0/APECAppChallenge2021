package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView addressTv = findViewById(R.id.addressTv);

        String city = getIntent().getStringExtra("USER_CITY");
        if (city != null) {
            addressTv.setText(city);
        } else {
            addressTv.setText("Set Location");
        }


    }
}