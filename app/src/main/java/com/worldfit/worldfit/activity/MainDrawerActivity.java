package com.worldfit.worldfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.fragment.MainFragment;
import com.worldfit.worldfit.model.User;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class MainDrawerActivity extends MaterialNavigationDrawer implements MaterialAccountListener {

    private static User user;

    @Override
    public void init(Bundle savedInstanceState) {

        this.disableLearningPattern();

        user = User.readSharedUser(this);

        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(), user.getName(), user.getMail() , R.drawable.ic_avatar_default, R.drawable.bamboo);
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

}
