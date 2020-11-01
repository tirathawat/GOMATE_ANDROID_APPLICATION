package com.example.gomate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gomate.MainActivity;
import com.example.gomate.Model.Employee;
import com.example.gomate.R;
import com.example.gomate.fragment.rent.ConfirmFragment;
import com.example.gomate.fragment.rent.DescriptionFragment;

import java.util.HashMap;
import java.util.List;

public class GomateAdapter extends RecyclerView.Adapter<GomateAdapter.GomateHolder> {
    private Context context;
    private List<Employee> employees;
    private HashMap<String, String> data;

    public GomateAdapter (Context context, List<Employee> employees, HashMap<String, String> data) {
        this.employees = employees;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GomateHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_gomate, parent, false);
        return new GomateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GomateHolder holder, final int position) {
        final Employee employee = employees.get(position);
        holder.tvName.setText(employee.getName());
        if (employee.getImageURL().equals("default")) {
            holder.ivProfile.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Glide.with(context).load(employee.getImageURL()).into(holder.ivProfile);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity)v.getContext();

                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.home_frame, ConfirmFragment.newInstance(employee, data))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class GomateHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivProfile;
        CardView cardView;

        public GomateHolder(@NonNull View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tvName = itemView.findViewById(R.id.tv_name_gomate);
            ivProfile = itemView.findViewById(R.id.iv_gomate);
        }
    }
}
