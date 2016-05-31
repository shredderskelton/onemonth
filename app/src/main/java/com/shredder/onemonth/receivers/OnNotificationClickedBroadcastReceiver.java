package com.shredder.onemonth.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shredder.onemonth.build.IntentBuilder;
import com.shredder.onemonth.build.NotificationBuilder;
import com.shredder.onemonth.progress.ProgressManager;

public class OnNotificationClickedBroadcastReceiver extends BroadcastReceiver {
    private ProgressManager progressManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        progressManager = new ProgressManager(context);
        if (intent.getAction().equalsIgnoreCase(IntentBuilder.INTENT_ACTION_QUICKRESPONSE_YES)) {
            progressManager.checkIn();
        }
        if (intent.getAction().equalsIgnoreCase(IntentBuilder.INTENT_ACTION_QUICKRESPONSE_NO)) {
            progressManager.start();
        }
        new NotificationBuilder(context).dismiss();
    }
}
