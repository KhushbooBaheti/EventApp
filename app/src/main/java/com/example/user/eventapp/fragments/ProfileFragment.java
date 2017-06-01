package com.example.user.eventapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.eventapp.R;
import com.example.user.eventapp.basic.UserActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class ProfileFragment extends Fragment implements View.OnClickListener{

    TextView Name,Email,Mobile,Specialization;
    Button ChangePassword,EditDetails;
    String Profile_url="http://eventapp.000webhostapp.com/profile.php";
    String myJSON;
    JSONArray user;
    String name,email,specialization,category,uid,mobile;


    public ProfileFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid=((UserActivity)getActivity()).getUid();
        class GetDataJSON extends AsyncTask<String, Void, String> {

            HttpURLConnection httpURLConnection;
            @Override
            protected String doInBackground(String... params) {
                StringBuilder result = new StringBuilder();

                try{
                    int UID=Integer.parseInt(uid);
                    URL url = new URL(Profile_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(String.valueOf(UID),"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line=null;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    //httpURLConnection.disconnect();
                }
                return result.toString();
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_profile, container, false);

        Name=(TextView)v.findViewById(R.id.Name);
        Email=(TextView)v.findViewById(R.id.Email);
        Mobile=(TextView)v.findViewById(R.id.Mobile);
        Specialization=(TextView)v.findViewById(R.id.Spec);

        ChangePassword=(Button)v.findViewById(R.id.change_password);
        EditDetails=(Button)v.findViewById(R.id.edit_details);




                return v;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ChangePassword.setOnClickListener(this);
        EditDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_password:
                Toast.makeText(getActivity(),"Paid.....",Toast.LENGTH_LONG).show();
                break;
            case R.id.edit_details:
                Toast.makeText(getActivity(),"Paid.....",Toast.LENGTH_SHORT).show();
                break;

            default:

                break;
        }
    }
    public void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            user = jsonObj.getJSONArray("result");

            for(int i=0;i<user.length();i++){
                JSONObject c = user.getJSONObject(i);
                name=c.getString("name");
                mobile=c.getString("mobile_no");
                specialization=c.getString("specialization");
                email=c.getString("email");
                category=c.getString("category");
                Name.setText(name);
                Mobile.setText(mobile);
                Email.setText(email);
                Specialization.setText(specialization);



            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
