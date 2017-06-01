package com.example.user.eventapp.Utilties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.user.eventapp.basic.UserActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 29/5/17.
 */

public class backGroundWorker extends AsyncTask<String,Void,String> {

    String type1,name,category;
    Context context;
    Activity activity;

    public backGroundWorker (Context ctx,Activity act) {
        context = ctx;
        activity = act;
    }
    @Override
    protected String doInBackground(String... params) {
        type1 = params[0];
        String login_url = "http://eventapp.000webhostapp.com/login.php";
        String signup_url = "http://eventapp.000webhostapp.com/signup.php";
        String conference_data_fetch_url = "http://eventapp.000webhostapp.com/conference_list.php";
        if(type1.equals("login")) {
            try {
                 name = params[1];
                String password = params[2];
                 category = params[3];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(category
                        ,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type1.equals("signup")){
            try {
                String name = params[1];
                String mobile_no = params[2];
                String specialization = params[3];
                String email = params[4];
                String password = params[5];

                URL url = new URL(signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("mobile_no","UTF-8")+"="+URLEncoder.encode(mobile_no,"UTF-8")+"&"
                        +URLEncoder.encode("specialization","UTF-8")+"="+URLEncoder.encode(specialization,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }
            catch(Exception e){
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

       // int uid=Integer.parseInt(result);

        if(result.equals("login not success,name,password or category not valid")){

        }
        else{
            SharedPreferences sharedPreferences = context.getSharedPreferences("loggedIn info", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("category", category);
            editor.putString("uid",result);
            editor.commit();

            Intent intent = new Intent(context, UserActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.finish();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
