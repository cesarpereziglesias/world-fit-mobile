package com.worldfit.worldfit.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.plus.Plus;
import com.worldfit.worldfit.R;
import com.worldfit.worldfit.fragment.MainFragment;
import com.worldfit.worldfit.util.FitApiWrapper;
import com.worldfit.worldfit.util.SimpleSharedPreferences;
=======
import android.widget.ImageView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.fragment.MainFragment;
import com.worldfit.worldfit.model.User;
>>>>>>> b2a5d9d90f6369d701efccd3576fc3436e5b200c

import java.util.concurrent.Callable;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class MainDrawerActivity extends MaterialNavigationDrawer implements MaterialAccountListener, Runnable{

    private static User user;

    @Override
    public void init(Bundle savedInstanceState) {

        this.disableLearningPattern();

        FitApiWrapper.getInstance(this);
        FitApiWrapper.getInstance(this).connect(this);
        user = User.readSharedUser(this);

        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(), user.getName(), user.getMail() , R.drawable.ic_avatar_male, R.drawable.bamboo);
        user.setAvatar(this, (ImageView) findViewById(R.id.user_photo));

        this.addAccount(account);

        // create sections
        this.addSection(newSection(getString(R.string.resume), new MainFragment()));

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
    }
}
