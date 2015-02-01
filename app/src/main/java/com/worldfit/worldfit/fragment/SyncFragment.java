package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.worldfit.worldfit.R;
import com.worldfit.worldfit.model.User;
import com.worldfit.worldfit.services.UsersManager;
import com.worldfit.worldfit.services.listeners.UsersManagerListener;
import com.worldfit.worldfit.util.FitApiWrapper;
import com.worldfit.worldfit.util.SimpleSharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class SyncFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, ReceiverStepData, UsersManagerListener {

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
                FitApiWrapper.getInstance()
                        .registerConnectionCallBack(SyncFragment.this)
                        .connect();
            }
        });
    }



    @Override
    public void onConnected(Bundle bundle) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        long oneMonthBackFromNowTimestamp =  calendar.getTimeInMillis();

        //long lastSyncTimestamp = SimpleSharedPreferences
        //       .getSimpleSharedPreference(mParentActivity)
        //        .read(LAST_SYNC_TIMESTAMP_KEY, oneMonthBackFromNowTimestamp);


        FitApiWrapper.getInstance().
                sendToReceiverUserStepsStartingFrom(oneMonthBackFromNowTimestamp, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mParentActivity = activity;
    }

    @Override
    public void sendData(HashMap<String, Integer> stepData) {
        UsersManager usersManager = new UsersManager();
        String stepsTag = com.worldfit.worldfit.model.Activity.TYPE_STEPS;
        List<com.worldfit.worldfit.model.Activity> activities =
                new ArrayList<com.worldfit.worldfit.model.Activity>();
        for(String date : stepData.keySet()){
            com.worldfit.worldfit.model.Activity activity =
                    new com.worldfit.worldfit.model.Activity(stepsTag, date, stepData.get(date));



            activities.add(activity);
        }
        User user = User.readSharedUser(mParentActivity);
        usersManager.insertActivity(user.getHash(), activities, this);

        Calendar current = Calendar.getInstance();
        current.set(current.get(Calendar.YEAR),
                current.get(Calendar.MONTH),
                current.get(Calendar.DATE),0,0,0);

        //SimpleSharedPreferences.getSimpleSharedPreference(mParentActivity)
        //        .save(LAST_SYNC_TIMESTAMP_KEY, current.getTimeInMillis());
    }


    @Override
    public void onGetUsers(List<User> users) {

    }

    @Override
    public void onCreateUser(String userHash) {

    }

    @Override
    public void onGetUserActivities(List<com.worldfit.worldfit.model.Activity> activities) {

    }

    @Override
    public void onInsertActivity() {

    }
}
