package com.example.user.present.counter.count;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import timber.log.Timber;

public class UserPresentReceiver extends BroadcastReceiver {

    private static final String TAG = "UserPresentReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.d("onReceive: " + intent.getAction());

        Intent serviceIntent = new Intent(context.getApplicationContext(), RecordService.class);
        // フォアグラウンドサービスを起動中であればバックグラウンドサービスを起動することができる
        context.startService(serviceIntent);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
