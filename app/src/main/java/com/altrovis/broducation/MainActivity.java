package com.altrovis.broducation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.altrovis.broducation.entity.Course;
import com.altrovis.broducation.util.CourseAdapter;
import com.altrovis.broducation.util.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANGGA on 4/6/2017.
 */

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    public static final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedPreferences;

    private ProgressDialog progressDialog;
    private NavigationView navigationView;

    private RecyclerView recyclerView;
    private List<Course> courseList = new ArrayList<>();
    private CourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setUpNavigationView();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        TextView username = (TextView) findViewById(R.id.username_text);

        if(sharedPreferences.contains("username"))
            username.setText(sharedPreferences.getString("username",""));
        else
            username.setText("Unknown User");
        // setup recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CourseAdapter(courseList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // do it
                Intent i = new Intent(MainActivity.this,CourseDetailActivity.class);
                i.putExtra("course_id",courseList.get(position).getId());
                startActivity(i);
            }
        });

        refreshData();
    }

    public void refreshData(){
        Log.d(TAG,"Start Refreshing Data");
        courseList.clear();
        ArrayList<String> availability = new ArrayList<>();
        availability.add("SENIN JAM 2-4");
        availability.add("SELASA JAM 1-5");
        availability.add("RABU JAM 2-5");
        courseList.add(new Course(1,"ANGGA","MELUKIS",availability));
        courseList.add(new Course(2,"RAUFAN","MUSIK",availability));
        courseList.add(new Course(3,"SANGADJI","MENGGAMBAR",availability));
        courseList.add(new Course(4,"RICKI","MATEMATIKA",availability));
        courseList.add(new Course(5,"WISHNU","IPA",availability));
        courseList.add(new Course(6,"FU","IPS",availability));
        courseList.add(new Course(7,"DHABITH","FISIKA",availability));
        mAdapter.notifyDataSetChanged();

        Log.d(TAG,"Done Refreshing Data");
    }

    private void logout(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging you out...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
        editor.putBoolean("loggedIn",false);
        editor.commit();
        Log.d(TAG,"Logging out");
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
        progressDialog.dismiss();
        finish();
    }

    private void navigateToAbout(){
        Intent i = new Intent(MainActivity.this,AboutActivity.class);
        startActivity(i);
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
                    case R.id.nav_logout:
                        logout();
                        return true;
                    case R.id.nav_about_us:
                        navigateToAbout();
                        return true;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this, "settings clicked",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.nav_subscribed:
                        Intent i = new Intent(MainActivity.this, SubscribedCourseActivity.class);
                        startActivity(i);
                        return true;
                    default:
                        int navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                return true;
            }
        });
    }

}
