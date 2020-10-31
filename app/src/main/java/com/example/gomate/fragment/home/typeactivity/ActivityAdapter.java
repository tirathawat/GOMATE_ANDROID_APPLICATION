package com.example.gomate.fragment.home.typeactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomate.R;

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
    public void onBindViewHolder(@NonNull ActivityItemsHolder holder, int position) {
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.imageActivity.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ActivityItemsHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView imageActivity;

        public ActivityItemsHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_card_activity);
            imageActivity = itemView.findViewById(R.id.iv_card_activity);
        }
    }
}
