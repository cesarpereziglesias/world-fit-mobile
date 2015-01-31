package com.worldfit.worldfit.model;

public class Activity {

    public final static String TYPE_STEPS = "steps";

    private String type;
    private String date;
    private int value;

    public Activity(String type, String date, int value) {
        this.type = type;
        this.date = date;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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