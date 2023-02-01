package com.example.mygallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
Context context;
private ArrayList<String> imageFileList= new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<String> imageFileList){
this.context = context;
this.imageFileList = imageFileList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
         View view = inflater.inflate(R.layout.card,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        int currentPosition = holder.getAdapterPosition();
        Glide.with(context)
                .load(imageFileList.get(currentPosition))
                .centerCrop()
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullScreenActivity.class);
                intent.putExtra("imageList",imageFileList);
                intent.putExtra("position", holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageFileList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);


        }
    }


}
