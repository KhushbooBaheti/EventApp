package com.example.user.eventapp.basic;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.user.eventapp.Adapters.ListConfAdapter;
import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.RecyclerTouchListener;
import com.example.user.eventapp.java_models.Conference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListOfConfActivity extends AppCompatActivity {


    String myJSON;

    String conference_data_fetch_url = "http://eventapp.000webhostapp.com/conference_list.php";

    private static final String TAG_RESULTS="result";
    private static final String TAG_CID = "cid";
    private static final String TAG_TOPIC ="topic";
    private static final String TAG_STARTDATE ="startdate";
    private static final String TAG_VENUE ="venue";
    private static final String TAG_ABOUT ="about";
    private static final String TAG_SCHEDULE ="schedule";
    private static final String TAG_CONFCHAIR ="confchair";
    private static final String TAG_DAYS ="days";
    private static final String TAG_FEESLIST="fees_list";
    private static final String TAG_FEESPART="fees_part";
    private static final String TAG_IMAGEURL ="imageURL";


    JSONArray conferences = null;
    private RecyclerView recyclerView;
    private CardView cardview;
    ArrayList<Conference> conferenceList;
    int[] images;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_conf);
        Toolbar tootlbar = (Toolbar) findViewById(R.id.mToolbar);
        tootlbar.setTitle("Conferences");
        tootlbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(tootlbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        cardview = (CardView) findViewById(R.id.card_view_list_conf);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_conf);

        RecyclerView.LayoutManager oLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(oLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        conferenceList=new ArrayList<Conference>();

        getData();
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), recyclerView ,new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Conference conf = conferenceList.get(position);
                        Toast.makeText(getApplicationContext(),conf.getConfDescription(),Toast.LENGTH_SHORT).show();
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ConferenceActivity.class);
                        intent.putExtra("conf_name",conf.getConfName());
                        intent.putExtra("conf_id",conf.getConfId());
                        intent.putExtra("conf_chair",conf.getConfChair());
                        intent.putExtra("conf_date",conf.getConfDate());
                        intent.putExtra("conf_description",conf.getConfDescription());
                        intent.putExtra("conf_days",conf.getDays());
                        context.startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Conference conf = conferenceList.get(position);
                        Toast.makeText(getApplicationContext(),conf.getConfName(),Toast.LENGTH_SHORT).show();

                    }


                })
        );
    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            HttpURLConnection httpURLConnection;
            @Override
            protected String doInBackground(String... params) {
                StringBuilder result = new StringBuilder();

                try{
                URL url = new URL(conference_data_fetch_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();



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
    public void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            conferences = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<conferences.length();i++){
                JSONObject c = conferences.getJSONObject(i);


                int id = Integer.parseInt(c.getString(TAG_CID));
                String it=c.getString(TAG_CID);
                String topic = c.getString(TAG_TOPIC);
                String venue = c.getString(TAG_VENUE);
                String schedule = c.getString(TAG_SCHEDULE);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(c.getString(TAG_STARTDATE));
                String about = c.getString(TAG_ABOUT);
                String confchair = c.getString(TAG_CONFCHAIR);
                int days = Integer.parseInt(c.getString(TAG_DAYS));
                int fees_list = Integer.parseInt(c.getString(TAG_FEESLIST));
                int fees_part = Integer.parseInt(c.getString(TAG_FEESPART));
                String imageURL = c.getString(TAG_IMAGEURL);

                Conference conf=new Conference(id,topic,date,venue,confchair,days,fees_list,fees_part,about,schedule,imageURL);


                conferenceList.add(conf);
            }
            ListConfAdapter listConfAdapter = new ListConfAdapter(getApplicationContext(),conferenceList);
            recyclerView.setAdapter(listConfAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
