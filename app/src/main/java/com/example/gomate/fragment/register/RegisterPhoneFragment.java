package com.example.gomate.fragment.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gomate.MainActivity;
import com.example.gomate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterPhoneFragment extends Fragment {

    private TextInputLayout editText_phone;

    private static String email;
    private static String password;
    private static String name;
    private static String old;

    private RegisterPhoneFragment() {}

    static RegisterPhoneFragment registerPhoneInstance(HashMap<String, String> userData) {
        RegisterPhoneFragment registerPhoneFragment = new RegisterPhoneFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Email", userData.get("Email"));
        bundle.putString("Password", userData.get("Password"));
        bundle.putString("Name", userData.get("Name"));
        bundle.putString("Old", userData.get("Old"));
        registerPhoneFragment.setArguments(bundle);
        return registerPhoneFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_phone, container, false);
        assert getArguments() != null;
        email = getArguments().getString("Email");
        password = getArguments().getString("Password");
        name = getArguments().getString("Name");
        old = getArguments().getString("Old");
        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhone();
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
                        .replace(R.id.contentContainer, RegisterOldFragment.registerOldInstance(userData))
                        .addToBackStack(null)
                        .commit();
            }
        });
        editText_phone = view.findViewById(R.id.edit_text_phone);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void addPhone() {
        String phone = Objects.requireNonNull(editText_phone.getEditText()).getText().toString();
        if (TextUtils.isEmpty(phone))
            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "Phone are required", Toast.LENGTH_SHORT).show();
        else {
            HashMap<String, String> userData = new HashMap<>();
            userData.put("Name", name);
            userData.put("Old", old);
            userData.put("Phone", phone);
            userData.put("Email", email);
            userData.put("Password", password);
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer, RegisterInterestFragment.registerInterestInstance(userData))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
