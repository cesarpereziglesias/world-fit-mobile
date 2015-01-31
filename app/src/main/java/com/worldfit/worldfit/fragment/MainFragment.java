package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.worldfit.worldfit.R;
import com.worldfit.worldfit.model.User;
import com.worldfit.worldfit.services.UsersManager;
import com.worldfit.worldfit.services.listeners.UsersManagerListener;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements UsersManagerListener {

    private static final String TAG = MainFragment.class.getName();
    private Activity mParentActivity;
    private BarChart mBarChart;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UsersManager usersManager = new UsersManager();
        User user = User.readSharedUser(mParentActivity);
        usersManager.getUserActivities(user.getHash(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mParentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mBarChart = (BarChart) v.findViewById(R.id.chart);
        return v;
    }


    @Override
    public void onGetUsers(List<User> users) {

    }

    @Override
    public void onCreateUser(String userHash) {

    }

    @Override
    public void onGetUserActivities(List<com.worldfit.worldfit.model.Activity> activities) {
        this.showActivities(activities);
    }

    private void showActivities(List<com.worldfit.worldfit.model.Activity> activities) {


        ArrayList<BarEntry> valsComp1 = new ArrayList<BarEntry>();
        int i = 0;
        ArrayList<String> xVals = new ArrayList<String>();
        for(com.worldfit.worldfit.model.Activity activity: activities){
            String date = activity.getDate();
            valsComp1.add(new BarEntry(activity.getValue(), i++));
            xVals.add(date.substring(date.length()-2));
        }
        BarDataSet barDataSet = new BarDataSet(valsComp1, "Steps/Day");

        BarData barData = new BarData(xVals, barDataSet);

        mBarChart.setData(barData);

        mBarChart.setVisibility(View.VISIBLE);
        mBarChart.animateY(500);
    }

    @Override
    public void onInsertActivity() {

    }
}
