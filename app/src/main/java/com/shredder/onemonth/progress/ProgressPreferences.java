package com.shredder.onemonth.progress;

import android.content.Context;

import com.shredder.onemonth.base.SharedPreferenceBase;

class ProgressPreferences extends SharedPreferenceBase {
    private static final String KEY_START = "START";
    private static final String KEY_CHECK_IN = "CHECK IN";

    protected ProgressPreferences(Context context) {
        super(context);
    }

    @Override
    protected String getFileName() {
        return "progress";
    }

    public void setStart(long time) {
        setLong(KEY_START, time);
    }

    public long getStart() {
        return getLong(KEY_START, 0);
    }

    public void setLastCheckIn(long time) {
        setLong(KEY_CHECK_IN, time);
    }

    public long getLastCheckIn() {
        return getLong(KEY_CHECK_IN, 0);
    }

    public void clear(){
        super.clearAllEntries();
    }
}
