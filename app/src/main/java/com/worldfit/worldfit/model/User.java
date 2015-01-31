package com.worldfit.worldfit.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.worldfit.worldfit.util.CircleTransform;
import com.worldfit.worldfit.util.MD5Util;
import com.worldfit.worldfit.util.SimpleSharedPreferences;

/**
 * Created by tonimc on 31/1/15.
 */
public class User {
    private final static String AVATAR_URL = "http://www.gravatar.com/avatar/";

    public final static String USER_HASH = "user_hash";
    public final static String USER_NAME = "user_name";
    public final static String USER_MAIL = "user_mail";

    private final static String USER_NODATA = "nodata";
    private static final String TAG = User.class.getSimpleName();

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
            user.mail = "tonimc@gmail.com";
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

    public void setAvatar(Context context, ImageView imageView) {
        String gravatarHash = MD5Util.md5Hex(this.mail);
        Log.d(TAG, "Download gravatar");
        Picasso.with(context).load(AVATAR_URL+gravatarHash+"?s=200&d=mm")
                .transform(new CircleTransform())
                .into(imageView);
    }
}
