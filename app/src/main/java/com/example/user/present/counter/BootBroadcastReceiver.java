package com.example.user.present.counter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "BootBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        boolean bootCompleted;
        String action = intent.getAction();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            bootCompleted = Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(action);
        } else {
            bootCompleted = Intent.ACTION_BOOT_COMPLETED.equals(action);
        }
        if (!bootCompleted) {
            return;
        }

        Intent bootServiceIntent = new Intent(context.getApplicationContext(), BootService.class);
        // Android8.0よりバックグラウンドからバックグラウンドサービスを起動することはできない
        // startForegroundService()を利用することで新しいサービスを実行することができる
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(bootServiceIntent);
        } else {
            context.startService(bootServiceIntent);
        }

    }
}
