package com.example.gomate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gomate.MessageActivity;
import com.example.gomate.Model.HomeChat;
import com.example.gomate.Model.User;
import com.example.gomate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static com.example.gomate.R.mipmap.ic_launcher;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context context;
    private List<HomeChat> chats;

    public ChatAdapter (Context context, List<HomeChat> chats) {
        this.chats = chats;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final HomeChat chat = chats.get(position);
        holder.name.setText(chat.getName());
        holder.lastMessage.setText(chat.getLastMessage());
//        holder.img.
        holder.timestamp.setText(chat.getTimeStamp());
        holder.userId = chat.getuserId();

        holder.box.findViewById(R.id.home_chat_box).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("Test",holder.userId);
                Intent intentMessage = new Intent(context, MessageActivity.class);
                intentMessage.putExtra("userId",holder.userId);
                context.startActivity(intentMessage);

            }
        });
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView lastMessage;
        ImageView img;
        TextView timestamp;
        LinearLayout box;
        String userId;
        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.chat_name);
            lastMessage = itemView.findViewById(R.id.chat_lastMessage);
            img = itemView.findViewById(R.id.chat_img);
            timestamp = itemView.findViewById(R.id.chat_timestamp);
            box = itemView.findViewById(R.id.home_chat_box);
        }
    }
}
