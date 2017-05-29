package com.example.user.eventapp.basic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    Button btnRegister;
    TextView registerErrorMsg;


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
                    backGroundWorker backgroundWorker = new backGroundWorker(getApplicationContext());
                    backgroundWorker.execute(type, name,mobile_no,specialization,email,password);
                }
            }

        });

    }

}
