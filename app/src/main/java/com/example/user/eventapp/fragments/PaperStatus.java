package com.example.user.eventapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.eventapp.Adapters.ListOfPapers;
import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.RecyclerTouchListener;
import com.example.user.eventapp.basic.UserActivity;
import com.example.user.eventapp.java_models.Papers;

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
import java.util.ArrayList;

public class PaperStatus extends Fragment {


    RecyclerView recyclerView;
    String myJSON;
    JSONArray papers = null;
    String uid;
    ArrayList<Papers> paperList;

    private ProgressBar spinner;


    String paper_fetch = "http://eventapp.000webhostapp.com/userPaperStatus.php";
    private static final String TAG_RESULTS="result";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_paper_status,container,false);
        //barber=new ArrayList<>();
        //  alertMsg=(TextView)view.findViewById( R.id.alert_msg);

        uid=((UserActivity)getActivity()).getUid();
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_list_paper);

        paperList=new ArrayList<Papers>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView ,new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Papers conf = paperList.get(position);
                        String url = "https://docs.google.com/gview?embedded=true&url=" + conf.getUrl();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                })
        );

        getData();
    return view;

    }








    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            HttpURLConnection httpURLConnection;




            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                spinner.setVisibility(View.VISIBLE);
//
            }

            @Override
            protected String doInBackground(String... params) {
                StringBuilder result = new StringBuilder();

                try{
                    URL url = new URL(paper_fetch);
                    int UID=Integer.parseInt(uid);
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
//                spinner.setVisibility(View.GONE);
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    public void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            papers = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<papers.length();i++){
                JSONObject c = papers.getJSONObject(i);


                int id = Integer.parseInt(c.getString("id"));
                String topic = c.getString("topic");
                String papertopic = c.getString("papertopic");
                String status = c.getString("status");

                String url = c.getString("url");

                Papers conf=new Papers(id, topic, papertopic, status, url);


                paperList.add(conf);
            }

            if(paperList!=null){
            ListOfPapers listConfAdapter = new ListOfPapers(getActivity().getApplicationContext(),paperList);
            recyclerView.setAdapter(listConfAdapter);}
            else {
                Toast.makeText(getContext(), "Null list", Toast.LENGTH_SHORT).show();
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
