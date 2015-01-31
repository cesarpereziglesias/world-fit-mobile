package com.worldfit.worldfit.model;

public class Activity {

    public final static String TYPE_STEPS = "steps";

    private String activity_type;
    private String date;
    private int value;

    public Activity(String type, String date, int value) {
        this.activity_type = type;
        this.date = date;
        this.value = value;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}