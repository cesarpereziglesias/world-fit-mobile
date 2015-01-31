package com.worldfit.worldfit.model;

import java.util.Date;

/**
 * Created by tonimc on 31/1/15.
 */
public class Challenge {

    private static final String TAG = User.class.getSimpleName();

    public final static String CHALLENGE_NAME = "name";
    public final static String CHALLENGE_OWNER = "owner";
    public final static String CHALLENGE_TYPE = "owner";
    public final static String CHALLENGE_DATE_INIT = "init";
    public final static String CHALLENGE_DATE_END = "end";

    private String name;
    private String owner;
    private String type;
    private Date init;
    Date end;

}
