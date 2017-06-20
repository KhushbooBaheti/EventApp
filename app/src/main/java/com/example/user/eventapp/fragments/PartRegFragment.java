package com.example.user.eventapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.backGroundWorker;
import com.example.user.eventapp.basic.CustomDialog;
import com.example.user.eventapp.basic.MainActivity;


public class PartRegFragment extends Fragment implements View.OnClickListener{

    private Button btnPay;
    private Button btnUpload;
    private Button btnRegister;
    private Button btnSelect;
    private String TAG_ID;
    private int TAG_CID;
    private String uid,cid;
    private String name;
    private String mobile;
    private String email;
    private EditText namefield;
    private EditText mobilefield;
    private EditText emailfield;
    private String checktype = "2";
    private LinearLayout paper;
    private boolean readCond;
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
        namefield=(EditText)view.findViewById(R.id.name);
        mobilefield=(EditText)view.findViewById(R.id.mobile);
        emailfield=(EditText)view.findViewById(R.id.email);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TAG_CID = bundle.getInt("Conf_id");
        }
        cid=Integer.toString(TAG_CID);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("UID_Details", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("Name","no name defined");
        mobile=sharedPreferences.getString("Mobile_No","0000000000");
        email=sharedPreferences.getString("Email","xxxxxx@YYYYYY");
        namefield.setText(name);
        mobilefield.setText(mobile);
        emailfield.setText(email);
        namefield.setEnabled(false);
        mobilefield.setEnabled(false);
        emailfield.setEnabled(false);
        uid=this.getActivity().getSharedPreferences("loggedIn info", Context.MODE_PRIVATE).getString("uid","");

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnPay=(Button) getActivity().findViewById(R.id.btnPay);
        btnUpload=(Button) getActivity().findViewById(R.id.btnUpload);
        btnSelect = (Button)getActivity().findViewById(R.id.btnSelect);
        btnRegister=(Button) getActivity().findViewById(R.id.btnRegister);
        paper = (LinearLayout)getActivity().findViewById(R.id.linear_paper);
        btnPay.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

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
            case R.id.btnRegister:
                CustomDialog cdd = new CustomDialog(getActivity());
                cdd.setCancelable(true);
                cdd.setType("listenerRegistration");
                cdd.setChecktype("2");
                cdd.setCid(cid);
                cdd.setUid(uid);
                cdd.setPaper(paper);
                cdd.show();
                readCond = cdd.isReadCond();
            case R.id.btnSelect:
                break;
            default:

                break;
        }
    }
}
