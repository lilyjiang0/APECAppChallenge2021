package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.libraries.places.api.Places;

public class SetLocationActivity extends AppCompatActivity {
    EditText enterAddTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        enterAddTv = findViewById(R.id.enterAddTv);

        Places.initialize(getApplicationContext(), "AIzaSyCsSbWv-fDswmMoEaTTlvsOCwfn06vOIR0");
    }
}