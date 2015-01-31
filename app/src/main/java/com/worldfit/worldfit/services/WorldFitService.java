package com.worldfit.worldfit.services;

import com.worldfit.worldfit.model.Activity;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.model.User;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface WorldFitService {

    @GET("/users")
    List<User> listUsers();

    @POST("/users")
    String createUser(@Body User user);

    @POST("/users/{hash}/activities")
    List<Activity> getUserActivities(@Path("hash") String hash);

    @POST("/users/{hash}/activities")
    String insertActivity(
            @Path("hash") String hash,
            @Body List<Activity> activities
    );

    @GET("/challenges")
    List<Challenge> listChallenges();

    @GET("/challenge/{id}")
    List<Challenge> getChallenge(@Path("id") int id);

}
