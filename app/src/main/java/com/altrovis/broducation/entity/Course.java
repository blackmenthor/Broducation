package com.altrovis.broducation.entity;

import java.util.ArrayList;

/**
 * Created by ANGGA on 4/6/2017.
 */

public class Course {

    private int id;
    private String teacher_name;
    private String course_name;
    private ArrayList<String> availability;

    public Course(int id,String teacher_name, String course_name, ArrayList<String> availability) {
        this.id = id;
        this.teacher_name = teacher_name;
        this.course_name = course_name;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public ArrayList<String> getAvailability() {
        return availability;
    }

    public void setAvailability(ArrayList<String> availability) {
        this.availability = availability;
    }
}
