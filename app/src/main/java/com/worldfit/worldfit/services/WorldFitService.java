package com.worldfit.worldfit.services;

import com.worldfit.worldfit.model.User;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface WorldFitService {

    @GET("/users")
    List<User> listUsers();

    @POST("/users")
    String createUser(@Body User user);
}
