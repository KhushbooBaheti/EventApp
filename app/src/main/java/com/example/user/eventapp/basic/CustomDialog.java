package com.example.user.eventapp.basic;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.backGroundWorker;

/**
 * Created by Rahul on 19-06-2017.
 */

public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Activity c;
    private Dialog d;
    private Button yes, no;
    private EditText topicName;
    private CheckBox check;
    private boolean readCond = false;
    private String uid;
    private String cid;
    private String checktype,type;
    private LinearLayout paper;

    public void setPaper(LinearLayout paper) {
        this.paper = paper;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReadCond() {

        return readCond;
    }

    public void setReadCond(boolean Cond) {
        this.readCond = Cond;
    }



    public CustomDialog(Activity activity) {
        super(activity);
        this.c=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        topicName = (EditText)findViewById(R.id.edtxt_topic);
        check = (CheckBox)findViewById(R.id.checkbtn);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                // c.finish();
                if(check.isChecked()==true){
                    setReadCond(true);
                    backGroundWorker backgroundWorker = new backGroundWorker(getContext(),c);
                    backgroundWorker.execute(type,cid,uid,checktype);
                    //paper.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getContext(),"Please read the guidelines related to Paper",Toast.LENGTH_SHORT).show();
                    setReadCond(false);
                }
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();

    }
}

