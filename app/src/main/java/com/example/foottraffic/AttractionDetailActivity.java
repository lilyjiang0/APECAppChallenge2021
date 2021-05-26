package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.ForecastData;
import com.github.mikephil.charting.charts.BarChart;
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
    private String apiKey = "pri_f9cc4722a147468a85e2696073b71b4f";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);

        name = getIntent().getStringExtra("VENUE_NAME");
        address = getIntent().getStringExtra("VENUE_ADDRESS");
        TextView nameTv = findViewById(R.id.nameTv);
        nameTv.setText(name);
        getHourDataFromApi();
//
//        BarChart hourBc = findViewById(R.id.hourBc);
//        ArrayList<BarEntry> visitors = new ArrayList<>();
//        visitors.add(new BarEntry(1, 60));
//        visitors.add(new BarEntry(2, 40));
//        visitors.add(new BarEntry(3, 50));
//        visitors.add(new BarEntry(4, 60));
//        visitors.add(new BarEntry(5, 90));
//        visitors.add(new BarEntry(6, 20));
//        visitors.add(new BarEntry(7, 30));
//
//        BarDataSet barDataSet = new BarDataSet(visitors, "Vistors");
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
//        BarData barData = new BarData(barDataSet);
//        hourBc.setFitBars(true);
//        hourBc.setData(barData);
//        hourBc.getDescription().setText("TEST");
//        hourBc.animateY(2000);
    }

    private void getHourDataFromApi() {
        // call api to get hour data
        apiInterface = APIClientActivity.getClient().create(APIInterfaceActivity.class);
        Call<ForecastData> call = apiInterface.getForecast(apiKey, name, address);
        call.enqueue(new Callback<ForecastData>() {
            @Override
            public void onResponse(Call<ForecastData> call, Response<ForecastData> response) {
                Log.d("---------", String.valueOf(response.code()));
                // check api status
                if (response.code() == 200) {
                    List<Integer> myList = new ArrayList<>();
                    myList = response.body().getAnalysis().get(0).getDayRaw();
                    System.out.println(Arrays.toString(myList.toArray()));

                }
            }

            @Override
            public void onFailure(Call<ForecastData> call, Throwable t) {
                call.cancel();
                Log.d("ERROR", "Api call failed");
            }
        });
    }
}