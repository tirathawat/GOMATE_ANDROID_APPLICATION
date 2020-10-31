package com.example.gomate.fragment.rent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GomateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GomateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GomateFragment extends Fragment {

    private List<Employee> employees;
    ImageView[] profile = new ImageView[4];
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GomateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GomateFragment.
     */
    // TODO: Rename and change types and number of parameters
    private static GomateFragment newInstance(String param1, String param2) {
        GomateFragment fragment = new GomateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gomate, container, false);
        profile[0] = view.findViewById(R.id.profile1);
        profile[1] = view.findViewById(R.id.profile2);
        profile[2] = view.findViewById(R.id.profile3);
        profile[3] = view.findViewById(R.id.profile4);
        employees = new ArrayList<>();
        readUser();
        profile[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, ConfirmFragment.newInstance(employees.get(0)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        profile[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, ConfirmFragment.newInstance(employees.get(1)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        profile[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, ConfirmFragment.newInstance(employees.get(2)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        profile[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, ConfirmFragment.newInstance(employees.get(3)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void readUser() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employees.clear();
                for (DataSnapshot s : snapshot.getChildren()) {
                    Employee employee = s.getValue(Employee.class);
                    assert employee != null;
                    assert firebaseUser != null;
                    if (!employee.getId().equals(firebaseUser.getUid()) && employee.isEmployee()) {
                        employees.add(employee);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
