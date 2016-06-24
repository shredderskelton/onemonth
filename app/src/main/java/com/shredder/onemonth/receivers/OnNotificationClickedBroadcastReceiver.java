package com.shredder.onemonth.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shredder.onemonth.builders.AlarmBuilder;
import com.shredder.onemonth.builders.IntentBuilder;
import com.shredder.onemonth.builders.NotificationBuilder;
import com.shredder.onemonth.progress.ProgressManager;

public class OnNotificationClickedBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ProgressManager progressManager = new ProgressManager(context);
        AlarmBuilder alarmManager = new AlarmBuilder(context);
        if (intent.getAction().equalsIgnoreCase(IntentBuilder.INTENT_ACTION_QUICKRESPONSE_YES)) {
            progressManager.checkIn();
        }
        if (intent.getAction().equalsIgnoreCase(IntentBuilder.INTENT_ACTION_QUICKRESPONSE_NO)) {
            progressManager.start();
        }
        if (intent.getAction().equalsIgnoreCase(IntentBuilder.INTENT_ACTION_QUICKRESPONSE_IGNORE)) {
            //do nothing, just reset the alarm
        }
        alarmManager.resetAlarm();
        new NotificationBuilder(context).dismiss();
    }
}
