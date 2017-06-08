package com.example.user.eventapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.eventapp.R;


public class PartRegFragment extends Fragment implements View.OnClickListener{

    Button btnPay;
    Button btnUpload;
    Button btnSumbit;
    private String TAG_ID;

    public PartRegFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);








    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_part_reg, container, false);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnPay=(Button) getActivity().findViewById(R.id.btnPay);
        btnUpload=(Button) getActivity().findViewById(R.id.btnUpload);
        btnSumbit=(Button) getActivity().findViewById(R.id.btnSubmit);

        btnPay.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        btnSumbit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPay:
                Toast.makeText(getActivity(),"Paid.....",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnUpload:
                Toast.makeText(getActivity(),"Paid.....",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSubmit:
                Toast.makeText(getActivity(),"Paid.....",Toast.LENGTH_LONG).show();
                break;
            default:

                break;
        }
    }
}
