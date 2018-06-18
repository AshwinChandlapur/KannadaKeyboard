package com.Notifications.Pada;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.blackcj.customkeyboard.R;

public class Broadcast_Receiver_Pada extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager1 = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);


        Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

        NotificationCompat.BigPictureStyle bigPicture = new NotificationCompat.BigPictureStyle();
        bigPicture.bigPicture(icon1);

        Intent mainActivity1 = new Intent((context),KannadaPada.class);
        mainActivity1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context,0,mainActivity1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent1)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Type Kannada ")
                .setContentText("Kannada Pada")
                .setStyle(bigPicture)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        notificationManager1.notify(200,builder1.build());
    }
}

