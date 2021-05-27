package com.example.foottraffic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder>{
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mAddress = new ArrayList<>();
    private ArrayList<Double> mKm = new ArrayList<>();
    private ArrayList<String> mID = new ArrayList<>();
    private ArrayList<Integer> mBusy = new ArrayList<>();
    private ArrayList<String> image = new ArrayList<>();
    private Context mContext;

    public DiscoverAdapter(ArrayList<String> mName, ArrayList<String> mImage, ArrayList<String> mAddress, ArrayList<Double> mKm, ArrayList<String> mID, ArrayList<Integer> mBusy, ArrayList<String> image, Context mContext) {
        this.mName = mName;
        this.mImage = mImage;
        this.mAddress = mAddress;
        this.mKm = mKm;
        this.mID = mID;
        this.mBusy = mBusy;
        this.image = image;
        this.mContext = mContext;
    }

    @NonNull
    @NotNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_list, parent, false);
        return new DiscoverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DiscoverAdapter.ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide
                .with(mContext)
                .load(mImage.get(position))
                .centerCrop()
                .apply(new RequestOptions().override(600, 200))
                .into(holder.image);
        holder.name.setText(mName.get(position));
        holder.km.setText(mKm.get(position) + " km");
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, mName.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, AttractionDetailActivity.class);
                intent.putExtra("VENUE_NAME", mName.get(position));
                intent.putExtra("VENUE_ADDRESS", mAddress.get(position));
                intent.putExtra("VENUE_ID", mID.get(position));
                intent.putExtra("VENUE_IMAGE", image.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView km;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.discoverIv);
            this.name = itemView.findViewById(R.id.discoverNameTv);
            this.km = itemView.findViewById(R.id.discoverKmTv);

        }
    }
}
