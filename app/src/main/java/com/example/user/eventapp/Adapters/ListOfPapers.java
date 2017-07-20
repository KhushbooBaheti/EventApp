package com.example.user.eventapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.eventapp.R;
import com.example.user.eventapp.java_models.Papers;

import java.util.ArrayList;

/**
 * Created by user on 20/7/17.
 */

public class ListOfPapers  extends RecyclerView.Adapter<ListOfPapers.MyViewHolder> {
    ArrayList<Papers> paperList;
    Context mContext;

    public  ListOfPapers(Context mContext,ArrayList<Papers> conferenceList){
        paperList=conferenceList;
        this.mContext=mContext;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView confName, venue, startDate;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);

            confName=(TextView) view.findViewById(R.id.tv_paper_topic);
            venue=(TextView) view.findViewById(R.id.tv_conference_name);
            startDate=(TextView) view.findViewById(R.id.tv_status);


        }
    }
    @Override
    public ListOfPapers.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_paper, parent, false);
        return new ListOfPapers.MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(ListOfPapers.MyViewHolder holder, int position) {
        final Papers conf=paperList.get(position);

        holder.confName.setText(conf.getPapertopic());
        holder.venue.setText(conf.getTopic());


        holder.startDate.setText(conf.getStatus());



    }

    @Override
    public int getItemCount() {
        return paperList.size();
    }
}

