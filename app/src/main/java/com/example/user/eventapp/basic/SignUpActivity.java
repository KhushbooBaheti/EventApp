package com.example.user.eventapp.basic;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.backGroundWorker;


public class SignUpActivity extends Activity {
   /**
    *   JSON Response node names.
    **/

    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_MOBILE_NO= "mobile_no";
    private static String KEY_SPECIALIZATION = "specialization";
    private static String KEY_EMAIL = "email";
    private static String KEY_CATEGORY = "category";
    private static String KEY_PASSWORD = "password";
    private static String KEY_ERROR ="error";

    /**
     * Defining layout items.
     **/

    EditText inputName;
    EditText inputMobileno;
    EditText inputSpecialization;
    EditText inputEmail;
    EditText inputPassword;
    EditText inputConfirmPassword;
    Button btnRegister;
    ImageView chechConfirm;
    boolean check;

    String inputPasswordField;
    String inputConfirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        inputName=(EditText) findViewById(R.id.name);
        inputMobileno=(EditText) findViewById(R.id.mobile);
        inputSpecialization=(EditText) findViewById(R.id.specialization);
        inputEmail=(EditText) findViewById(R.id.email);
        inputPassword=(EditText) findViewById(R.id.password);
        btnRegister=(Button)findViewById(R.id.buttonsign) ;
        chechConfirm=(ImageView)findViewById(R.id.checkconfirm) ;
        inputConfirmPassword=(EditText)findViewById(R.id.confirm_password);
        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputPasswordField=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                    inputPasswordField=s.toString();
                check=checkPasswordMatch(inputPasswordField,inputConfirm);
                if(check){

                    btnRegister.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            if(v == btnRegister){
                                String name = inputName.getText().toString();
                                String password = inputPassword.getText().toString();
                                String mobile_no = inputMobileno.getText().toString();
                                String email = inputEmail.getText().toString();
                                String specialization=inputSpecialization.getText().toString();
                                String type = "signup";
                                backGroundWorker backgroundWorker = new backGroundWorker(getApplicationContext(),SignUpActivity.this);
                                backgroundWorker.execute(type, name,mobile_no,specialization,email,password);
                            }
                        }

                    });}
                else{
                    btnRegister.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v){
                            if(v == btnRegister){
                                Toast.makeText(getApplicationContext(),"Passwords does'nt match,re-enter",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

        inputConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputConfirm=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                    inputConfirm=s.toString();
                check=checkPasswordMatch(inputPasswordField,inputConfirm);
                if(check){

                    btnRegister.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            if(v == btnRegister){
                                String name = inputName.getText().toString();
                                String password = inputPassword.getText().toString();
                                String mobile_no = inputMobileno.getText().toString();
                                String email = inputEmail.getText().toString();
                                String specialization=inputSpecialization.getText().toString();
                                String type = "signup";
                                backGroundWorker backgroundWorker = new backGroundWorker(getApplicationContext(),SignUpActivity.this);
                                backgroundWorker.execute(type, name,mobile_no,specialization,email,password);
                            }
                        }

                    });}
                else{
                    btnRegister.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v){
                            if(v == btnRegister){
                                Toast.makeText(getApplicationContext(),"Passwords does'nt match,re-enter",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

    }
    public boolean checkPasswordMatch(String s1,String s2){
        if(s1.equals(s2)){
            chechConfirm.setVisibility(View.VISIBLE);
            return true;
        }
        else{
           chechConfirm.setVisibility(View.INVISIBLE);
            return false;
        }
    }

}
