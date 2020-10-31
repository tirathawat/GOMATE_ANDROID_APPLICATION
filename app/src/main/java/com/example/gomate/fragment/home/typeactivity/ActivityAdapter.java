package com.example.gomate.fragment.home.typeactivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomate.R;
import com.example.gomate.fragment.home.HomeFragment;
import com.example.gomate.fragment.rent.DescriptionFragment;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityItemsHolder> {

    private Context mContext;
    private List<ActivityItem> mData;

    public ActivityAdapter(Context mContext, List<ActivityItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ActivityItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_activity,parent,false);
        return new ActivityItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityItemsHolder holder, final int position) {
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.imageActivity.setImageResource(mData.get(position).getImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("title",mData.get(position).getTitle());
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                DescriptionFragment descriptionFragment = new DescriptionFragment(mData.get(position).getTitle());
                descriptionFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,descriptionFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ActivityItemsHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView imageActivity;
        CardView cardView;

        public ActivityItemsHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_card_activity);
            imageActivity = itemView.findViewById(R.id.iv_card_activity);
            cardView = itemView.findViewById(R.id.card_activity);
        }
    }
}
