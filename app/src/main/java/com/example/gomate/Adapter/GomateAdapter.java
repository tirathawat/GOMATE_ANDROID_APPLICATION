package com.example.gomate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gomate.MainActivity;
import com.example.gomate.Model.Employee;
import com.example.gomate.R;

import java.util.List;

public class GomateAdapter extends RecyclerView.Adapter<GomateAdapter.GomateHolder> {
    private Context context;
    private List<Employee> employees;

    public GomateAdapter (Context context, List<Employee> employees) {
        this.employees = employees;
        this.context = context;
    }

    @NonNull
    @Override
    public GomateHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_gomate, parent, false);
        return new GomateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GomateHolder holder, int position) {
        holder.tvName.setText(employees.get(position).getName());
//        holder.ivProfile.setImageResource(employees.get(position).getImageURL());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class GomateHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivProfile;

        public GomateHolder(@NonNull View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_gomate);
            ivProfile = itemView.findViewById(R.id.iv_gomate);
        }
    }
}
