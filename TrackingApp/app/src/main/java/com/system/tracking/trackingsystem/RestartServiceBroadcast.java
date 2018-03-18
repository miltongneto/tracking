package com.system.tracking.trackingsystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RestartServiceBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Thread.sleep(300);
            context.startService(new Intent(context, NotifyAssaultService.class));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
