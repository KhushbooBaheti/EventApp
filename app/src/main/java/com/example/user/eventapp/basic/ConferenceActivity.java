package com.example.user.eventapp.basic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.eventapp.R;
import com.example.user.eventapp.fragments.ConfDefaultFragment;
import com.example.user.eventapp.fragments.ConfDetailFragment;
import com.example.user.eventapp.fragments.PartRegFragment;
import com.example.user.eventapp.fragments.RegListFragment;
import com.example.user.eventapp.fragments.UserEnquiryFragment;

import java.util.Date;


public class ConferenceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG_RESULTS="result";
    private int TAG_CID ;
    private String TAG_TOPIC ="topic";
    private Date TAG_STARTDATE;
    private String TAG_VENUE ="venue";
    private String TAG_ABOUT ="about";
    private String TAG_SCHEDULE ="schedule";
    private String TAG_CONFCHAIR ="confchair";
    private int TAG_DAYS ;
    private Fragment fragment;
    private Bundle bundle;
    private Intent intent;
    private String uid;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(TAG_TOPIC);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //intent=new Intent();
        TAG_ABOUT=getIntent().getStringExtra("conf_description");
        TAG_CID=getIntent().getIntExtra("conf_id",0);
        TAG_TOPIC=getIntent().getStringExtra("conf_name");
        TAG_CONFCHAIR=getIntent().getStringExtra("conf_chair");
        TAG_DAYS=getIntent().getIntExtra("conf_days",0);
        TAG_STARTDATE=new Date(getIntent().getLongExtra("conf_date",0));


        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame1, new ConfDefaultFragment());
        tx.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.conference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences sharedPreferences = getSharedPreferences("loggedIn info", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", "");
            editor.apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about) {
            getSupportActionBar().setTitle(TAG_TOPIC);
            fragment = new ConfDetailFragment();
            bundle = new Bundle();
            bundle.putInt("Conf_id",TAG_CID);
            bundle.putString("Conf_name",TAG_TOPIC);
            bundle.putString("Conf_chair",TAG_CONFCHAIR);
            bundle.putString("Conf_desc",TAG_ABOUT);
            bundle.putInt("Conf_days",TAG_DAYS);
            long time = TAG_STARTDATE.getTime();
            bundle.putLong("Conf_date",time);
            fragment.setArguments(bundle);
        } else if (id == R.id.attendee_reg) {
            getSupportActionBar().setTitle(TAG_TOPIC);
            fragment = new RegListFragment();
            bundle = new Bundle();
            bundle.putInt("Conf_id",TAG_CID);
            fragment.setArguments(bundle);
        } else if (id == R.id.presenter_reg) {
            getSupportActionBar().setTitle(TAG_TOPIC);
            fragment = new PartRegFragment();
            bundle = new Bundle();
            bundle.putInt("Conf_id",TAG_CID);
            fragment.setArguments(bundle);
        }
        else if (id == R.id.enquiry) {
            getSupportActionBar().setTitle(TAG_TOPIC);
            fragment=new UserEnquiryFragment();
            bundle=new Bundle();
            bundle.putInt("Conf_id",TAG_CID);
            SharedPreferences shared=getSharedPreferences("loggedIn info", Context.MODE_PRIVATE);
            uid=shared.getString("uid","");
            bundle.putString("uid",uid);
            fragment.setArguments(bundle);

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int commit = fragmentManager.beginTransaction()
                    .replace(R.id.frame1, fragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}