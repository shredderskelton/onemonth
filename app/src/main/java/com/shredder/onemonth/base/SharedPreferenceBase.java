package com.shredder.onemonth.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public abstract class SharedPreferenceBase {

    private Context context;
    private final SharedPreferences mPreferences;

    protected abstract String getFileName();

    protected SharedPreferenceBase(Context context) {
        this.context = context;
        mPreferences = context.getSharedPreferences(getFileName(), Activity.MODE_PRIVATE);
    }

    protected Context getContext() {
        return context;
    }

    protected String getString(String key, String defaultValue) {
        return mPreferences.getString(key, defaultValue);
    }

    protected void setString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    protected boolean getBoolean(String key, boolean defaultValue) {
        return mPreferences.getBoolean(key, defaultValue);
    }

    protected void setBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).apply();
    }

    protected float getFloat(String key, float defaultValue) {
        return mPreferences.getFloat(key, defaultValue);
    }

    protected void setFloat(String key, float value) {
        mPreferences.edit().putFloat(key, value).apply();
    }

    protected long getLong(String key, long defaultValue) {
        return mPreferences.getLong(key, defaultValue);
    }

    protected void setLong(String key, long value) {
        mPreferences.edit().putLong(key, value).apply();
    }

    protected boolean contains(String key) {
        return mPreferences.contains(key);
    }

    protected void clear(String key) {
        mPreferences.edit().remove(key).apply();
    }

    protected void clearAllEntries(){
        mPreferences.edit().clear().apply();
    }

    protected Map<String, ?> getAll(){
        return mPreferences.getAll();
    }
}