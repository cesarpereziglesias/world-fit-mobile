package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.adapter.ChallengeListAdapter;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.services.ChallengeManager;
import com.worldfit.worldfit.services.listeners.ChallengesManagerListener;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 */
public class ChallengeListFragment extends Fragment implements ChallengesManagerListener {

    private static final String TAG = ChallengeListFragment.class.getSimpleName();

    private Activity mParentActivity;

    private ListView mListChallenge;
    private ChallengeListAdapter mChallengeAdapter;
    private List<Challenge> mChallenges = new ArrayList<Challenge>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChallengeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_challenge_list, container, false);

        this.mListChallenge = (ListView) mainView.findViewById(R.id.list_challenges);
        this.mChallengeAdapter = new ChallengeListAdapter(mParentActivity, mChallenges);
        setup();
        initEvents();
        return mainView;
    }


    private void setup() {
        this.mListChallenge.setAdapter(this.mChallengeAdapter);
        ChallengeManager challengeManager = new ChallengeManager();
        challengeManager.getChallengesList(this);
    }

    private void initEvents() {

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onGetChallenges(List<Challenge> challenges) {
        this.mChallenges.clear();
        this.mChallenges.addAll(challenges);
        this.mChallengeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateChallenge(String result) {

    }
}
