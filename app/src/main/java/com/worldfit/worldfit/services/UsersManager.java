package com.worldfit.worldfit.services;

import android.os.AsyncTask;
import android.util.Log;

import com.worldfit.worldfit.model.Activity;
import com.worldfit.worldfit.model.User;
import com.worldfit.worldfit.services.listeners.UsersManagerListener;

import java.util.List;

public class UsersManager extends ServiceManager {

    private static final String TAG = UsersManager.class.getName();

    public void getUsersList(final UsersManagerListener listener) {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                List<User> users = mService.listUsers();

                return users;
            }

            @Override
            protected void onPostExecute(List<User> list) {

                super.onPostExecute(list);

                listener.onGetUsers(list);
            }
        }.execute();
    }

    public void createUser(final User user, final UsersManagerListener listener) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return  mService.createUser(user);
            }

            @Override
            protected void onPostExecute(String userHash) {

                super.onPostExecute(userHash);

                listener.onCreateUser(userHash);
            }
        }.execute();
    }

    public void getUserActivities(final String hash, final UsersManagerListener listener) {
        new AsyncTask<Void, Void, List<Activity>>() {
            @Override
            protected List<Activity> doInBackground(Void... params) {
                Log.d(TAG, hash);
                return mService.getUserActivities(hash);
            }

            @Override
            protected void onPostExecute(List<Activity> activities) {

                super.onPostExecute(activities);

                listener.onGetUserActivities(activities);
            }
        }.execute();
    }

    public void insertActivity(final String hash, final List<Activity> activities, final UsersManagerListener listener) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mService.insertActivity(hash, activities);
                // TODO: Check if everything was fine
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);

                listener.onInsertActivity();
            }
        }.execute();
    }
}
