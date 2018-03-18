package com.system.tracking.trackingsystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.system.tracking.trackingsystem.enums.TrackingState;
import com.system.tracking.trackingsystem.services.DangeoursService;
import com.system.tracking.trackingsystem.services.SafeService;

public class RestartServiceBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Thread.sleep(3000);
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.tracking_state_file), Context.MODE_PRIVATE);
            Integer trackingState = sharedPreferences.getInt(context.getString(R.string.tracking_state_key), TrackingState.SAFE.getCode());

            if (trackingState == TrackingState.SAFE.getCode()) {
                context.startService(new Intent(context, SafeService.class));
            } else if (trackingState == TrackingState.DANGEOURS.getCode()) {
                context.startService(new Intent(context, DangeoursService.class));
            } else if (trackingState == TrackingState.TRANCKING.getCode()) {
                context.startService(new Intent(context, SafeService.class));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
