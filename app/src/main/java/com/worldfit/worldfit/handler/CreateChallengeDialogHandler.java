package com.worldfit.worldfit.handler;

import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.worldfit.worldfit.R;
import com.worldfit.worldfit.fragment.ChallengeListFragment;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.services.ChallengeManager;
import com.worldfit.worldfit.services.listeners.ChallengesManagerListener;

import java.util.List;

/**
 * Created by adoankim on 2/1/15.
 */
public class CreateChallengeDialogHandler extends MaterialDialog.ButtonCallback implements ChallengesManagerListener {
    private MaterialDialog mMaterialDialog;
    private ChallengeListFragment.ChallengeCreatedListener mChallengeCreatedListener;
    public CreateChallengeDialogHandler(ChallengeListFragment.ChallengeCreatedListener listener){
        mChallengeCreatedListener = listener;
    }

    @Override
    public void onPositive(MaterialDialog dialog){
        mMaterialDialog = dialog;


        String challengeDescription =
                ((TextView) mMaterialDialog.findViewById(R.id.challengeNameEditText))
                        .getText().toString();
        String beginDate =
                ((TextView) mMaterialDialog.findViewById(R.id.beginDateEditText))
                        .getText().toString();
        String endDate =
                ((TextView) mMaterialDialog.findViewById(R.id.endDateEditText))
                        .getText().toString();
        ChallengeManager challengeManager = new ChallengeManager();
        Challenge challenge = new Challenge(0, challengeDescription,
                "",
                com.worldfit.worldfit.model.Activity.TYPE_STEPS,
                beginDate,
                endDate);
        challengeManager.createChallenge(challenge, this);
    }

    @Override
    public void onNegative(MaterialDialog dialog){
        mMaterialDialog = dialog;
        hideAndCleanDialog();
    }

    @Override
    public void onGetChallenges(List<Challenge> challenges) {

    }

    @Override
    public void onCreateChallenge(String result) {
        hideAndCleanDialog();
        mChallengeCreatedListener.challengeCreated();
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onGetChallenge(Challenge challenge) {

    }

    private void hideAndCleanDialog(){
        ((TextView) mMaterialDialog.findViewById(R.id.challengeNameEditText)).setText("");
        ((TextView) mMaterialDialog.findViewById(R.id.beginDateEditText)).setText("");
        ((TextView) mMaterialDialog.findViewById(R.id.endDateEditText)).setText("");
        mMaterialDialog.hide();
    }
}
