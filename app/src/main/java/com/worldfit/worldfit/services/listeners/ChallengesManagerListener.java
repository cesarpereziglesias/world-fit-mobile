package com.worldfit.worldfit.services.listeners;

import com.worldfit.worldfit.model.Activity;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.model.User;

import java.util.List;

public interface ChallengesManagerListener {

    public void onGetChallenges(List<Challenge> challenges);

    public void onCreateChallenge(String result);

    public void onSubscribe();
    void onGetChallenge(Challenge challenge);
}
