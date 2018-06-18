package com.main;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.Notifications.Facts.Broadcast_Receiver_Facts;
import com.Notifications.Pada.KannadaPada;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.Calendar;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();


        ///AlarmManager for Notification of Facts
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.MINUTE,02);
        calendar.set(Calendar.SECOND,00 );
        if(calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }
        Intent intent = new Intent((getApplicationContext()),Broadcast_Receiver_Facts.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        ///Ends Here

    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler{

        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            String bigText;
            String imgUrl;
            String targetUrl;

            if (data != null) {
                bigText = data.optString("bigText", null);
                imgUrl = data.optString("imgUrl",null);
                targetUrl = data.optString("targetUrl",null);
                Log.i(targetUrl,"TargetUrl");
                // if(!(bigText!=null && imgUrl!=null) && targetUrl!=null)
                if(targetUrl!=null)
                {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(targetUrl));
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }else if(bigText!=null && imgUrl!=null) {
                    Intent intent = new Intent(getApplicationContext(), KannadaPada.class);
                    intent.putExtra("bigText", bigText);
                    intent.putExtra("imgUrl", imgUrl);
                    startActivity(intent);
                }
                data.remove(bigText);//This is mandatory, because the Old JSON data will still be stored that causes error while opening newest notification
                data.remove(imgUrl);//
            }
        }
    }
}