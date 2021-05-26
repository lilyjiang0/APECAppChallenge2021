package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.ForecastData;
import com.example.foottraffic.pojo.WeekForecastData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttractionDetailActivity extends AppCompatActivity {
    private APIInterfaceActivity apiInterface;
    private String name;
    private String address;
    private String venueID;
    private String image;
    private Integer busyness;
    private String privateApiKey = "pri_f9cc4722a147468a85e2696073b71b4f";
    private String publicApiKey = "pub_e6af9cfdcffc4b5da127d98fec9a9b89";
    ArrayList<Integer> colors = new ArrayList<Integer>();
    List<Integer> busyHrsList = new ArrayList<>();
    List<Integer> quietHrsList = new ArrayList<>();
    private int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);

        name = getIntent().getStringExtra("VENUE_NAME");
        address = getIntent().getStringExtra("VENUE_ADDRESS");
        venueID = getIntent().getStringExtra("VENUE_ID");
        image = getIntent().getStringExtra("VENUE_IMAGE");
        busyness = getIntent().getIntExtra("VENUE_BUSY", 0);


        TextView nameTv = findViewById(R.id.nameTv);
        TextView busyTv = findViewById(R.id.busyTv);
        ImageView imageIv = findViewById(R.id.attractionIv);
        nameTv.setText(name);
        if (busyness != -100) {
            if (busyness < 40) {
                busyTv.setText("Not Busy");
            } else if (busyness < 70) {
                busyTv.setText("A little Busy");
            } else {
                busyTv.setText("Busy");
            }
        } else {
            busyTv.setText("");
        }
        Glide.with(this).load(image).into(imageIv);


        getHourDataFromApi();
        if (venueID != null) {
            getWeekDataFromApi();
        }
    }

    private void getHourDataFromApi() {
        // call api to get hour data
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        Call<ForecastData> call = apiInterface.getForecast(privateApiKey, name, address);
        call.enqueue(new Callback<ForecastData>() {
            @Override
            public void onResponse(Call<ForecastData> call, Response<ForecastData> response) {
                Log.d("---------", String.valueOf(response.code()));
                // check api status
                if (response.code() == 200) {
                    BarChart hourBc = findViewById(R.id.hourBc);
                    ArrayList<BarEntry> busy = new ArrayList<>();
                    for (int i = 0; i < response.body().getAnalysis().get(0).getDayRaw().size(); i++) {
                        if (response.body().getAnalysis().get(0).getDayRaw().get(i) != 0) {
                            if (i + 6 <= 24) {
                                busy.add(new BarEntry( i + 6, response.body().getAnalysis().get(0).getDayRaw().get(i)));
                            } else {
                                busy.add(new BarEntry( i - 19, response.body().getAnalysis().get(0).getDayRaw().get(i)));
                            }
                        }
                    }

                    int start = (int) busy.get(0).getX();
                    int end = (int) busy.get(busy.size() - 1).getX();
                    if (end < start) {
                        end += 25;
                    }

                    busyHrsList = response.body().getAnalysis().get(0).getBusyHours();
                    quietHrsList = response.body().getAnalysis().get(0).getQuietHours();

                    for (int i = start; i < end + 1; i++) {
                        int size = quietHrsList.size() + busyHrsList.size();
                        for (int k = 0; k < size; k++) {
                            if (k < busyHrsList.size() && busyHrsList != null && !busyHrsList.isEmpty() && busyHrsList.get(k) == i) {
                                colors.add(Color.rgb(245, 12,10));
                            } else if (k < quietHrsList.size() && quietHrsList != null && !quietHrsList.isEmpty() && quietHrsList.get(k) == i) {
                                colors.add(Color.rgb(56,199,112));
                            }
                        }
                        count++;
                        if (colors.size() != count) {
                            colors.add(Color.rgb(52, 232, 235));
                        }
                    }


                    BarDataSet barDataSet = new BarDataSet(busy, "Busyness%");
                    barDataSet.setColors(colors);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(10f);
                    barDataSet.setDrawValues(false);
                    BarData barData = new BarData(barDataSet);
                    hourBc.setFitBars(true);
                    hourBc.setData(barData);
                    hourBc.getDescription().setEnabled(false);
                    hourBc.getAxisRight().setDrawGridLines(false);
                    hourBc.getAxisLeft().setDrawGridLines(false);
                    hourBc.getXAxis().setDrawGridLines(false);
                    YAxis rightYAxis = hourBc.getAxisRight();
                    rightYAxis.setEnabled(false);
                    hourBc.animateY(2000);
                }
            }

            @Override
            public void onFailure(Call<ForecastData> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Api call failed");
            }
        });
    }

    private void getWeekDataFromApi() {
        // call api to get hour data
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        Call<WeekForecastData> call = apiInterface.getWeekForecast(publicApiKey, venueID);
        call.enqueue(new Callback<WeekForecastData>() {
            @Override
            public void onResponse(Call<WeekForecastData> call, Response<WeekForecastData> response) {
                // check api status
                if (response.code() == 200) {
                    List<WeekForecastData.WeekOverview> myList = new ArrayList<>();

                    BarChart weekBc = findViewById(R.id.weekBc);
                    ArrayList<BarEntry> busy = new ArrayList<>();
                    for (int i = 0; i < response.body().getAnalysis().getWeekOverview().size(); i++) {
                        busy.add(new BarEntry( i + 1, response.body().getAnalysis().getWeekOverview().get(i).getDayMean()));
                    }
//
                    BarDataSet barDataSet = new BarDataSet(busy, "Busyness%");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(10f);
                    barDataSet.setDrawValues(false);
                    BarData barData = new BarData(barDataSet);
                    weekBc.setFitBars(true);
                    weekBc.setData(barData);
                    weekBc.getAxisRight().setDrawGridLines(false);
                    weekBc.getAxisLeft().setDrawGridLines(false);
                    weekBc.getXAxis().setDrawGridLines(false);
                    weekBc.getDescription().setEnabled(false);
                    YAxis rightYAxis = weekBc.getAxisRight();
                    rightYAxis.setEnabled(false);
                    weekBc.animateY(2000);
                }
            }

            @Override
            public void onFailure(Call<WeekForecastData> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Api call failed");
            }
        });
    }
}