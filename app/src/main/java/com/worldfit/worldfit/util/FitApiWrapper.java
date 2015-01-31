package com.worldfit.worldfit.util;

import android.app.Activity;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.plus.Plus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by adoankim on 1/31/15.
 */
public class FitApiWrapper  {

    //Singleton provider
    private static FitApiWrapper mInstance;
    private Runnable mConnectedCallBack;

    public static FitApiWrapper getInstance(Activity contextActivity) {
        if(mInstance == null) {
            mInstance = new FitApiWrapper(contextActivity);
        }
        return mInstance;
    }


    private static final String TAG = "FitApiWrapper";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final int REQUEST_OAUTH = 1002;

    private Activity mContextActivity;
    private GoogleApiClient mGoogleApiClient;
    private boolean mAuthInProgress;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat(DATE_FORMAT);

    private FitApiWrapper(Activity contextActivity){
        this.mContextActivity = contextActivity;
        buildFitnessClient();
    }

    public HashMap<String, Integer> retrieveUserStepsStartingFrom(long beginStepsTimestamp){
        DataReadRequest dataReadRequest =  this.queryStepsFitData(beginStepsTimestamp);
        DataReadResult dataReadResult =
                Fitness.HistoryApi.readData(this.mGoogleApiClient, dataReadRequest).await(1, TimeUnit.MINUTES);



        HashMap<String, Integer> userStepsDataGroupedByDate = new LinkedHashMap<String, Integer>();
        //Each bucket represents a day
        for(Bucket bucket : dataReadResult.getBuckets()){
            try {
                //we have only one dataset and one datapoint in a day because of the aggregation in
                //fit query procedure
                DataSet dataset = bucket.getDataSets().get(0);
                DataPoint datapoint = dataset.getDataPoints().get(0);
                String date = mDateFormat.format(datapoint.getStartTime(TimeUnit.MILLISECONDS));
                int value = datapoint.getValue(Field.FIELD_STEPS).asInt();
                userStepsDataGroupedByDate.put(date, value);
            }catch (Exception e){
                // we pass if day doesn't have dataset
            }
        }

        return userStepsDataGroupedByDate;
    }


    private void extractGrouStepInfo(DataSet dataset){
        String date;

        for (DataPoint dataPoint : dataset.getDataPoints()){
        }
    }
    /**
     *  Build a {@link com.google.android.gms.common.api.GoogleApiClient} that will authenticate the user and allow the application
     *  to connect to Fitness APIs. The scopes included should match the scopes your app needs
     *  (see documentation for details). Authentication will occasionally fail intentionally,
     *  and in those cases, there will be a known resolution, which the OnConnectionFailedListener()
     *  can address. Examples of this include the user never having signed in before, or
     *  having multiple accounts on the device and needing to specify which account to use, etc.
     */
    private void buildFitnessClient() {
        // Create the Google API Client
        mGoogleApiClient = new GoogleApiClient.Builder(mContextActivity.getApplicationContext())
                .addApi(Fitness.API)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected(Bundle bundle) {
                                mConnectedCallBack.run();
                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                // If your connection to the sensor gets lost at some point,
                                // you'll be able to determine the reason and react to it here.
                                if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                    Log.i(TAG, "Connection lost.  Cause: Network Lost.");
                                } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                    Log.i(TAG, "Connection lost.  Reason: Service Disconnected");
                                }
                            }
                        }
                )
                .addOnConnectionFailedListener(
                        new GoogleApiClient.OnConnectionFailedListener() {
                            // Called whenever the API client fails to connect.
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.i(TAG, "Connection failed. Cause: " + result.toString());
                                if (!result.hasResolution()) {
                                    // Show the localized error dialog
                                    GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(),
                                            mContextActivity, 0).show();
                                    return;
                                }
                                // The failure has a resolution. Resolve it.
                                // Called typically when the app is not yet authorized, and an
                                // authorization dialog is displayed to the user.
                                if (!mAuthInProgress) {
                                    try {
                                        Log.i(TAG, "Attempting to resolve failed connection");
                                        mAuthInProgress = true;
                                        result.startResolutionForResult(mContextActivity,
                                                REQUEST_OAUTH);
                                    } catch (IntentSender.SendIntentException e) {
                                        Log.e(TAG,
                                                "Exception while starting resolution activity", e);
                                    }
                                }
                            }
                        }
                )
                .build();
    }


    /**
     * Return a {@link DataReadRequest} for all step count changes from now to the last given date.
     */
    private DataReadRequest queryStepsFitData(long LastSyncTimestamp) {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = LastSyncTimestamp;
        long startTime = cal.getTimeInMillis();

        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();

        return readRequest;
    }


    public void connect(Runnable callback) {
        this.mConnectedCallBack = callback;
        if(mGoogleApiClient.isConnected()){
            this.mConnectedCallBack.run();
        }else{
            this.mGoogleApiClient.connect();
        }
    }

    public String getSignedEmail() {
        return Plus.AccountApi.getAccountName(mGoogleApiClient);
    }
}
