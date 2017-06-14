package com.example.user.eventapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.backGroundWorker;


public class UserEnquiryFragment extends Fragment implements View.OnClickListener{

    EditText topic,message;
    Button enquire;
    String uid,cid,Topic,Message;

    public UserEnquiryFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_user_enquiry, container, false);

        topic=(EditText)v.findViewById(R.id.topic);
        message=(EditText)v.findViewById(R.id.message);
        enquire=(Button)v.findViewById(R.id.btnenquire);
        enquire.setOnClickListener(this);
        Bundle b=this.getArguments();
        if(b!=null){

            cid=String.valueOf(b.getInt("Conf_id"));
            uid=b.getString("uid");
        }

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnenquire:

                Topic=topic.getText().toString();
                Message=message.getText().toString();
                String type = "userenquiry";
               // Toast.makeText(getContext(),"cid"+cid,Toast.LENGTH_SHORT).show();
                backGroundWorker backgroundWorker = new backGroundWorker(getContext(),getActivity());
                backgroundWorker.execute(type, cid,uid,Topic,Message);
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                topic.getText().clear();
                message.getText().clear();


                break;


            default:

                break;
        }
    }
}
