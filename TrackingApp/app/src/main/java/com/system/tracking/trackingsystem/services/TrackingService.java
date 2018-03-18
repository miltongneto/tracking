package com.system.tracking.trackingsystem.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Milton on 18/03/2018.
 */

public class TrackingService extends IntentService {
    public TrackingService() {
        super("TrackingService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
