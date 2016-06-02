package com.shredder.onemonth.builders;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.shredder.onemonth.R;

public class NotificationBuilder {

    public static final int NOTIFICATION_ID = 325;
    private final Context context;
    private final NotificationManagerCompat notificationManager;

    public NotificationBuilder(Context context) {
        this.context = context;
        notificationManager = NotificationManagerCompat.from(context);
    }

    public void create() {
        IntentBuilder creator = new IntentBuilder(context);

        String yes = context.getString(R.string.yes);
        String no = context.getString(R.string.no);
        String notificationQuestion = context.getString(R.string.notification_question);
        String appName = context.getString(R.string.app_name);

        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(appName)
                .setContentText(notificationQuestion)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationQuestion))
                .setContentIntent(creator.createOpenAppIntent())
                .setTicker(appName).setWhen(0)
                .addAction(R.drawable.ic_done_black_24dp, yes, creator.createIntentYes())
                .addAction(R.drawable.ic_clear_black_24dp, no, creator.createIntentNo())
                .setSmallIcon(R.drawable.ic_local_drink_blue_600_48dp)
                .build();

        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void dismiss() {
        notificationManager.cancel(NOTIFICATION_ID);
    }
}