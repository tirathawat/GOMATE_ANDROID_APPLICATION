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

import com.example.gomate.Adapter.ChatAdapter;
import com.example.gomate.Adapter.UserAdapter;
import com.example.gomate.Model.HomeChat;
import com.example.gomate.Model.User;
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

public class ChatFragment extends Fragment {
    private HashMap<String, String> users = new HashMap<>();
    private RecyclerView recyclerView;
    private ChatAdapter ChatAdapter;
    private List<HomeChat> chats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        getAllUsersData();
        recyclerView = view.findViewById(R.id.chat_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chats = new ArrayList<>();
        ChatAdapter = new ChatAdapter(getContext(), chats);
        recyclerView.setAdapter(ChatAdapter);
        getFirebaseData(view);
        //readUser();
        return view;
    }
    private void getAllUsersData (){
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot a : dataSnapshot.getChildren()){
                    Log.d("Test1234",a.child("ID").toString());
                    if(users.containsKey(a.child("ID").toString())){
                        Log.d("Test1234","Duplicate user found");
                    }
                    else {
                        users.put(a.getKey().toString(),a.child("Name").getValue().toString());
                    }
                    Log.d("Test1234",users.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getFirebaseData (View view){
        final FirebaseUser firebaseUser;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = view.findViewById(R.id.chat_recycler_view);
        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chats");
        chats = new ArrayList<>();
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chats = new ArrayList<>();
                for(DataSnapshot a : dataSnapshot.getChildren()){
                    if(a.child("sender").getValue().toString().equals(firebaseUser.getUid())||a.child("receiver").getValue().toString().equals(firebaseUser.getUid())){

                        Log.d("Test1234",a.toString());
                        String targetName = a.child("sender").getValue().toString().equals((firebaseUser.getUid()))? a.child("receiver").getValue().toString() : a.child("sender").getValue().toString();
                        String userId = targetName;
                        targetName = users.get(targetName);
                        boolean isFound = false;
                        Log.d("Test1234",targetName);
                        for(HomeChat b  : chats){
                            if(b.getName().equals(targetName)){
                                b.setLastMessage(a.child("message").getValue().toString());
                                isFound = true;
                            }
                        }
                        if(!isFound){
                            Log.d("Test",userId);
                            HomeChat b = new HomeChat(targetName,a.child("message").getValue().toString(),"default",a.child("timestamp").getValue().toString(),userId);
                            chats.add(b);
                        }
                    }
                }
                ChatAdapter = new ChatAdapter(getContext(),chats);
                recyclerView.setAdapter(ChatAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}