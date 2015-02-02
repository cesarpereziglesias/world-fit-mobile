package com.worldfit.worldfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.worldfit.worldfit.R;
import com.worldfit.worldfit.fragment.ChallengeListFragment;
import com.worldfit.worldfit.fragment.MainFragment;
import com.worldfit.worldfit.fragment.SyncFragment;
import com.worldfit.worldfit.model.Activity;
import com.worldfit.worldfit.model.User;
import com.worldfit.worldfit.services.UsersManager;
import com.worldfit.worldfit.services.listeners.UsersManagerListener;
import com.worldfit.worldfit.util.FitApiWrapper;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class MainDrawerActivity extends MaterialNavigationDrawer implements MaterialAccountListener, GoogleApiClient.ConnectionCallbacks, UsersManagerListener {

    private static final String TAG = MainDrawerActivity.class.getName();
    public static User mUser;
    private MaterialAccount mAccount;

    @Override
    public void init(Bundle savedInstanceState) {

        this.disableLearningPattern();

        this.mUser = User.readSharedUser(this);

        this.mAccount = new MaterialAccount(this.getResources(), mUser.getName(), mUser.getMail() , R.drawable.ic_avatar_default, R.drawable.bamboo);

        this.mUser.setAvatar(this, (ImageView) findViewById(R.id.user_photo));
        this.addAccount(mAccount);

        FitApiWrapper.getInstance()
                .attachContextActivity(this)
                .registerConnectionCallBack(this)
                .connect();

        // create sections
        this.addSection(newSection(getString(R.string.resume), R.drawable.ic_avatar_default, new MainFragment()));
        this.addSection(newSection(getString(R.string.challenges), R.drawable.ic_challenge, new ChallengeListFragment()));
        this.addSection(newSection(getString(R.string.synchronize), android.R.drawable.stat_notify_sync, new SyncFragment()));

        // create bottom section
        this.addBottomSection(newSection(getString(R.string.action_settings),R.drawable.ic_settings_black_24dp,new Intent(this,SettingsActivity.class)));

    }

    @Override
    public void onAccountOpening(MaterialAccount account) {

    }

    @Override
    public void onChangeAccount(MaterialAccount newAccount) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            FitApiWrapper.getInstance()
                    .attachContextActivity(this)
                    .registerConnectionCallBack(this)
                    .connect();
        }
    }


    @Override
    public void onGetUsers(List<User> users) {
        // Do nothing
    }

    @Override
    public void onCreateUser(String userHash) {
        Log.d("Email", userHash);
        mUser.setHash(userHash);
        mUser.saveInLocalShared(this);
        setUser();
    }

    @Override
    public void onGetUserActivities(List<Activity> activities) {

    }

    private void setUser(){
        mUser = User.readSharedUser(this);
        mAccount.setTitle(mUser.getName());
        mAccount.setSubTitle(mUser.getMail());
        notifyAccountDataChanged();

        mUser.setAvatar(this, (ImageView) findViewById(R.id.user_photo));
       this.setFragment(new MainFragment(), getString(R.string.resume));
    }

    @Override
    public void onInsertActivity() {
        // TODO
    }

    @Override
    public void onConnected(Bundle bundle) {
        String email = FitApiWrapper.getInstance().getSignedEmail();
        User testUser = User.readSharedUser(this);

        if(testUser.getMail().equals(email)){
            Log.d(TAG, "Registered user");
            return;
        }

        mUser = new User(email);
        UsersManager usersManager = new UsersManager();
        usersManager.createUser(mUser, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
