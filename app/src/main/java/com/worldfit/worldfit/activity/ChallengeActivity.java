package com.worldfit.worldfit.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.adapter.ResultListAdapter;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.model.Result;
import com.worldfit.worldfit.services.ChallengeManager;
import com.worldfit.worldfit.services.listeners.ChallengesManagerListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChallengeActivity extends ActionBarActivity implements ChallengesManagerListener {

    private static final String TAG = ChallengeActivity.class.getSimpleName();
    public static String BUNDLE_CHALLENGE = "challenge_bunlde";
    public static String ACTION_SHOW = "action_show";

    private Challenge mChallenge;

    private ListView mCalsification;
    private ResultListAdapter mResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        this.mChallenge = Challenge.fromBundle(getIntent().getBundleExtra(BUNDLE_CHALLENGE));
        Log.d(TAG, mChallenge.toString());
        ChallengeManager challengeManager = new ChallengeManager();
        challengeManager.getChallenge(this.mChallenge.getId(), this);

        initLayout();
    }

    private void initLayout() {
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        ((TextView)findViewById(R.id.challenge_name)).setText(this.mChallenge.getName());
        ((TextView)findViewById(R.id.challenge_init_date)).setText(this.mChallenge.getInit());
        ((TextView)findViewById(R.id.challenge_end_date)).setText(this.mChallenge.getEnd());
        this.mCalsification = (ListView)findViewById(R.id.participants);
        this.mResultAdapter = new ResultListAdapter(this, new ArrayList<Result>());

        this.mCalsification.setAdapter(this.mResultAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_suscribe) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetChallenges(List<Challenge> challenges) {
    }

    @Override
    public void onCreateChallenge(String result) {

    }

    @Override
    public void onGetChallenge(Challenge challenge) {
        this.mResultAdapter.clear();
        this.mResultAdapter.addAll(challenge.getResult());
        this.mResultAdapter.notifyDataSetChanged();
    }
}
