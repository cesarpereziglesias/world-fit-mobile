package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.adapter.ChallengeListAdapter;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.services.ChallengeManager;
import com.worldfit.worldfit.services.listeners.ChallengesManagerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 */
public class ChallengeCreateFragment extends DialogFragment implements ChallengesManagerListener{

    private static final String TAG = ChallengeCreateFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_challenge_create, container, false);
        Log.d("CREATE!", "CREATE");
        return v;
    }

    private Activity mParentActivity;

    private TextView mChallengeTV;
    private TextView mBeginDateTV;
    private TextView mEndDateTV;
    private Button mCreateChallengeBtn;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChallengeCreateFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "Create dialog");
        return super.onCreateDialog(savedInstanceState);
    }


    private void  configureViews(View containerView){
        mChallengeTV = (TextView) containerView.findViewById(R.id.challengeNameEditText);
        mBeginDateTV = (TextView) containerView.findViewById(R.id.beginDateEditText);
        mEndDateTV = (TextView) containerView.findViewById(R.id.endDateEditText);
        mCreateChallengeBtn = (Button) containerView.findViewById(R.id.createChallengeBtn);
    }

    private void setupViewsListeners() {
        Log.d(TAG, "setup!");
        Log.d(TAG, "" + mCreateChallengeBtn);
        mCreateChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "create!");
                ChallengeManager challengeManager = new ChallengeManager();
                Challenge challenge = new Challenge(0, mChallengeTV.getText().toString(),
                        "",
                        com.worldfit.worldfit.model.Activity.TYPE_STEPS,
                        mBeginDateTV.getText().toString(),
                        mEndDateTV.getText().toString());
                challengeManager.createChallenge(challenge, ChallengeCreateFragment.this);
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mParentActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mParentActivity = null;
    }


    @Override
    public void onGetChallenges(List<Challenge> challenges) {
    }

    @Override
    public void onCreateChallenge(String result) {
        this.onDestroy();
    }
}
