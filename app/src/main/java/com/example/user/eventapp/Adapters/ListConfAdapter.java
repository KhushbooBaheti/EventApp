package com.example.user.eventapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.eventapp.R;


/**
 * Created by user on 28/5/17.
 */

public class ListConfAdapter extends RecyclerView.Adapter<ListConfAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView confName, venue, startDate;

        public MyViewHolder(View view) {
            super(view);
            confName=(TextView) view.findViewById(R.id.tv_conf_name);
            venue=(TextView) view.findViewById(R.id.tv_venue);
            startDate=(TextView) view.findViewById(R.id.tv_start_date);


        }
    }
    @Override
    public ListConfAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListConfAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
