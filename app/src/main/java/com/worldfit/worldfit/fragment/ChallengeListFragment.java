package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.adapter.ChallengeListAdapter;
import com.worldfit.worldfit.model.Challenge;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 */
public class ChallengeListFragment extends Fragment {

    private static final String TAG = ChallengeListFragment.class.getSimpleName();

    private Activity mParentActivity;

    private ListView mListChallenge;
    private ListAdapter mChallengeAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChallengeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_challenge_list, container, false);

        this.mListChallenge = (ListView) mainView.findViewById(R.id.list_challenges);
        this.mChallengeAdapter = new ChallengeListAdapter(mParentActivity, getChallenges());
        setup();
        initEvents();
        return mainView;
    }

    private List<Challenge> getChallenges() {
        List<Challenge> challenges = new ArrayList<Challenge>();
        challenges.add(new Challenge(1, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(2, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(3, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(4, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(5, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(6, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(7, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        challenges.add(new Challenge(8, "Corre gordo", "", "steps", new Date(), new Date()));
        return challenges;
    }

    private void setup() {
        this.mListChallenge.setAdapter(this.mChallengeAdapter);
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


}
