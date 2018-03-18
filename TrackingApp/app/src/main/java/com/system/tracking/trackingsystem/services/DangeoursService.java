package com.system.tracking.trackingsystem.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.system.tracking.trackingsystem.R;
import com.system.tracking.trackingsystem.business.CommunicationDevice;
import com.system.tracking.trackingsystem.enums.Confirmation;
import com.system.tracking.trackingsystem.enums.TrackingState;

/**
 * Created by Milton on 18/03/2018.
 */

public class DangeoursService extends IntentService{

    public DangeoursService() {
        super("DangeoursService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        CommunicationDevice communicationDevice = new CommunicationDevice();
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.tracking_state_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int confirmation = communicationDevice.getConfirmation();
        if(confirmation == Confirmation.YES.getCode()) {
            // SEND NOTIFICATION FOR SERVER
            editor.putInt(getString(R.string.tracking_state_key), TrackingState.TRANCKING.getCode());
            editor.commit();
        } else if (confirmation == Confirmation.NO.getCode()){
            editor.putInt(getString(R.string.tracking_state_key), TrackingState.SAFE.getCode());
            editor.commit();
        }
    }
}
