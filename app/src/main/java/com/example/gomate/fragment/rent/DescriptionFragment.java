package com.example.gomate.fragment.rent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gomate.R;
import com.example.gomate.fragment.register.RegisterOldFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Objects;

public class DescriptionFragment extends Fragment {

    private TextInputLayout dest;
    private TextInputLayout description;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionFragment newInstance(String param1, String param2) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        final TextInputLayout dest = view.findViewById(R.id.description_location);
        final TextInputLayout description = view.findViewById(R.id.description_description);
        view.findViewById(R.id.description_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        view.findViewById(R.id.description_next_btn).setOnClickListener(new View.OnClickListener(){
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
        return view;
    }

}