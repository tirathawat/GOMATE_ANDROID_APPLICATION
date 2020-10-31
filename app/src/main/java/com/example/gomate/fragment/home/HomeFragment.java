package com.example.gomate.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gomate.R;
import com.example.gomate.fragment.home.adsbanner.BannerAdapter;
import com.example.gomate.fragment.home.adsbanner.BannerItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private BannerAdapter bannerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setupBannerItems();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.tv_banner);
//        viewPager2.setAdapter(bannerAdapter);
        return  view;

    }

    private void setupBannerItems(){
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