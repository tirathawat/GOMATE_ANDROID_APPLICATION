package com.example.gomate.fragment.rent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gomate.Adapter.GomateAdapter;
import com.example.gomate.Model.Employee;
import com.example.gomate.R;
import com.example.gomate.fragment.register.RegisterNameFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GomateFragment extends Fragment {

    private List<Employee> employees;
    ImageView[] profile = new ImageView[4];
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    public GomateFragment() {}


    static GomateFragment newInstance(HashMap<String, String> data) {
        GomateFragment fragment = new GomateFragment();
        Bundle args = new Bundle();
        args.putString("Description", data.get("Description"));
        args.putString("Location", data.get("Location"));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employees = new ArrayList<>();
        readUser();
        for(int i = 0;i < employees.size();i++){
            Log.e("GomateFragment",employees.get(i).getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final HashMap<String, String> data = new HashMap<>();
        data.put("Location", Objects.requireNonNull(getArguments()).getString("Location"));
        data.put("Description", Objects.requireNonNull(getArguments()).getString("Description"));
        View view = inflater.inflate(R.layout.fragment_gomate, container, false);
        TextView text_location = view.findViewById(R.id.text_location);
        text_location.setText(data.get("Location"));

//        profile[0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.home_frame, ConfirmFragment.newInstance(employees.get(0), data))
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//        profile[1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.home_frame, ConfirmFragment.newInstance(employees.get(1), data))
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//        profile[2].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.home_frame, ConfirmFragment.newInstance(employees.get(2), data))
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//        profile[3].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.home_frame, ConfirmFragment.newInstance(employees.get(3), data))
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
        RecyclerView gomateRecycler = view.findViewById(R.id.recycler_gomate);
        gomateRecycler.setAdapter(new GomateAdapter(this.getContext(), employees));
        gomateRecycler.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void readUser() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employees.clear();
                for (DataSnapshot s : snapshot.getChildren()) {
                    Employee employee = s.getValue(Employee.class);
                    employees.add(employee);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
