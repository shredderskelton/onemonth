package com.shredder.onemonth.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shredder.onemonth.build.AlarmBuilder;
import com.shredder.onemonth.build.NotificationBuilder;
import com.shredder.onemonth.progress.ProgressManager;

public class OnAlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (new ProgressManager(context).isStarted()) {
            new NotificationBuilder(context).create();
        } else {
            new AlarmBuilder(context).cancelAlarm();
        }
    }
}
