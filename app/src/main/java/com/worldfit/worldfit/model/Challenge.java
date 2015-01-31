package com.worldfit.worldfit.model;

import android.os.Bundle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tonimc on 31/1/15.
 */
public class Challenge {

    private static final String TAG = User.class.getSimpleName();

    public final static String CHALLENGE_ID = "id";
    public final static String CHALLENGE_NAME = "name";
    public final static String CHALLENGE_OWNER = "owner";
    public final static String CHALLENGE_TYPE = "challenge_type";
    public final static String CHALLENGE_DATE_INIT = "init";
    public final static String CHALLENGE_DATE_END = "end";

    private int id;
    private String name;
    private String owner;
    private String challenge_type;
    private Date init;
    private Date end;

    public Challenge() {}

    public Challenge(int id, String name, String owner, String type, Date init, Date end) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.challenge_type = type;
        this.init = init;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getChallenge_type() {
        return challenge_type;
    }

    public void setChallenge_type(String challenge_type) {
        this.challenge_type = challenge_type;
    }

    public Date getInit() {
        return init;
    }

    public void setInit(Date init) {
        this.init = init;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(this.id);
        sb.append(", ").append("name=").append(this.name);
        sb.append(", ").append("owner=").append(this.owner);
        sb.append(", ").append("challenge_type=").append(this.challenge_type);
        sb.append(", ").append("init=").append(dateFormat.format(this.init));
        sb.append(", ").append("end=").append(dateFormat.format(this.end));
        return sb.toString();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(CHALLENGE_ID, getId());
        bundle.putString(CHALLENGE_NAME, getName());
        bundle.putString(CHALLENGE_OWNER, getOwner());
        bundle.putString(CHALLENGE_TYPE, getChallenge_type());
        bundle.putLong(CHALLENGE_DATE_INIT, getInit().getTime());
        bundle.putLong(CHALLENGE_DATE_END, getEnd().getTime());
        return bundle;
    }

    public static Challenge fromBundle(Bundle bundle) {
        Challenge challenge = new Challenge();
        challenge.setId(bundle.getInt(CHALLENGE_ID));
        challenge.setName(bundle.getString(CHALLENGE_NAME));
        challenge.setOwner(bundle.getString(CHALLENGE_OWNER));
        challenge.setChallenge_type(bundle.getString(CHALLENGE_TYPE));
        challenge.setInit(new Date(bundle.getLong(CHALLENGE_DATE_INIT)));
        challenge.setEnd(new Date(bundle.getLong(CHALLENGE_DATE_INIT)));
        return challenge;
    }

}
