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

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.user.present.counter.count.UnlockReceiver;

import timber.log.Timber;

public class MeasurementService extends Service {

    private static final String TAG = "MeasurementService";

    private UnlockReceiver mUnlockReceiver = null;

    @Override
    public void onCreate() {
        Timber.d("onCreate");
        super.onCreate();

        registerUnlockReceiver();
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        Timber.d("startForegroundService");
        return super.startForegroundService(service);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("onStartCommand");
        // TODO: Notificationののデザインガイド
        // https://material.io/design/platform-guidance/android-notifications.html#

        createNotificationChannel();
        startForeground(1, createNotification());

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Timber.d("onBind");
        throw  new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();

        // TODO: ForegroundServiceにしているんだけどすぐにonDestroyがコールされてしまうなあ
        unregisterUnlockReceiver();
    }

    private void registerUnlockReceiver() {
        Timber.d("registerUnlockReceiver");

        mUnlockReceiver = new UnlockReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mUnlockReceiver, intentFilter);
    }

    private void unregisterUnlockReceiver() {
        unregisterReceiver(mUnlockReceiver);
    }

    private Notification createNotification() {
        // 標準のアクティビティのPendingIntentを設定する
        // https://developer.android.com/training/notify-user/navigation.html?hl=ja#DirectEntry
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, 0);

        Intent stopIntent = new Intent(this, MeasurementReceiver.class);
        stopIntent.setAction(MeasurementReceiver.ACTION_STOP_MEASUREMENT);
        PendingIntent stopPendingIntent =
                PendingIntent.getBroadcast(this, 0, stopIntent, 0);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.drawable.ic_launcher_foreground,
                getString(R.string.stop_measurement_from_notification),
                stopPendingIntent)
                .build();

        Notification notification = new NotificationCompat.Builder(this, "temp_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("boot service title")
                .setContentText("boot service content text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .addAction(action)
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
