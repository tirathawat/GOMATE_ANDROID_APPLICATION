package com.example.gomate.fragment.rent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gomate.R;
import com.example.gomate.fragment.MapsFragment;

public class DescriptionFragment extends Fragment {

    private String selectActivity;

    public DescriptionFragment(String title) {
        this.selectActivity = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        view.findViewById(R.id.button_maps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MapsFragment mapsFragment = new MapsFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.home_frame,mapsFragment).commit();
                getFragmentManager().beginTransaction().replace(R.id.home_frame,new MapsFragment()).commit();
            }
        });
        return view;
    }
}