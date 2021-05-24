package com.example.foottraffic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.foottraffic.api.APIClientActivity;
import com.example.foottraffic.pojo.ForecastData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateTripActivity extends AppCompatActivity {

    private APIInterfaceActivity apiInterface;
    private String apiKey = "pri_f9cc4722a147468a85e2696073b71b4f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_trip);

        // destination addresses
//        TextView
    }

    private void getHourDataFromApi(String name, String address) {
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