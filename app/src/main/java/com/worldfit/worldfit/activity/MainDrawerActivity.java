package com.worldfit.worldfit.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
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

import java.util.concurrent.Callable;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class MainDrawerActivity extends MaterialNavigationDrawer implements MaterialAccountListener, Runnable{

    private SimpleSharedPreferences data;

    @Override
    public void init(Bundle savedInstanceState) {

        data = SimpleSharedPreferences.getSimpleSharedPreference(this);
        // add accounts
        FitApiWrapper.getInstance(this);
        FitApiWrapper.getInstance(this).connect(this);
        MaterialAccount account = new MaterialAccount(this.getResources(),"Test","worldfit@gmail.com",R.drawable.photo, R.drawable.bamboo);
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
