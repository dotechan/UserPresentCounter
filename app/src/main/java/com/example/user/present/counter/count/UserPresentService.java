package com.example.user.present.counter.count;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.user.present.counter.MainActivity;
import com.example.user.present.counter.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class UserPresentService extends Service {

    private static final String TAG = "UserPresentService";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        // 認証情報暗号化ストレージではなくデバイス暗号化ストレージに保存する
        // これは認証情報暗号化ストレージにアクセスできない状態でExceptionが発生し
        // フォアグラウンドサービスが停止されてしまうのを防ぐためでもある
        Context directBootContext = getApplicationContext().createDeviceProtectedStorageContext();
        SharedPreferences sharedPreferences = directBootContext.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final int originalUnlockCount =
                sharedPreferences.getInt(getString(R.string.saved_unlock_count_key),
                        getResources().getInteger(R.integer.initial_unlock_count));
        final int currentUnlockCount = originalUnlockCount + 1;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.saved_unlock_count_key), currentUnlockCount);
        editor.commit();

        createNotificationChannel();
        startForeground(1, createNotification());

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        Log.d(TAG, "startForegroundService: ");
        return super.startForegroundService(service);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, "temp_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("user present service title")
                .setContentText("user present service content text")
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
