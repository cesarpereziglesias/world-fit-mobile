package com.worldfit.worldfit.activity;

import android.content.Intent;
import android.os.Bundle;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.fragment.MainFragment;
import com.worldfit.worldfit.util.SimpleSharedPreferences;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class MainDrawerActivity extends MaterialNavigationDrawer implements MaterialAccountListener {

    private SimpleSharedPreferences data;

    @Override
    public void init(Bundle savedInstanceState) {

        data = SimpleSharedPreferences.getSimpleSharedPreference(this);

        // add accounts
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

}
