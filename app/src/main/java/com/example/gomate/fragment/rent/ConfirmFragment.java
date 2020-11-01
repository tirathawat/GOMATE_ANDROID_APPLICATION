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
import com.example.gomate.fragment.home.ChatFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class ConfirmFragment extends Fragment {

    FirebaseUser firebaseUser;
    String sender, receiver;

    public ConfirmFragment() {}

    public static ConfirmFragment newInstance(Employee employee, HashMap<String, String> data) {
        ConfirmFragment fragment = new ConfirmFragment();
        Bundle args = new Bundle();
        args.putString("Description", data.get("Description"));
        args.putString("Location", data.get("Location"));
        args.putString("Id", employee.getId());
        args.putString("Name", employee.getName());
        args.putString("ImageURL", employee.getImageURL());
        args.putString("Time",data.get("Time"));
        args.putString("TotalTime",data.get("TotalTime"));
        Log.d("Test",data.get("TotalTime"));
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
        TextView time = view.findViewById(R.id.text_time);
        receiver = getArguments().getString("Id");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        sender = firebaseUser.getUid();
        time.setText(getArguments().getString("Time"));
        String totalTime_string = getArguments().getString("TotalTime");
        Log.d("Test1234",totalTime_string);
//        totalTime_string = totalTime_string.split(".")[0];
        double totalTime = Double.parseDouble(totalTime_string);

        final double total_gomate = totalTime*5;
        final double total_promotion = total_gomate*0.15;
        final double transport_price = 100;
        final double total_price = total_gomate+total_promotion+transport_price;

        TextView price_gomate = view.findViewById(R.id.text_price_gomate);
        price_gomate.setText(String.valueOf(total_gomate));
        TextView price_promotion = view.findViewById(R.id.text_price_promotion);
        price_promotion.setText(String.valueOf(total_promotion));
        TextView price_transportation = view.findViewById(R.id.text_price_transport);
        price_transportation.setText(String.valueOf(transport_price));
        TextView text_price_total = view.findViewById(R.id.text_price_total);
        text_price_total.setText(String.valueOf(total_price));


        TextView gomate = view.findViewById(R.id.tv_name_gomate);
        gomate.setText(getArguments().getString("Name"));

        view.findViewById(R.id.button_maps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(receiver, sender, "Hello, I'm your gomate! How are you doing?");
                getFragmentManager().beginTransaction().replace(R.id.home_frame,new MapsFragment(getArguments().getString("Name"))).commit();

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

    private void sendMessage (String sender, String receiver, String message) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("timestamp", "10-10-10");
        databaseReference.child("Chats").push().setValue(hashMap);
    }
}
