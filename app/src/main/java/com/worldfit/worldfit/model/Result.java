package com.worldfit.worldfit.model;

/**
 * Created by tonimc on 31/1/15.
 */
public class Result {

    public final static String RESULT_NAME = "result_name";
    public final static String RESULT_MAIL = "result_mail";

    private User user;

    //fake por si lo utiliza retrofit
    private String mail;
    private int value;

    public Result(String mail, int value) {
        this.user = new User(mail);
        this.value = value;
    }

    public String getMail() {
        return this.user.getMail();
    }

    public String getName() { return this.user.getName(); }

    public User getUser() { return this.user; }

    public void setMail(String mail) {
        this.user = new User(mail);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
