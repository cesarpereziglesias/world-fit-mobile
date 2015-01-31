package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
public class ChallengeCreateFragment extends Fragment implements ChallengesManagerListener {

    private static final String TAG = ChallengeCreateFragment.class.getSimpleName();

    private Activity mParentActivity;

    private ListView mListChallenge;
    private ChallengeListAdapter mChallengeAdapter;
    private List<Challenge> mChallenges = new ArrayList<Challenge>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChallengeCreateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_challenge_create, container, false);
        return mainView;
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

    }
}
