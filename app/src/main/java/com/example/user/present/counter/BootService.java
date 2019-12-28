package com.example.user.present.counter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.user.present.counter.count.UserPresentReceiver;

public class BootService extends Service {

    private static final String TAG = "BootService";

    private UserPresentReceiver mUserPresentReceiver = null;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();

        registerUserPresentReceiver();
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        Log.d(TAG, "startForegroundService: ");
        return super.startForegroundService(service);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        // TODO: Notificationののデザインガイド
        // https://material.io/design/platform-guidance/android-notifications.html#

        createNotificationChannel();
        startForeground(1, createNotification());

        // TODO: 通知のタップ アクションを設定する
//        https://developer.android.com/training/notify-user/build-notification?hl=ja#click

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        throw  new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();

        unregisterUserPresentReceiver();
    }

    private void registerUserPresentReceiver() {
        Log.d(TAG, "registerUserPresentReceiver: ");

        mUserPresentReceiver = new UserPresentReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mUserPresentReceiver, intentFilter);
    }

    private void unregisterUserPresentReceiver() {
        unregisterReceiver(mUserPresentReceiver);
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, "temp_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("temp title")
                .setContentText("temp text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        return notification;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            NotificationChannel channel =
                    new NotificationChannel("temp_id", name, NotificationManager.IMPORTANCE_LOW);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
