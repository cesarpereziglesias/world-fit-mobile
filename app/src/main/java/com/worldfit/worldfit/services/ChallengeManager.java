package com.worldfit.worldfit.services;

import android.os.AsyncTask;
import android.util.Log;

import com.worldfit.worldfit.model.Activity;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.model.User;
import com.worldfit.worldfit.services.listeners.ChallengesManagerListener;
import com.worldfit.worldfit.services.listeners.UsersManagerListener;

import java.util.List;

public class ChallengeManager extends ServiceManager {

    private static final String TAG = ChallengeManager.class.getName();

    public void getChallengesList(final ChallengesManagerListener listener) {
        new AsyncTask<Void, Void, List<Challenge>>() {
            @Override
            protected List<Challenge> doInBackground(Void... params) {
                List<Challenge> challenges = mService.listChallenges();

                return challenges;
            }

            @Override
            protected void onPostExecute(List<Challenge> list) {

                super.onPostExecute(list);

                listener.onGetChallenges(list);
            }
        }.execute();
    }

    public void createChallenge(final Challenge challenge, final ChallengesManagerListener listener) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return  mService.createChallenge(challenge);
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                listener.onCreateChallenge(result);
            }
        }.execute();
    }

    public void getChallenge(final int id, final ChallengesManagerListener listener) {
        new AsyncTask<Void, Void, Challenge>() {
            @Override
            protected Challenge doInBackground(Void... params) {
                return mService.getChallenge(id);
            }

            @Override
            protected void onPostExecute(Challenge challenge) {

                super.onPostExecute(challenge);

                listener.onGetChallenge(challenge);
            }
        }.execute();
    }

    public void subscribeChallenge(final int challengeId, final String userHash, final ChallengesManagerListener listener) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mService.subscribeChallenge(challengeId, userHash);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);

                listener.onSubscribe();
            }
        }.execute();
    }


}
