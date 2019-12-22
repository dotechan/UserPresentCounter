package com.example.user.present.counter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

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
        // TODO: Notificationを作成してフォアグラウンドサービスとして実行する

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
}
