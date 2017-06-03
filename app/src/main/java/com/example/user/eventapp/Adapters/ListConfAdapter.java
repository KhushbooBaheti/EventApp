package com.example.user.eventapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.eventapp.R;
import com.example.user.eventapp.java_models.Conference;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Created by user on 28/5/17.
 */

public class ListConfAdapter extends RecyclerView.Adapter<ListConfAdapter.MyViewHolder> {

    ArrayList<Conference> conferenceList;
    Context mContext;

    public  ListConfAdapter(Context mContext,ArrayList<Conference> conferenceList){
        this.conferenceList=conferenceList;
        this.mContext=mContext;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView confName, venue, startDate;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image=(ImageView) view.findViewById(R.id.iv_conf);
            confName=(TextView) view.findViewById(R.id.tv_conf_name);
            venue=(TextView) view.findViewById(R.id.tv_venue);
            startDate=(TextView) view.findViewById(R.id.tv_start_date);


        }
    }
    @Override
    public ListConfAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_conf_row, parent, false);
        return new ListConfAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListConfAdapter.MyViewHolder holder, int position) {
        final Conference conf=conferenceList.get(position);

        holder.confName.setText(conf.getConfName());
        holder.venue.setText(conf.getVenue());

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(conf.getConfDate());
        holder.startDate.setText(s);

        Glide.with(mContext).load(conf.getImageURL())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return conferenceList.size();
    }
}
