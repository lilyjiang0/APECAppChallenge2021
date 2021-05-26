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
import org.w3c.dom.Text;

import java.util.ArrayList;

public class browseAllAdapter extends RecyclerView.Adapter<browseAllAdapter.ViewHolder>{
    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<Integer> mBusy = new ArrayList<>();
    private ArrayList<String> mAddress = new ArrayList<>();
    private ArrayList<String> mID = new ArrayList<>();
    private Context mContext;

    public browseAllAdapter(ArrayList<String> mName, ArrayList<String> mImage, ArrayList<Integer> mBusy, ArrayList<String> mAddress, ArrayList<String> mID, Context mContext) {
        this.mName = mName;
        this.mImage = mImage;
        this.mBusy = mBusy;
        this.mAddress = mAddress;
        this.mID = mID;
        this.mContext = mContext;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide
                .with(mContext)
                .load(mImage.get(position))
                .apply(new RequestOptions().override(600, 200))
                .into(holder.image);
        holder.name.setText(mName.get(position));
        if (mBusy.get(position) != 0 && mBusy.get(position) != -100) {
            holder.busy.setText(mBusy.get(position) + "");
        } else {
            holder.busy.setText("");
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, mName.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, AttractionDetailActivity.class);
                intent.putExtra("VENUE_NAME", mName.get(position));
                intent.putExtra("VENUE_ADDRESS", mAddress.get(position));
                intent.putExtra("VENUE_ID", mID.get(position));
                intent.putExtra("VENUE_IMAGE", mImage.get(position));
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
        TextView busy;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.browseAllIv);
            this.name = itemView.findViewById(R.id.browseAllName);
            this.busy = itemView.findViewById(R.id.browseAllBusy);
        }
    }
}
