package com.example.user.present.counter.count;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

public class UserPresentReceiver extends BroadcastReceiver {

    private static final String TAG = "UserPresentReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getAction());

        Intent serviceIntent = new Intent(context.getApplicationContext(), UserPresentService.class);
        // Android8.0よりバックグラウンドからバックグラウンドサービスを起動することはできない
        // startForegroundService()を利用することで新しいサービスを実行することができる
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(serviceIntent);
            // TODO: フォアグラウンドサービスからバックグラウンドサービスを起動することはできたのでUserPresentServiceはバックグラウンドサービスに修正する
            context.startService(serviceIntent);
        } else {
            context.startService(serviceIntent);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
