package com.worldfit.worldfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.fragment.MainFragment;
import com.worldfit.worldfit.fragment.SyncFragment;
import com.worldfit.worldfit.model.User;
import com.worldfit.worldfit.services.UsersManager;
import com.worldfit.worldfit.services.listeners.UsersManagerListener;
import com.worldfit.worldfit.util.FitApiWrapper;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class MainDrawerActivity extends MaterialNavigationDrawer implements MaterialAccountListener, Runnable, UsersManagerListener {

    private static User mUser;

    @Override
    public void init(Bundle savedInstanceState) {

        this.disableLearningPattern();

        mUser = User.readSharedUser(this);

        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(), mUser.getName(), mUser.getMail() , R.drawable.ic_avatar_default, R.drawable.bamboo);
        mUser.setAvatar(this, (ImageView) findViewById(R.id.user_photo));

        FitApiWrapper.getInstance(this).connect(this);
        this.addAccount(account);

        // create sections
        this.addSection(newSection(getString(R.string.resume), new MainFragment()));
        this.addSection(newSection(getString(R.string.synchronize), new SyncFragment()));

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
            FitApiWrapper.getInstance(this).connect(this);
        }
    }

    @Override
    public void run() {
        String email = FitApiWrapper.getInstance(this).getSignedEmail();
        Log.d("Email", email);
        User user = new User(email);
        UsersManager usersManager = new UsersManager();
        usersManager.createUser(user, this);
    }

    @Override
    public void onGetUsers(List<User> users) {
        // Do nothing
    }

    @Override
    public void onCreateUser(String userHash) {
        Log.d("Email", userHash);
    }

    @Override
    public void onInsertActivity() {
        // TODO
    }
}
