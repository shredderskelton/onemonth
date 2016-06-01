package com.shredder.onemonth.progress;

import android.content.Context;

import java.util.concurrent.TimeUnit;

public class ProgressManager {
    private final ProgressPreferences preferences;

    public ProgressManager(Context context) {
        preferences = new ProgressPreferences(context);
    }

    public boolean isStarted() {
        return preferences.getStart() != 0;
    }

    public void start() {
        preferences.setStart(System.currentTimeMillis());
        checkIn();
    }

    public void checkIn() {
        preferences.setLastCheckIn(System.currentTimeMillis());
    }

    public void stop() {
        preferences.clear();
    }

    public long daysSober() {
        long ONE_DAY_IN_MS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS); //gives 86400000
        long millisecondsSober = preferences.getLastCheckIn() - preferences.getStart();
        return millisecondsSober / ONE_DAY_IN_MS;
    }

    public boolean shouldRemind() {
        return isStarted() && daysSince(preferences.getLastCheckIn()) > 0;
    }

    private int daysSince(long time) {
        long ONE_DAY_IN_MS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS); //gives 86400000
        long millisecondsSober = System.currentTimeMillis() - time;
        return (int) (millisecondsSober / ONE_DAY_IN_MS);
    }
}