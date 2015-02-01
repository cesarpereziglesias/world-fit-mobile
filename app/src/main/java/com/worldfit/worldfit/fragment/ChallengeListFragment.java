package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.worldfit.worldfit.R;
import com.worldfit.worldfit.adapter.ChallengeListAdapter;
import com.worldfit.worldfit.handler.CreateChallengeDialogHandler;
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
public class ChallengeListFragment extends Fragment implements ChallengesManagerListener {

    private static final String TAG = ChallengeListFragment.class.getSimpleName();

    private Activity mParentActivity;

    private ListView mListChallenge;
    private ChallengeListAdapter mChallengeAdapter;
    private List<Challenge> mChallenges = new ArrayList<Challenge>();
    private ChallengeManager mChallengeManager = new ChallengeManager();
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
        mChallengeManager.getChallengesList(this);
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
    public void onGetChallenges(List<Challenge> challenges) {
        this.mChallenges.clear();
        this.mChallenges.addAll(challenges);
        this.mChallengeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateChallenge(String result) {

    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onGetChallenge(Challenge challenge) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_challenge_list, menu);
    }

    private ChallengeCreatedListener mChallengeCreatedListener = new ChallengeCreatedListener() {
        @Override
        public void challengeCreated() {
            mChallengeManager.getChallengesList(ChallengeListFragment.this);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_subscribe:
                boolean wrapInScrollView = true;
                new MaterialDialog.Builder(mParentActivity)
                        .title(R.string.new_challenge)
                        .customView(R.layout.create_challenge, wrapInScrollView)
                        .positiveText(R.string.create)
                        .negativeText(android.R.string.cancel)
                        .positiveColorRes(R.color.material_deep_teal_500)
                        .negativeColorRes(R.color.material_deep_teal_200)
                        .callback(new CreateChallengeDialogHandler(mChallengeCreatedListener))
                        .build()
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public interface ChallengeCreatedListener {
        public void challengeCreated();
    }


}
