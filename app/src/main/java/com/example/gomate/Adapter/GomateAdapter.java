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

public class GomateAdapter extends RecyclerView.Adapter<GomateAdapter.ViewHolder> {
    private Context context;
    private List<Employee> employees;

    public GomateAdapter (Context context, List<Employee> employees) {
        this.employees = employees;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gomate_item, parent, false);
        return new GomateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Employee employee = employees.get(position);
        if (employee.getImageURL().equals("default")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Glide.with(context).load(employee.getImageURL()).into(holder.profile_image);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("employeeId", employee.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_image;

        ViewHolder(View itemView) {
            super (itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
