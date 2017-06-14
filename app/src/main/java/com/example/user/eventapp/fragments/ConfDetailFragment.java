package com.example.user.eventapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.eventapp.R;

public class ConfDetailFragment extends Fragment {
    private String TAG_TOPIC;
    private String TAG_ABOUT;
    private String TAG_CONFCHAIR;
    private int TAG_CID;
    private int TAG_DAYS;
    private int TAG_STARTDATE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_conf_detail, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TAG_CID = bundle.getInt("Conf_id");
            TAG_DAYS = bundle.getInt("Conf_days");
            TAG_STARTDATE = bundle.getInt("Conf_date");
            TAG_TOPIC = bundle.getString("Conf_name");
            TAG_CONFCHAIR = bundle.getString("Conf_chair");
            TAG_ABOUT = bundle.getString("Conf_desc");
        }
        String num=Integer.toString(TAG_CID);
        Toast.makeText(getActivity(),num,Toast.LENGTH_SHORT).show();

        return view;

    }


}