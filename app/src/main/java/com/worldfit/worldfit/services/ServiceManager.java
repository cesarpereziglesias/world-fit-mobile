package com.worldfit.worldfit.services;

import retrofit.RestAdapter;

abstract class ServiceManager {

    private static final String ENDPOINT = "http://ccbsoftware.com:6543";

    protected WorldFitService mService;

    public ServiceManager() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        mService = restAdapter.create(WorldFitService.class);
    }
}
