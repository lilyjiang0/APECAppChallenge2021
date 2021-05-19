package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class OpenScreenActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // if user already give permission
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // get user location
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            Double latitude = location.getLatitude();
                            Double longitude = location.getLongitude();
                            try {
                                TextView addressTv = findViewById(R.id.addressTv);
                                Geocoder geocoder = new Geocoder(OpenScreenActivity.this, Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                // get city of the user
                                city = addresses.get(0).getLocality();
                                // go to main page in 3 seconds
                                Timer timer = new Timer();
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(OpenScreenActivity.this, MainActivity.class);
                                        intent.putExtra("USER_CITY", city);
                                        startActivity(intent);
                                        // prevent user go back to this activity
                                        finish();
                                    }
                                }, 3000);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                // ask for permission if there is no existing permission
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(OpenScreenActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        // get user location and go to main page if permission is granted
                        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location != null) {
                                    Double latitude = location.getLatitude();
                                    Double longitude = location.getLongitude();
                                    try {
                                        TextView addressTv = findViewById(R.id.addressTv);
                                        Geocoder geocoder = new Geocoder(OpenScreenActivity.this, Locale.getDefault());
                                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                        city = addresses.get(0).getLocality();
                                        Timer timer = new Timer();
                                        timer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(OpenScreenActivity.this, MainActivity.class);
                                                intent.putExtra("USER_CITY", city);
                                                startActivity(intent);
                                                // prevent user go back to this activity
                                                finish();
                                            }
                                        }, 3000);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }else{
                    // directly enter main page if permission denied
                    Toast.makeText(this, "Permission denied, you can either enable location permission in setting OR set your location manually", Toast.LENGTH_LONG).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(OpenScreenActivity.this, MainActivity.class);
                            intent.putExtra("USER_CITY", city);
                            startActivity(intent);
                            // prevent user go back to this activity
                            finish();
                        }
                    }, 3000);
                }
                return;
            }
        }
    }
}