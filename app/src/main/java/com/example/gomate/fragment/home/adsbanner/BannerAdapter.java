package com.example.gomate.fragment.home.adsbanner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomate.R;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder>{

    private List<BannerItem> bannerItems;

    public BannerAdapter(List<BannerItem> bannerItems){
        this.bannerItems = bannerItems;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BannerViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.banner_frame, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Log.e("BannerFragment", "onCreateViewHolder");

        holder.setItemData(bannerItems.get(position));
    }

    @Override
    public int getItemCount() {
        return bannerItems.size();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        BannerViewHolder(@NonNull View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.tv_banner);

        }

        void setItemData(BannerItem bannerItem){
            textView.setText(bannerItem.getTextShow());
        }
    }
}
