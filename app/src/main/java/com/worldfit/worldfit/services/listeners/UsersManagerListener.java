package com.worldfit.worldfit.services.listeners;

import com.worldfit.worldfit.model.User;

import java.util.List;

public interface UsersManagerListener {

    public void onGetUsers(List<User> users);

    public void onCreateUser(String userHash);
}
