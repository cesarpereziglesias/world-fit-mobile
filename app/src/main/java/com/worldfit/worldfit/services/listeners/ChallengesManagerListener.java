package com.worldfit.worldfit.services.listeners;

import com.worldfit.worldfit.model.Activity;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.model.User;

import java.util.List;

public interface ChallengesManagerListener {

    public void onGetChallenges(List<Challenge> challenges);

    public void onCreateChallenge(String result);

<<<<<<< HEAD
    public void onSubscribe();
=======
    void onGetChallenge(Challenge challenge);
>>>>>>> a2eb9e2ed767b6d7293acf5c3614e90a6fc30611
}
