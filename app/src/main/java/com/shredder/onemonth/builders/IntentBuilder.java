package com.shredder.onemonth.builders;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.shredder.onemonth.MainActivity;

public class IntentBuilder {

    public static final int INTENT_ACTION_QUICKRESPONSE_CODE = 565684;
    public static final String INTENT_ACTION_QUICKRESPONSE_YES = "com.shredder.onemonth.yes";
    public static final String INTENT_ACTION_QUICKRESPONSE_NO = "com.shredder.onemonth.no";

    private final Context context;

    public IntentBuilder(Context context) {
        this.context = context;
    }

    public PendingIntent createOpenAppIntent() {
        return PendingIntent.getActivity(context, INTENT_ACTION_QUICKRESPONSE_CODE, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @NonNull
    public PendingIntent createIntentYes() {
        Intent intent = new Intent(INTENT_ACTION_QUICKRESPONSE_YES);
        return createPendingIntent(intent);
    }

    @NonNull
    public PendingIntent createIntentNo() {
        Intent intent = new Intent(INTENT_ACTION_QUICKRESPONSE_NO);
        return createPendingIntent(intent);
    }

    private PendingIntent createPendingIntent(Intent yesIntent) {
        return PendingIntent.getBroadcast(context, INTENT_ACTION_QUICKRESPONSE_CODE, yesIntent, 0);
    }
}
