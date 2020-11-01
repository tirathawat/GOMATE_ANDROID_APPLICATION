package com.example.gomate.fragment.rent;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GomateFragment extends Fragment {

    private String _Time;
    private List<Employee> employees;
    ImageView[] profile = new ImageView[4];
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    RecyclerView gomateRecycler;
    HashMap<String,String> data;


    public GomateFragment() {}


    static GomateFragment newInstance(HashMap<String, String> data) {
        GomateFragment fragment = new GomateFragment();
        Bundle args = new Bundle();
        args.putString("Description", data.get("Description"));
        args.putString("Location", data.get("Location"));
        args.putString("TimeBegin", data.get("TimeBegin"));
        args.putString("TimeStop", data.get("TimeStop"));
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employees = new ArrayList<>();
        for(int i = 0;i < employees.size();i++){
            Log.e("GomateFragment",employees.get(i).getName());
        }
        readUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = new HashMap<>();
        data.put("Location", Objects.requireNonNull(getArguments()).getString("Location"));
        data.put("Description", Objects.requireNonNull(getArguments()).getString("Description"));
        data.put("TimeBegin", Objects.requireNonNull(getArguments()).getString("TimeBegin"));
        data.put("TimeStop", Objects.requireNonNull(getArguments()).getString("TimeStop"));
        View view = inflater.inflate(R.layout.fragment_gomate, container, false);
        TextView text_location = view.findViewById(R.id.text_location);
        TextView text_time = view.findViewById(R.id.text_time);
        Log.d("Test",data.get("TimeBegin"));
        text_location.setText(data.get("Location"));
        gomateRecycler = view.findViewById(R.id.recycler_gomate);
        String[] begin = data.get("TimeBegin").split(":");
        double be_hr =  Double.parseDouble(begin[0]);
        double be_mn =  Double.parseDouble(begin[1]);
        String[] end = data.get("TimeStop").split(":");
        double end_hr =  Double.parseDouble(end[0]);
        double end_mn =  Double.parseDouble(end[1]);
        String a="";
        if(be_hr<10&&be_mn<10){
            a = "0"+begin[0]+":0"+begin[1];
        }
        else if(be_hr<10){
            a = "0"+begin[0]+":"+begin[1];
        }
        else if(be_mn<10){
            a = begin[0]+":0"+begin[1];
        }
        else {
            a = begin[0]+":"+begin[1];
        }

        String b="";
        if(end_hr<10&&end_mn<10){
            b = "0"+end[0]+":0"+end[1];
        }
        else if(end_hr<10){
            b = "0"+end[0]+":"+end[1];
        }
        else if(end_mn<10){
            b = end[0]+":0"+end[1];
        }
        else {
            b = end[0]+":"+end[1];
        }
        text_time.setText(a + "  - " + b);
        _Time = a + "  - " + b;
        data.put("Time",_Time);
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
        gomateRecycler.setAdapter(new GomateAdapter(this.getContext(), employees, data));
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
        Log.d("Test","gan");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employees.clear();
                for (DataSnapshot s : snapshot.getChildren()) {
                    Log.d("Test",s.toString());
                    if(s.child("isEmployee").getValue().toString().equals("true")){
                        Employee emp = new Employee(s.getKey().toString(),s.child("Name").getValue().toString(),"default");
                        employees.add(emp);

                    }
                }

                gomateRecycler.setAdapter(new GomateAdapter(getContext(), employees, data));
                gomateRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
