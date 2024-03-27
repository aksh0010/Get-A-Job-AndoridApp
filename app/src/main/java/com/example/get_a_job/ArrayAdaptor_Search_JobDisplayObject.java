package com.example.get_a_job;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArrayAdaptor_Search_JobDisplayObject extends RecyclerView.Adapter<ArrayAdaptor_Search_JobDisplayObject.MyViewHolder> {

    ArrayList<JobDisplay_Search_Object> datalist;
    private ItemClickListener itemClickListener;
    public ArrayAdaptor_Search_JobDisplayObject(ArrayList<JobDisplay_Search_Object> data, String user_email){
        this.datalist =data;
        this.user_email = user_email;
    }

    public ImageView saveImg;
    public String user_email;
    public void setItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_saved_job_display,parent,false);
        saveImg = view.findViewById(R.id.saveImg);

        saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(parent.getContext(), "test_db", null, 1);
                TextView id_view = view.findViewById(R.id.tv_job_id);
                String id = id_view.getText().toString();

                dbHelper.markJob(view,user_email,id);
                TextView test_v = view.findViewById(R.id.tv_job_name);
                String txt = test_v.getText().toString();
                //Log.d("testñ","tezting#"+id+"");
                //Log.d("testñ","Want to mark applic #"+id+"");
                Log.d("testñ","List has been Updated");
                notifyDataSetChanged();
                dbHelper.close();

            }
        });
        return  new MyViewHolder(view);
    }
    public void setId(String user_id){

    }
    @Override
    public void onBindViewHolder(@NonNull ArrayAdaptor_Search_JobDisplayObject.MyViewHolder holder, int position) {

        JobDisplay_Search_Object data =datalist.get(position);
        holder.tv_job_name.setText(data.getJob_name());
        holder.tv_job_company.setText(data.getJob_company());
        holder.tv_job_location.setText(data.getJob_location());
        holder.tv_job_applied_date.setText(data.getJob_applied_date());
        holder.tv_job_id.setText(data.getJob_id());
//        holder.imageView.setImageResource(data.getImage());
//        holder.textView.setText(data.getText());
//        holder.buttonView.setText(data.getButton());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_job_name,tv_job_company,tv_job_location,tv_job_applied_date,tv_job_id;
        ImageView saveImg;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            tv_job_name = itemView.findViewById(R.id.tv_job_name);
            tv_job_company = itemView.findViewById(R.id.tv_job_company);
            tv_job_location = itemView.findViewById(R.id.tv_job_location);
            tv_job_applied_date = itemView.findViewById(R.id.tv_job_applied_date);
            tv_job_id = itemView.findViewById(R.id.tv_job_id);
            saveImg = itemView.findViewById(R.id.saveImg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }


    }
    public interface ItemClickListener {
        void onItemClick(int position);
    }
}