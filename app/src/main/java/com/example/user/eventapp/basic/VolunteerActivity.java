package com.example.user.eventapp.basic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.eventapp.R;
import com.example.user.eventapp.fragments.UserEnquiryFragment;


/**
 * Created by aadarsh.eddy on 05-07-2017.
 */

public class VolunteerActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    private static final String TAG_MY_CONFERENCES = "My Conferences";
    private static final String TAG_MESSAGES = "Messages";
    private static final String TAG_ACCOUNT = "Account Details";
    private static final String TAG_SETTINGS = "Settings";
    private static final String TAG_LOGOUT = "logout";
    public static String CURRENT_TAG = TAG_MY_CONFERENCES;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    String name;
    String category,uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_volunteer_titles);
        SharedPreferences sharedPreferences=getSharedPreferences("loggedIn info", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("name","");
        category=sharedPreferences.getString("category","");
        uid=sharedPreferences.getString("uid","");

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_MY_CONFERENCES;
            loadHomeFragment();
        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };


    }

    private void loadNavHeader() {

        txtName.setText(name);
        txtWebsite.setText(category);

    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu*/

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();


            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        /*Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }*/


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
              // MyConferencesFragment conferencesFragment=new MyConferencesFragment();
               // return conferencesFragment;
            case 1:
               // MessagesFragment messagesFragment = new MessagesFragment();
               // return messagesFragment;

            case 2:
               // VolunteerAccountFragment volunteerAccountFragment = new VolunteerAccountFragment();
               // return volunteerAccountFragment;
            case 3:
               // SettingsFragment settingsFragment = new SettingsFragment();
               // return settingsFragment;


            default:
                return new UserEnquiryFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_volunteer_conf:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_MY_CONFERENCES;
                        break;
                    case R.id.nav_volunteer_messages:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_MESSAGES;
                        break;
                    case R.id.nav_volunteer_account:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_ACCOUNT;
                        break;
                    case R.id.nav_volunteer_settings:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;

                    case R.id.nav_logout:
                        SharedPreferences sharedPreferences = getSharedPreferences("loggedIn info", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", "");
                        editor.apply();
                        startActivity(new Intent(VolunteerActivity.this, LoginActivity.class));
                        drawer.closeDrawers();
                        finish();
                        break;

                    default:
                        navItemIndex = 0;
                }
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

    }



    @Override    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState)
;
        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_MY_CONFERENCES;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }
    public String getUid(){
        return uid;
    }

}
