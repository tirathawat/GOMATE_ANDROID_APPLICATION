package com.example.gomate.fragment.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.gomate.R;
import com.example.gomate.StartActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Objects;

public class RegisterEmailFragment extends Fragment {

    private TextInputLayout editText_email, editText_password;

    public RegisterEmailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_email, container, false);
        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmailPassword();
            }
        });
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        editText_email = view.findViewById(R.id.edit_text_email);
        editText_password = view.findViewById(R.id.edit_text_password);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void addEmailPassword() {
        String email = Objects.requireNonNull(editText_email.getEditText()).getText().toString();
        String password = Objects.requireNonNull(editText_password.getEditText()).getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        else if (password.length() < 6)
            Toast.makeText(Objects.requireNonNull(getActivity()).getBaseContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        else {
            HashMap<String, String> userData = new HashMap<>();
            userData.put("Email", email);
            userData.put("Password", password);
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer, RegisterNameFragment.registerNameInstance(userData))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
