package com.example.fb.Adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fb.Model.LoadData;
import com.example.fb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FacebookRecyclerAdapater extends RecyclerView.Adapter<FacebookRecyclerAdapater.MyHolder> {

    List<LoadData> fbtimelinelist = new ArrayList<>();
    Context context;

    public FacebookRecyclerAdapater(List<LoadData> fbtimelinelist, Context context) {
        this.fbtimelinelist = fbtimelinelist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facebook_repeat_layout,parent,false);
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final  LoadData timelineData= fbtimelinelist.get(position);

        holder.fullname.setText(timelineData.getFullname());
        holder.status.setText(timelineData.getStatus());
        holder.time.setText(timelineData.getTime());


            Picasso.with(context)
                    .load("http://10.0.2.2:5000/uploads/" + timelineData.getLoadimage())
                    .into(holder.circleimageview);



    }

    @Override
    public int getItemCount() {
        return fbtimelinelist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView circleimageview;
        TextView fullname, status, time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            circleimageview = itemView.findViewById(R.id.imgView_proPic);
            fullname = itemView.findViewById(R.id.tv_name);
            status = itemView.findViewById(R.id.tv_status);
            time = itemView.findViewById(R.id.tv_time);

        }
    }
}
