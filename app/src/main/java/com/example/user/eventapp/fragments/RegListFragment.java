package com.example.user.eventapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.eventapp.R;

public class RegListFragment extends Fragment {
    private int TAG_CID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_reg_list, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TAG_CID = bundle.getInt("Conf_id");
        }
        String num=Integer.toString(TAG_CID);
        Toast.makeText(getActivity(),num,Toast.LENGTH_SHORT).show();

        return view;

    }


}
