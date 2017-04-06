package com.altrovis.broducation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class SubscribedCourseActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_subscribed_course);

        // Always cast your custom Toolbar here, and set it as the ActionBar.
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayHomeAsUpEnabled(true);

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
                Toast.makeText(SubscribedCourseActivity.this, courseList.get(position).getCourse_name() +" BY "+courseList.get(position).getTeacher_name(), Toast.LENGTH_SHORT).show();
            }
        });

        refreshData();
    }

    public void refreshData(){
        Log.d(TAG,"Start Refreshing Data");
        courseList.clear();
        ArrayList<String> availability = new ArrayList<>();
        availability.add("SENIN JAM 2-4");
        courseList.add(new Course(1,"ANGGA","MELUKIS",availability));
        ArrayList<String> availability2 = new ArrayList<>();
        availability2.add("SELASA JAM 1-5");
        courseList.add(new Course(2,"RAUFAN","MUSIK",availability2));
        ArrayList<String> availability3 = new ArrayList<>();
        availability3.add("RABU JAM 2-5");
        courseList.add(new Course(3,"SANGADJI","MENGGAMBAR",availability3));
        mAdapter.notifyDataSetChanged();

        Log.d(TAG,"Done Refreshing Data");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
