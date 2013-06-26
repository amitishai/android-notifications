package com.example.notificationChecker;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import java.util.LinkedList;

public class MyActivity extends Activity {
    private static int notificationId = 1001;
    private static LinkedList<Integer> ntfList = new LinkedList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification(MyActivity.this,new Bundle());
            }
        });
    }

    private void createNotification(Context context, Bundle bundle){
        Intent cit = new Intent(context, Bla.class);
        cit.putExtra("string", "please disappear");
        PendingIntent pendingContentIntent = PendingIntent.getActivity(context, 0, cit, PendingIntent.FLAG_ONE_SHOT);

        Intent dit = new Intent("ACTION_DELETE_NOTIFICATION");
        PendingIntent pendingDeleteIntent = PendingIntent.getBroadcast(context, 0, dit, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification ntf = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("body")
                .setContentText("body")
                .setNumber(ntfList.size() > 0 ? ntfList.size() + 1 : 0)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("R.string.push_notification_title")
                .setContentIntent(pendingContentIntent)
                .setDeleteIntent(pendingDeleteIntent)
                .build();


        NotificationManager ntfManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        ntfList.add(notificationId);
        ntfManager.notify(notificationId, ntf);
    }
}
