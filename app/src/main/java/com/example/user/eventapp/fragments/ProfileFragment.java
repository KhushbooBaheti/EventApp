package com.example.user.eventapp.fragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.backGroundWorker;
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

    TextView Name,Email,Mobile,Specialization,Category;
    Button ChangePassword,EditDetails;
    String Profile_url="http://eventapp.000webhostapp.com/profile.php";
    String myJSON;
    JSONArray user;
    String name,email,specialization,category,uid,mobile;

    private ProgressBar spinner;

    public ProfileFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uid=((UserActivity)getActivity()).getUid();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_profile, container, false);

        Name=(TextView)v.findViewById(R.id.Name);
        Email=(TextView)v.findViewById(R.id.Email);
        Mobile=(TextView)v.findViewById(R.id.Mobile);
        Specialization=(TextView)v.findViewById(R.id.Spec);
        Category=(TextView)v.findViewById(R.id.Category);
        ChangePassword=(Button)v.findViewById(R.id.change_password);
        EditDetails=(Button)v.findViewById(R.id.edit_details);
        spinner = (ProgressBar)v.findViewById(R.id.progressBar2);

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
                   // httpURLConnection.disconnect();
                }
                return result.toString();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String result){
                spinner.setVisibility(View.GONE);
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();








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
               getChangePassDailog();
                break;
            case R.id.edit_details:
                getEditDetailsDailog();
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
                Category.setText(category);



            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    void getChangePassDailog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_change_password, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.new_password);

        dialogBuilder.setTitle("Change Password");
        dialogBuilder.setMessage("Enter new password:");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String password=edt.getText().toString();
                String type = "changepassword";
                backGroundWorker backgroundWorker = new backGroundWorker(getContext(),getActivity());
                backgroundWorker.execute(type,uid,password);
                dialog.dismiss();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    void getEditDetailsDailog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_edit_detials, null);
        dialogBuilder.setView(dialogView);


        final EditText newName = (EditText) dialogView.findViewById(R.id.new_name);
        final EditText newMobile = (EditText) dialogView.findViewById(R.id.new_mobile);
        final EditText newEmail = (EditText) dialogView.findViewById(R.id.new_email);
        final EditText newSpecialization = (EditText) dialogView.findViewById(R.id.new_specialization);

        dialogBuilder.setTitle("Edit Details ");
        dialogBuilder.setMessage("Enter the new Details:");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String newname=newName.getText().toString();
                String newmobile=newMobile.getText().toString();
                String newemail=newEmail.getText().toString();
                String newspecialization=newSpecialization.getText().toString();
                String type = "editdetails";
                backGroundWorker backgroundWorker = new backGroundWorker(getContext(),getActivity());
                backgroundWorker.execute(type,uid,newname,newmobile,newemail,newspecialization);
                dialog.dismiss();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
