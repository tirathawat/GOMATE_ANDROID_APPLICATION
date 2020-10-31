package com.example.gomate.fragment.register;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.gomate.MainActivity;
import com.example.gomate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterInterestFragment extends Fragment {

    private static String email;
    private static String password;
    private static String name;
    private static String old;
    private static String phone;

    private ToggleButton button_photo;
    private ToggleButton button_dinner;
    private ToggleButton button_nature;
    private ToggleButton button_walk;
    private ToggleButton button_shopping;
    private ToggleButton button_movie;
    private ToggleButton button_karaoke;
    private ToggleButton button_concert;

    private Button button_back;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceInterest;

    private HashMap<String, String> interest = new HashMap<>();

    public RegisterInterestFragment() {}

    static RegisterInterestFragment registerInterestInstance(HashMap<String, String> userData) {
        RegisterInterestFragment registerInterestFragment = new RegisterInterestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Email", userData.get("Email"));
        bundle.putString("Password", userData.get("Password"));
        bundle.putString("Name", userData.get("Name"));
        bundle.putString("Old", userData.get("Old"));
        bundle.putString("Phone", userData.get("Phone"));
        registerInterestFragment.setArguments(bundle);
        return registerInterestFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_interest, container, false);

        button_photo = view.findViewById(R.id.button_photo);
        button_dinner = view.findViewById(R.id.button_dinner);
        button_nature = view.findViewById(R.id.button_nature);
        button_walk = view.findViewById(R.id.button_walk);
        button_shopping = view.findViewById(R.id.button_shopping);
        button_movie = view.findViewById(R.id.button_movie);
        button_karaoke = view.findViewById(R.id.button_karaoke);
        button_concert = view.findViewById(R.id.button_concert);
        button_back = view.findViewById(R.id.button_back);

        button_photo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("photo-graph", "");
                    button_photo.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_photo.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("photo-graph");
                    button_photo.setBackgroundResource(R.drawable.round_bg_gray);
                    button_photo.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_dinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("dinner", "");
                    button_dinner.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_dinner.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("dinner");
                    button_dinner.setBackgroundResource(R.drawable.round_bg_gray);
                    button_dinner.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_nature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("nature", "");
                    button_nature.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_nature.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("nature");
                    button_nature.setBackgroundResource(R.drawable.round_bg_gray);
                    button_nature.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("walk", "");
                    button_walk.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_walk.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("walk");
                    button_walk.setBackgroundResource(R.drawable.round_bg_gray);
                    button_walk.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_shopping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("shopping", "");
                    button_shopping.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_shopping.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("shopping");
                    button_shopping.setBackgroundResource(R.drawable.round_bg_gray);
                    button_shopping.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_movie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("movie", "");
                    button_movie.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_movie.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("movie");
                    button_movie.setBackgroundResource(R.drawable.round_bg_gray);
                    button_movie.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_karaoke.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("karaoke", "");
                    button_karaoke.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_karaoke.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("karaoke");
                    button_karaoke.setBackgroundResource(R.drawable.round_bg_gray);
                    button_karaoke.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_concert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    interest.put("concert", "");
                    button_concert.setBackgroundResource(R.drawable.round_bg_blue2);
                    button_concert.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                else {
                    interest.remove("concert");
                    button_concert.setBackgroundResource(R.drawable.round_bg_gray);
                    button_concert.setTextColor(getResources().getColor(R.color.colorBlack));
                }
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> userData = new HashMap<>();
                userData.put("Name", name);
                userData.put("Email", email);
                userData.put("Password", password);
                userData.put("Old", old);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, RegisterPhoneFragment.registerPhoneInstance(userData))
                        .addToBackStack(null)
                        .commit();
            }
        });

        assert getArguments() != null;
        email = getArguments().getString("Email");
        password = getArguments().getString("Password");
        name = getArguments().getString("Name");
        old = getArguments().getString("Old");
        phone = getArguments().getString("Phone");
        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(email, password);
            }
        });
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> userData = new HashMap<>();
                userData.put("Name", name);
                userData.put("Email", email);
                userData.put("Password", password);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, RegisterPhoneFragment.registerPhoneInstance(userData))
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void register(String email, String password) {
        final HashMap<String, String> userData = new HashMap<>();
        userData.put("Name", name);
        userData.put("Old", old);
        userData.put("Phone", phone);
        userData.put("Approve", "false");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String userId = Objects.requireNonNull(firebaseUser).getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                            userData.put("ID", userId);
                            databaseReference.setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        databaseReferenceInterest = databaseReference.child("Interest");
                                        databaseReferenceInterest.setValue(interest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "Can't register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
