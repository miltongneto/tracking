package com.system.tracking.trackingsystem.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import com.system.tracking.trackingsystem.R;
import com.system.tracking.trackingsystem.business.CommunicationDevice;
import com.system.tracking.trackingsystem.enums.TrackingState;

/**
 * Created by Milton on 18/03/2018.
 */

public class SafeService extends IntentService {
    public SafeService() {
        super("SafeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        CommunicationDevice communicationDevice = new CommunicationDevice();
        int signal = communicationDevice.getSignal();
        double distance = Math.pow(10, ((27.55 - (20 * Math.log10(14250)) + signal) / 20));
        Log.i("SafeService", String.valueOf(distance));
        if (checkDistance(distance)) {
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.tracking_state_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.tracking_state_key), TrackingState.DANGEOURS.getCode());
            editor.commit();
            communicationDevice.getActiveAssault();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent restartService = new Intent();
        restartService.setAction("RestartService");
        sendBroadcast(restartService);
    }

    private boolean checkDistance(double distance) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.distance_config_file), Context.MODE_PRIVATE);
        int defaultValue = 0;
        int currentLimit = sharedPreferences.getInt(getString(R.string.distance_config_key), defaultValue);
        if (distance > currentLimit) return true;
        return false;
    }
}
