package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service {
    public MyService() {
    }
    // Идентификатор уведомления
    private static final int NOTIFY_ID = 101;

    // Идентификатор канала
    private static String CHANNEL_ID = "channel";


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    void createNotification(){


        NotificationManager nm = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);


            channel.setDescription("Channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            nm.createNotificationChannel(channel);
        }

        Intent it = new Intent(this, MainActivity2.class);
        PendingIntent pi = PendingIntent.getActivity(this, 1,it, PendingIntent.FLAG_MUTABLE);


        Intent it2 = new Intent(this, MainActivity3.class);
        PendingIntent pi2 = PendingIntent.getActivity(this, 1,it2, PendingIntent.FLAG_MUTABLE);

        Intent it3 = new Intent(this, MainActivity4.class);
        PendingIntent pi3 = PendingIntent.getActivity(this, 1,it3, PendingIntent.FLAG_MUTABLE);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Notification")
                    .setContentIntent(pi)
                    .setContentText("Мир, дружба, жвачка")
                    .addAction(R.drawable.ic_launcher_foreground, "3 активность", pi2)
                    .addAction(R.drawable.ic_launcher_foreground, "4 активность", pi3)
                    .setAutoCancel(true);



        nm.notify(1, builder.build());
    }


}