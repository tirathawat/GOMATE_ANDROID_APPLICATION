package com.example.gomate.fragment.register;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gomate.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Objects;

public class RegisterOldFragment extends Fragment {

    private TextInputLayout editText_old;

    private static String email, password, name;

    private RegisterOldFragment() {}

    static RegisterOldFragment registerOldInstance(HashMap<String, String> userData) {
        RegisterOldFragment registerOldFragment = new RegisterOldFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name", userData.get("Name"));
        bundle.putString("Email", userData.get("Email"));
        bundle.putString("Password", userData.get("Password"));
        registerOldFragment.setArguments(bundle);
        return registerOldFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_old, container, false);
        email = Objects.requireNonNull(getArguments()).getString("Email");
        password = Objects.requireNonNull(getArguments()).getString("Password");
        name = Objects.requireNonNull(getArguments()).getString("Name");
        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOld();
            }
        });
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> userData = new HashMap<>();
                userData.put("Email", email);
                userData.put("Password", password);
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, RegisterNameFragment.registerNameInstance(userData))
                        .addToBackStack(null)
                        .commit();
            }
        });
        editText_old = view.findViewById(R.id.edit_text_old);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void addOld() {
        String old = Objects.requireNonNull(editText_old.getEditText()).getText().toString();
        if (TextUtils.isEmpty(old))
            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "Old are required", Toast.LENGTH_SHORT).show();
        else {
            HashMap<String, String> userData = new HashMap<>();
            userData.put("Email", email);
            userData.put("Password", password);
            userData.put("Name", name);
            userData.put("Old", old);
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer, RegisterPhoneFragment.registerPhoneInstance(userData))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
