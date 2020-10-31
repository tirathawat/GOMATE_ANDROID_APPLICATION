package com.example.gomate.fragment.rent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gomate.Model.Employee;
import com.example.gomate.R;

import java.util.HashMap;
import java.util.Objects;

public class ConfirmFragment extends Fragment {

    public ConfirmFragment() {}

    static ConfirmFragment newInstance(Employee employee, HashMap<String, String> data) {
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
        return inflater.inflate(R.layout.fragment_confirm, container, false);
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
