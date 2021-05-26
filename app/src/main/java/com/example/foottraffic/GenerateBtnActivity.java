package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateBtnActivity extends AppCompatActivity {

    private TextView editTextTextPersonName;
    private List<Map<String, String>> details = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_btn);
        details = (List<Map<String, String>>) getIntent().getSerializableExtra("list");

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName.setText(details.get(0).get("venue_name"));

    }
}