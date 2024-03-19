package com.example.get_a_job;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import java.util.ArrayList;

import kotlinx.coroutines.Job;

public class ArrayAdaptor_JobDisplayObject extends RecyclerView.Adapter<ArrayAdaptor_JobDisplayObject.MyViewHolder> {

    ArrayList<JobDisplayObject> datalist;

    public ArrayAdaptor_JobDisplayObject(ArrayList<JobDisplayObject> data){
        this.datalist =data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_job_display,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrayAdaptor_JobDisplayObject.MyViewHolder holder, int position) {

        JobDisplayObject data =datalist.get(position);
        holder.tv_job_name.setText(data.getJob_name());
        holder.tv_job_company.setText(data.getJob_company());
        holder.tv_job_location.setText(data.getJob_location());
        holder.tv_job_applied_date.setText(data.getJob_applied_date());
//        holder.imageView.setImageResource(data.getImage());
//        holder.textView.setText(data.getText());
//        holder.buttonView.setText(data.getButton());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_job_name,tv_job_company,tv_job_location,tv_job_applied_date;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            tv_job_name = itemView.findViewById(R.id.tv_job_name);
            tv_job_company = itemView.findViewById(R.id.tv_job_company);
            tv_job_location = itemView.findViewById(R.id.tv_job_location);
            tv_job_applied_date = itemView.findViewById(R.id.tv_job_applied_date);

        }


    }

}