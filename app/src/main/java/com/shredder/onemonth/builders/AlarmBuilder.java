package com.shredder.onemonth.builders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.shredder.onemonth.BuildConfig;
import com.shredder.onemonth.receivers.OnAlarmBroadcastReceiver;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class AlarmBuilder {
    private static final int ALARM_REQUEST_CODE = 45435;
    private final Context context;

    public AlarmBuilder(Context context) {
        this.context = context;
    }

    public void createAlarm() {
        Calendar calendar = createAlarmSchedule();
        long repeatingInterval = createRepeatingInterval();
        PendingIntent pendingIntent = createAlarmIntent();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), repeatingInterval, pendingIntent);
    }

    private long createRepeatingInterval() {
        if (BuildConfig.DEBUG) {
            return TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);
        }

        return AlarmManager.INTERVAL_DAY;
    }

    @NonNull
    private Calendar createAlarmSchedule() {
        if (BuildConfig.DEBUG) {
            return createDebugAlarmSchedule();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    @NonNull
    private Calendar createDebugAlarmSchedule() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        return calendar;
    }

    private PendingIntent createAlarmIntent() {
        Intent alarmIntent = new Intent(context, OnAlarmBroadcastReceiver.class);
        return PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(createAlarmIntent());
    }
}
