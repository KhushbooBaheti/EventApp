package com.example.user.eventapp.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.backGroundWorker;


public class LoginActivity extends AppCompatActivity {

    EditText name;
    EditText password;
    RadioButton user,organiser,volunteer;

    int checkValue=1;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=(EditText)findViewById(R.id.uname);
        password=(EditText)findViewById(R.id.password);
        loginButton=(Button)findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String uname = name.getText().toString();
                String upassword = password.getText().toString();
               /* String category="user";
                if(checkValue==1){
                    category="user";
                }
                else if(checkValue==2){
                    category="organiser";
                }
                else if(checkValue==3){
                    category="volunteer";
                }
*/

                String type = "login";
                backGroundWorker backgroundWorker = new backGroundWorker(getApplicationContext(),LoginActivity.this);
                backgroundWorker.execute(type, uname,upassword);
            }
        });


    }
  /* public void checkedRadio(View view) {
        onRadioButtonClicked();
    }*/

    /*public void onRadioButtonClicked() {


        organiser = (RadioButton) findViewById(R.id.organiser);
        user = (RadioButton) findViewById(R.id.users);
        volunteer=(RadioButton) findViewById(R.id.volunteer);

        if (user.isChecked()) {
            checkValue = 1;

        }
        if (organiser.isChecked()) {
            checkValue = 2;
        }
        if(volunteer.isChecked()){
            checkValue = 3;
        }


    }*/
    public void signup(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
