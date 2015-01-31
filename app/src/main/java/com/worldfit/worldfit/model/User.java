package com.worldfit.worldfit.model;

import android.content.Context;

import com.worldfit.worldfit.util.SimpleSharedPreferences;

/**
 * Created by tonimc on 31/1/15.
 */
public class User {
    public final static String USER_HASH = "user_hash";
    public final static String USER_NAME = "user_name";
    public final static String USER_MAIL = "user_mail";

    private final static String USER_NODATA = "nodata";

    private String hash;
    private String name;
    private String mail;

    public User(String hash, String name, String mail) {
        this.hash = hash;
        this.name = name;
        this.mail = mail;
    }

    public String getHash(){
        return this.hash;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }


    public static User readSharedUser(Context context) {
        SimpleSharedPreferences sharedData = SimpleSharedPreferences.getSimpleSharedPreference(context);
        User user = new User(
                sharedData.read(USER_HASH, USER_NODATA),
                sharedData.read(USER_NAME, USER_NODATA),
                sharedData.read(USER_MAIL, USER_NODATA)
        );
        if(USER_NODATA.equals(user.hash)) {
            user.name = "Default";
            user.mail = "default@worldfit.com";
            user.hash = "default";
            user.save(context);
        }
        return user;
    }

    public void save(Context context) {
        SimpleSharedPreferences sharedData = SimpleSharedPreferences.getSimpleSharedPreference(context);
        sharedData.save(USER_HASH, this.hash);
        sharedData.save(USER_NAME, this.name);
        sharedData.save(USER_MAIL, this.mail);
    }

}
