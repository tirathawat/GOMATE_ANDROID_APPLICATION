package com.example.gomate.fragment.rent;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gomate.MainActivity;
import com.example.gomate.R;
import com.example.gomate.fragment.register.RegisterOldFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Objects;
import com.example.gomate.fragment.MapsFragment;

public class DescriptionFragment extends Fragment {

    private String selectActivity;
    private int hourBegin;
    private int minutesBegin;
    private int hourStop;
    private int minutesStop;

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
        final TextInputLayout dest = view.findViewById(R.id.description_location);
        final TextInputLayout description = view.findViewById(R.id.description_description);
        TextView activity = view.findViewById(R.id.description_activity);
        activity.setText(getArguments().getString("title"));
        view.findViewById(R.id.description_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        view.findViewById(R.id.description_next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_description = Objects.requireNonNull(description.getEditText()).getText().toString();
                String text_location = Objects.requireNonNull(dest.getEditText()).getText().toString();
                 if (TextUtils.isEmpty(text_description) || TextUtils.isEmpty(text_location))
                    Toast.makeText(getContext(), "All field is required", Toast.LENGTH_SHORT).show();
                else {
                    HashMap<String, String> data = new HashMap<>();
                    data.put("Description", text_description);
                    data.put("Location", text_location);
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.home_frame, GomateFragment.newInstance(data))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        final TextView etTimeBegin = view.findViewById(R.id.time_begin);
        etTimeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String timeBegin = String.valueOf(i) + ":";
                        if(i1 < 10){
                            timeBegin +="0";
                        }
                        timeBegin += String.valueOf(i1);
                        etTimeBegin.setText(timeBegin);
                    }
                },0,0,true);
                timePickerDialog.show();
            }
        });

        final TextView etTimeStop = view.findViewById(R.id.time_stop);
        etTimeStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String timeStop = String.valueOf(i) + ":";
                        if(i1 < 10){
                            timeStop +="0";
                        }
                        timeStop += String.valueOf(i1);
                        etTimeStop.setText(timeStop);
                    }
                },0,0,true);
                timePickerDialog.show();
            }
        });
        return view;
    }


}