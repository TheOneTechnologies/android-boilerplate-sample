package com.theonetech.android.domain.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.theonetech.android.R;
import com.theonetech.android.presentation.view.activity.HomeActivity;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationUtils {
    private NotificationManager notificationManager = null;

    private static String NOTIFICATION_CHANNEL_ID = "app_structure_channel";
    private static String NOTIFICATION_CHANNEL_NAME = "app_structure";

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendNotification(Context context) {


        notificationManager = context.getSystemService(NotificationManager.class);
        // Notification Channel for android Oreo 8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            channel.setShowBadge(false);
            notificationManager.createNotificationChannel(channel);
        }

        Intent notificationIntent = new Intent(context, HomeActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(context.getString(R.string.app_name))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Welcome to Demo Application"))
                .setContentText("Welcome to the AppStructure")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);
        notificationManager.notify(2, builder.build());
    }
}
