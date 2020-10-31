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


public class RegisterNameFragment extends Fragment {
    private Callback mCallback;

    private TextInputLayout editText_name;

    private static String email, password;

    public RegisterNameFragment() {}

    static RegisterNameFragment registerNameInstance(HashMap<String, String> userData) {
        RegisterNameFragment registerNameFragment = new RegisterNameFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Email", userData.get("Email"));
        bundle.putString("Password", userData.get("Password"));
        registerNameFragment.setArguments(bundle);
        return registerNameFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_name, container, false);
        email = Objects.requireNonNull(getArguments()).getString("Email");
        password = Objects.requireNonNull(getArguments()).getString("Password");
        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.someEvent(new RegisterEmailFragment());
            }
        });
        editText_name = view.findViewById(R.id.edit_text_name);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }

    private void addName() {
        String name = Objects.requireNonNull(editText_name.getEditText()).getText().toString();
        if (TextUtils.isEmpty(name))
            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "Name are required", Toast.LENGTH_SHORT).show();
        else {
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
    }
}
