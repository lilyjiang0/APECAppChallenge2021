package com.example.foottraffic;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class generateAdapter extends RecyclerView.Adapter<generateAdapter.ViewHolder> {

    private ArrayList<String> venueNameParams = new ArrayList<>();
    private ArrayList<String> venueAddressParams = new ArrayList<>();
    private ArrayList<String> venueOpenParams = new ArrayList<>();
    private ArrayList<String> venueClosedParams = new ArrayList<>();
    private List<List<Integer>> quietHourParams = new ArrayList<List<Integer>>();
    private Integer dayOfWeek;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView venueName;
        TextView quietHours;
        TextView day;
        TextView tradingHour;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.venueName = itemView.findViewById(R.id.textView18);
            this.quietHours = itemView.findViewById(R.id.quietHoursTv);
            this.day = itemView.findViewById(R.id.textView19);
            this.tradingHour = itemView.findViewById(R.id.textView21);
        }
    }

    public generateAdapter(ArrayList<String> venueNameParams, ArrayList<String> venueOpenParams, ArrayList<String> venueClosedParams, List<List<Integer>> quietHourParams, Integer dayOfWeek, Context context) {
        this.venueNameParams = venueNameParams;
        this.venueOpenParams = venueOpenParams;
        this.venueClosedParams = venueClosedParams;
        this.quietHourParams = quietHourParams;
        this.dayOfWeek = dayOfWeek;
        this.context = context;
    }

    /**
     *
     * @param viewGroup
     * @param viewType
     * @return
     */

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.generate_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.venueName.setText(venueNameParams.get(position));
        if (quietHourParams.get(position).size() == 0) {
            holder.quietHours.setText("--");
        } else {
            quietHourParams.get(position);
            holder.quietHours.setText(Arrays.toString(quietHourParams.get(position).toArray()));
        }
        String[] strDays = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        holder.day.setText(strDays[dayOfWeek]);
        System.out.println("res is: " + venueOpenParams.get(position).toString());
        if (venueOpenParams.get(position).isEmpty() || venueClosedParams.get(position).equals("") || venueClosedParams.get(position) == null) {
            holder.tradingHour.setText("It's closed today.");
        } else {
            Integer closedPM = Integer.valueOf(venueClosedParams.get(position)) - 12;
            holder.tradingHour.setText("Trading hours: " + venueOpenParams.get(position) + "am - " + closedPM + "pm");
        }
    }

    @Override
    public int getItemCount() {
        return venueNameParams.size();
    }
}
