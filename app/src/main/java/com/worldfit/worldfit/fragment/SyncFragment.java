package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.worldfit.worldfit.R;
import com.worldfit.worldfit.util.FitApiWrapper;
import com.worldfit.worldfit.util.SimpleSharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class SyncFragment extends Fragment {

    private static final String TAG = "SyncFragment";
    private static final String LAST_SYNC_TIMESTAMP_KEY = "LAST_SYNC_TIMESTAMP";
    private Activity parentActivity;

    public static SyncFragment newInstance(String param1, String param2) {
        SyncFragment fragment = new SyncFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SyncFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sync, container, false);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity = activity;
    }


    private void onClickSyncButton(View view){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        long oneMonthBackFromNowTimestamp =  calendar.getTimeInMillis();

        long lastSyncTimestamp = SimpleSharedPreferences
                .getSimpleSharedPreference(parentActivity)
                .read(LAST_SYNC_TIMESTAMP_KEY, oneMonthBackFromNowTimestamp);
        HashMap<String, Integer> lastUserStepsData = FitApiWrapper.getInstance(parentActivity).
                retrieveUserStepsStartingFrom(lastSyncTimestamp);



    }




}
