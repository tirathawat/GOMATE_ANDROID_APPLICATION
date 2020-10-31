package com.example.gomate.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gomate.Adapter.ChatAdapter;
import com.example.gomate.Model.HomeChat;
import com.example.gomate.R;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
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

    private String ShowData(final View view){
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("log",firebaseUser.getUid());
        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chats");
        final HashMap<String, String> AllChats = new HashMap<String, String>();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Test1234",dataSnapshot.toString());
                for (DataSnapshot a : dataSnapshot.getChildren()){
                    if(firebaseUser.getUid().equals(a.child("sender").getValue().toString())||firebaseUser.getUid().equals(a.child("reciever").getValue().toString())){

                        AllChats.put(firebaseUser.getUid().equals(a.child("sender").getValue().toString())?a.child("reciever").getValue().toString() : a.child("sender").getValue().toString(),a.child("message").getValue().toString());
                        Log.d("Test1234",AllChats.toString());
                    }
                }
                List<HomeChat> allchats = new ArrayList<>();
                for(String a : AllChats.keySet()){
                    HomeChat b = new HomeChat(a,AllChats.get("a"),"");
                    allchats.add(b);
                }
                ChatAdapter ca = new ChatAdapter(getContext(),allchats);
                RecyclerView a = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
                Log.d("Gan",a.toString());
                a.setAdapter(ca);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        chatRef.addValueEventListener(listener);
        return "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        String userId =  ShowData(view);
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}