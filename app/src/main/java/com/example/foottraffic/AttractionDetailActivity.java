package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.pojo.Attractions;
import com.example.foottraffic.pojo.ForecastData;
import com.example.foottraffic.pojo.HourAnalysi;
import com.example.foottraffic.pojo.WeekForecastData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttractionDetailActivity extends AppCompatActivity implements OnChartValueSelectedListener {
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
    TextView openingTime;
    TextView bCIntensityTxt, bcHour, infoBox, busynessDesc, intensityDesc;
    RatingBar bcBusyness, bcIntensity;
    ArrayList<HourAnalysi> hourAnalysis = new ArrayList<>();




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
        TextView addressTv = findViewById(R.id.aAddressTv);
        ImageView imageIv = findViewById(R.id.attractionIv);
        openingTime = findViewById(R.id.openingHrsTv);

        nameTv.setText(name);
        openingTime.setText("-");
        addressTv.setText(address);
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
                int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
                if (dayOfWeek == 0) {
                    dayOfWeek = 7;
                }
                // check api status
                if (response.code() == 200) {
                    BarChart hourBc = findViewById(R.id.hourBc);
                    ArrayList<BarEntry> busy = new ArrayList<>();

                    String openingHrs = String.valueOf(response.body().getAnalysis().get(dayOfWeek).getDayInfo().getVenueOpen()) + "am-" + String.valueOf(response.body().getAnalysis().get(dayOfWeek).getDayInfo().getVenueClosed() + "pm");
                    if (response.body().getAnalysis().get(dayOfWeek).getDayInfo().getVenueOpen() != 0 && response.body().getAnalysis().get(dayOfWeek).getDayInfo().getVenueClosed() != 0) {
                        openingTime.setText(openingHrs);
                    } else {
                        openingTime.setText("-");
                    }


                    for (int i = 0; i < response.body().getAnalysis().get(dayOfWeek).getDayRaw().size(); i++) {
                        hourAnalysis.add(new HourAnalysi(response.body().getAnalysis().get(dayOfWeek).getHourAnalysis().get(i).getHour(), response.body().getAnalysis().get(dayOfWeek).getHourAnalysis().get(i).getIntensityNr(), response.body().getAnalysis().get(dayOfWeek).getHourAnalysis().get(i).getIntensityTxt()));
                        if (response.body().getAnalysis().get(dayOfWeek).getDayRaw().get(i) != 0) {
                            if (i + 6 <= 24) {
                                busy.add(new BarEntry( i + 6, response.body().getAnalysis().get(dayOfWeek).getDayRaw().get(i)));
                            } else {
                                busy.add(new BarEntry( i - 19, response.body().getAnalysis().get(dayOfWeek).getDayRaw().get(i)));
                            }
                        }
                    }

                    int start = (int) busy.get(0).getX();
                    int end = (int) busy.get(busy.size() - 1).getX();
                    if (end < start) {
                        end += 25;
                    }

                    busyHrsList = response.body().getAnalysis().get(dayOfWeek).getBusyHours();
                    quietHrsList = response.body().getAnalysis().get(dayOfWeek).getQuietHours();

                    for (int i = start; i < end + 1; i++) {
                        int size = quietHrsList.size() + busyHrsList.size();
                        for (int k = 0; k < size; k++) {
                            if (k < busyHrsList.size() && busyHrsList != null && !busyHrsList.isEmpty() && busyHrsList.get(k) == i) {
                                colors.add(Color.rgb(234, 91, 72));
                            } else if (k < quietHrsList.size() && quietHrsList != null && !quietHrsList.isEmpty() && quietHrsList.get(k) == i) {
                                colors.add(Color.rgb(0, 165, 76));
                            }
                        }
                        count++;
                        if (colors.size() != count) {
                            colors.add(Color.rgb(0, 142, 255));
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
                    hourBc.getLegend().setEnabled(false);
                    XAxis xAxis = hourBc.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


                    YAxis rightYAxis = hourBc.getAxisRight();
                    rightYAxis.setEnabled(false);
                    hourBc.animateY(2000);
                    hourBc.setOnChartValueSelectedListener(AttractionDetailActivity.this);
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
                    weekBc.getLegend().setEnabled(false);
                    XAxis xAxis = weekBc.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
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

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (bcBusyness != null) {
            ((ViewGroup) bcBusyness.getParent()).removeView(bcBusyness);
            ((ViewGroup) bCIntensityTxt.getParent()).removeView(bCIntensityTxt);
            ((ViewGroup) bcHour.getParent()).removeView(bcHour);
            ((ViewGroup) bcIntensity.getParent()).removeView(bcIntensity);
            ((ViewGroup) infoBox.getParent()).removeView(infoBox);
            ((ViewGroup) busynessDesc.getParent()).removeView(busynessDesc);
            ((ViewGroup) intensityDesc.getParent()).removeView(intensityDesc);
        }

        ConstraintLayout parentLayout = (ConstraintLayout)findViewById(R.id.detailCL);
        BarChart hourBc = findViewById(R.id.hourBc);
        TextView weekOverviewTv = findViewById(R.id.weekOverviewTv);
        ConstraintSet set = new ConstraintSet();

        bcBusyness = new RatingBar(this, null);
        bCIntensityTxt = new TextView(this);
        bcHour = new TextView(this);
        infoBox = new TextView(this);
        bcIntensity = new RatingBar(this, null);
        busynessDesc = new TextView(this);
        intensityDesc = new TextView(this);
        // set view id, else getId() returns -1
        bcBusyness.setId(View.generateViewId());
        bCIntensityTxt.setId(View.generateViewId());
        bcHour.setId(View.generateViewId());
        bcIntensity.setId(View.generateViewId());
        infoBox.setId(View.generateViewId());
        busynessDesc.setId(View.generateViewId());
        intensityDesc.setId(View.generateViewId());

        busynessDesc.setText("Busyness");
        intensityDesc.setText("Intensity");
                bcBusyness.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));
        bcIntensity.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));
        bcBusyness.setIsIndicator(true);
        bcIntensity.setIsIndicator(true);
        bcBusyness.setStepSize((float) 0.5);
        bcIntensity.setStepSize((float) 0.5);
        bcBusyness.setProgressDrawableTiled(ContextCompat.getDrawable(this, R.drawable.custom_rating_bar));
        bcIntensity.setProgressDrawableTiled(ContextCompat.getDrawable(this, R.drawable.custom_rating_bar));
        bcBusyness.setNumStars(5);
        bcIntensity.setNumStars(5);
        bcBusyness.setScaleX((float) 0.4);
        bcBusyness.setScaleY((float) 0.4);
        bcIntensity.setScaleX((float) 0.4);
        bcIntensity.setScaleY((float) 0.4);


        for (int i = 0; i < hourAnalysis.size(); i++) {
            if(hourAnalysis.get(i).getHour() == (int)e.getX()) {
                bcBusyness.setRating(e.getY() / 100 * 5);
                bCIntensityTxt.setText(String.valueOf(hourAnalysis.get(i).getIntensityTxt()));
                bcHour.setText(String.valueOf(hourAnalysis.get(i).getHour()+ ":00"));
                bcIntensity.setRating(hourAnalysis.get(i).getIntensityNr() + 3);
                Log.d("+++++++++++", String.valueOf(hourAnalysis.get(i).getHour()));
                Log.d("+++++++++++", String.valueOf(hourAnalysis.get(i).getIntensityNr()));
                Log.d("+++++++++++", String.valueOf(hourAnalysis.get(i).getIntensityTxt()));
            }
        }

        infoBox.setBackgroundColor(Color.LTGRAY);
        infoBox.setHeight(220);
        infoBox.setWidth(700);

        parentLayout.addView(infoBox, 0);
        parentLayout.addView(bcBusyness, 1);
        parentLayout.addView(bCIntensityTxt, 2);
        parentLayout.addView(bcHour, 3);
        parentLayout.addView(bcIntensity, 4);
        parentLayout.addView(busynessDesc, 4);
        parentLayout.addView(intensityDesc, 4);

        set.clone(parentLayout);
        // connect start and end point of views, in this case top of child to top of parent.
        set.connect(infoBox.getId(), ConstraintSet.TOP, hourBc.getId(), ConstraintSet.BOTTOM, 5);
        set.connect(infoBox.getId(), ConstraintSet.LEFT, hourBc.getId(), ConstraintSet.LEFT, 0);
        set.connect(infoBox.getId(), ConstraintSet.RIGHT, hourBc.getId(), ConstraintSet.RIGHT, 0);
        set.connect(bCIntensityTxt.getId(), ConstraintSet.TOP, infoBox.getId(), ConstraintSet.TOP, 2);
        set.connect(bCIntensityTxt.getId(), ConstraintSet.LEFT, infoBox.getId(), ConstraintSet.LEFT, 10);
        set.connect(bcHour.getId(), ConstraintSet.LEFT, bCIntensityTxt.getId(), ConstraintSet.RIGHT, 8);
        set.connect(bcHour.getId(), ConstraintSet.TOP, bCIntensityTxt.getId(), ConstraintSet.TOP, 0);
        set.connect(intensityDesc.getId(), ConstraintSet.TOP, bCIntensityTxt.getId(), ConstraintSet.BOTTOM, 6);
        set.connect(intensityDesc.getId(), ConstraintSet.LEFT, bCIntensityTxt.getId(), ConstraintSet.LEFT, 0);
        set.connect(bcIntensity.getId(), ConstraintSet.TOP, intensityDesc.getId(), ConstraintSet.TOP, 0);
        set.connect(bcIntensity.getId(), ConstraintSet.LEFT, intensityDesc.getId(), ConstraintSet.RIGHT, 0);
        set.connect(bcIntensity.getId(), ConstraintSet.BOTTOM, intensityDesc.getId(), ConstraintSet.BOTTOM, 0);
        set.connect(busynessDesc.getId(), ConstraintSet.TOP, intensityDesc.getId(), ConstraintSet.BOTTOM, 6);
        set.connect(busynessDesc.getId(), ConstraintSet.LEFT, intensityDesc.getId(), ConstraintSet.LEFT, 0);
        set.connect(bcBusyness.getId(), ConstraintSet.TOP, busynessDesc.getId(), ConstraintSet.TOP, 0);
        set.connect(bcBusyness.getId(), ConstraintSet.LEFT, busynessDesc.getId(), ConstraintSet.RIGHT, 0);
        set.connect(bcBusyness.getId(), ConstraintSet.BOTTOM, busynessDesc.getId(), ConstraintSet.BOTTOM, 0);
        set.connect(weekOverviewTv.getId(), ConstraintSet.TOP, infoBox.getId(), ConstraintSet.BOTTOM, 16);

        set.applyTo(parentLayout);
    }

    @Override
    public void onNothingSelected() {

    }
}