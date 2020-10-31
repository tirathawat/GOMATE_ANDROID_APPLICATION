package com.example.gomate.fragment.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gomate.R;
import com.example.gomate.fragment.home.adsbanner.BannerAdapter;
import com.example.gomate.fragment.home.adsbanner.BannerItem;
import com.example.gomate.fragment.home.typeactivity.ActivityAdapter;
import com.example.gomate.fragment.home.typeactivity.ActivityItem;
import com.example.gomate.fragment.register.RegisterOldFragment;
import com.example.gomate.fragment.rent.DescriptionFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private BannerAdapter bannerAdapter;
    private List<ActivityItem> activityItems;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityItems = new ArrayList<>();
        activityItems.add(new ActivityItem("Theater", R.drawable.ic_theater));
        activityItems.add(new ActivityItem("Dinner", R.drawable.ic_dinner));
        activityItems.add(new ActivityItem("Shopping", R.drawable.ic_shopping));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setupBannerItems();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.tv_banner);
//        viewPager2.setAdapter(bannerAdapter);

        RecyclerView activityRecycler = view.findViewById(R.id.recycler_activity);
        activityRecycler.setAdapter(new ActivityAdapter(this.getContext(), activityItems));
        activityRecycler.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        return view;

    }

    private void setupBannerItems() {
        List<BannerItem> bannerItems = new ArrayList<>();

        BannerItem itemFirst = new BannerItem();
        itemFirst.setTextShow("item 1");
        BannerItem itemSecond = new BannerItem();
        itemSecond.setTextShow("item 2");
        BannerItem itemThird = new BannerItem();
        itemThird.setTextShow("item 3");

        bannerItems.add(itemFirst);
        bannerItems.add(itemSecond);
        bannerItems.add(itemThird);

        BannerAdapter bannerAdapter = new BannerAdapter(bannerItems);

    }

}