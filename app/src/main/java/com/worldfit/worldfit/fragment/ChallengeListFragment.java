package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.worldfit.worldfit.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 */
public class ChallengeListFragment extends ListFragment {

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
        //List<Challenge> challenges = getChallenges();
        //this.mChallengeAdapter = new ChallengeListAdapter(mParentActivity, challenges);
        setup();
        initEvents();
        return mainView;
    }

    private void setup() {
        //this.mListChallenge.setAdapter();
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
