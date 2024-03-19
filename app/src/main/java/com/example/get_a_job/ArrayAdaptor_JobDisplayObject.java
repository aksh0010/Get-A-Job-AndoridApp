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
        holder.imageView.setImageResource(data.getImage());
        holder.textView.setText(data.getText());
        holder.buttonView.setText(data.getButton());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        Button buttonView;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            textView= itemView.findViewById(R.id.tv_card);
            buttonView = itemView.findViewById(R.id.tv_button);

            buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String buttonText = buttonView.getText().toString();
                    int newColor = Color.WHITE;

                    // Determine the new color based on the current button text
                    switch (buttonText) {
                        case "Red":
                            newColor = Color.RED;
                            textView.setTextColor(newColor);
                            buttonView.setText("White");
                            break;
                        case "Yellow":
                            newColor = Color.YELLOW;
                            textView.setTextColor(newColor);
                            buttonView.setText("White");
                            break;
                        case "Black":
                            newColor = Color.BLACK;
                            textView.setTextColor(newColor);
                            buttonView.setText("White");
                            break;
                        case "White":
                            // for first card
                            newColor = Color.WHITE;
                            if (textView.getText().equals("Card1") || textView.getText().equals("Text1"))
                            {
                                textView.setTextColor(newColor);
                                buttonView.setText("Red");
                            } else   if (textView.getText().equals("Card2") || textView.getText().equals("Text2"))
                            {
                                textView.setTextColor(newColor);
                                buttonView.setText("Yellow");
                            } else   if (textView.getText().equals("Card3") || textView.getText().equals("Text3"))
                            {
                                textView.setTextColor(newColor);
                                buttonView.setText("Black");
                            }

                            break;
                    }
                }
            });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(textView.getText().equals("Card1")){
                        textView.setText("Text1");
                    } else if (textView.getText().equals("Card2")) {
                        textView.setText("Text2");
                    }else if(textView.getText().equals("Card3")){
                        textView.setText("Text3");
                    } else
                    if(textView.getText().equals("Text1")){
                        textView.setText("Card1");
                    } else if (textView.getText().equals("Text2")) {
                        textView.setText("Card2");
                    }else if(textView.getText().equals("Text3")){
                        textView.setText("Card4");
                    }

                }
            });

        }


    }

}