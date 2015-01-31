package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.util.FitApiWrapper;
import com.worldfit.worldfit.util.SimpleSharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class SyncFragment extends Fragment implements Runnable, ReceiverStepData {

    private static final String TAG = "SyncFragment";
    private static final String LAST_SYNC_TIMESTAMP_KEY = "LAST_SYNC_TIMESTAMP";
    private Activity mParentActivity;

    private Button mSyncButton;

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

        View v = inflater.inflate(R.layout.fragment_sync, container, false);


        mSyncButton = (Button) v.findViewById(R.id.syncBtn);
        configureViewHandlers();
        return v;
    }


    private void configureViewHandlers(){
        mSyncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FitApiWrapper.getInstance(mParentActivity).connect(SyncFragment.this);
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mParentActivity = activity;
    }


    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        long oneMonthBackFromNowTimestamp =  calendar.getTimeInMillis();

        long lastSyncTimestamp = SimpleSharedPreferences
                .getSimpleSharedPreference(mParentActivity)
                .read(LAST_SYNC_TIMESTAMP_KEY, oneMonthBackFromNowTimestamp);


        Log.d(TAG, "last sync: " + lastSyncTimestamp);
        FitApiWrapper.getInstance(mParentActivity).
                sendToReceiverUserStepsStartingFrom(lastSyncTimestamp, this);


    }

    @Override
    public void sendData(HashMap<String, Integer> stepData) {
        for(String key : stepData.keySet()){
            Log.d(TAG,  key + "> " + stepData.get(key));
        }

        Calendar current = Calendar.getInstance();
        current.set(current.get(Calendar.YEAR),
                current.get(Calendar.MONTH),
                current.get(Calendar.DATE),0,0,0);

        SimpleSharedPreferences.getSimpleSharedPreference(mParentActivity)
                .save(LAST_SYNC_TIMESTAMP_KEY, current.getTimeInMillis());
    }


}
