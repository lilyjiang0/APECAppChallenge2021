package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        TextView addressTv = findViewById(R.id.addressTv);

        String city = getIntent().getStringExtra("USER_CITY");
        if (city != null) {
            addressTv.setText(city);
        } else {
            addressTv.setText("Set Location");
            addressTv.setTypeface(addressTv.getTypeface(), Typeface.BOLD_ITALIC);
            addressTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SetLocationActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}