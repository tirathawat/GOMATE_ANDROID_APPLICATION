package com.example.gomate.fragment.rent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gomate.Model.Employee;
import com.example.gomate.R;
import com.example.gomate.fragment.MapsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Objects;

public class ConfirmFragment extends Fragment {

    public ConfirmFragment() {}

    public static ConfirmFragment newInstance(Employee employee, HashMap<String, String> data) {
        ConfirmFragment fragment = new ConfirmFragment();
        Bundle args = new Bundle();
        args.putString("Description", data.get("Description"));
        args.putString("Location", data.get("Location"));
        args.putString("Id", employee.getId());
        args.putString("Name", employee.getName());
        args.putString("ImageURL", employee.getImageURL());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        TextView location = view.findViewById(R.id.text_location);
        TextView description = view.findViewById(R.id.text_description);
        location.setText(Objects.requireNonNull(getArguments()).getString("Location"));
        description.setText(Objects.requireNonNull(getArguments()).getString("Description"));

        TextView gomate = view.findViewById(R.id.tv_name_gomate);
        gomate.setText(getArguments().getString("Name"));

        view.findViewById(R.id.button_maps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","gan");
                getFragmentManager().beginTransaction().replace(R.id.home_frame,new MapsFragment()).commit();
            }
        });



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
