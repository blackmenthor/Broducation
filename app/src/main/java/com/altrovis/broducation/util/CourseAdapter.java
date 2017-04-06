package com.altrovis.broducation.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.altrovis.broducation.R;
import com.altrovis.broducation.entity.Course;

import java.util.List;

/**
 * Created by ANGGA on 4/6/2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private List<Course> courseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView teacher_name, course_name, availability;

        public MyViewHolder(View view) {
            super(view);
            teacher_name = (TextView) view.findViewById(R.id.teacher_name);
            course_name = (TextView) view.findViewById(R.id.course_name);
            availability = (TextView) view.findViewById(R.id.availability);
        }
    }

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.teacher_name.setText(course.getTeacher_name());
        holder.course_name.setText(course.getCourse_name());
        String availability = "";
        for(int i=0; i<course.getAvailability().size(); i++)
            availability+=course.getAvailability().get(i)+"\n";
        holder.availability.setText(availability);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

}
