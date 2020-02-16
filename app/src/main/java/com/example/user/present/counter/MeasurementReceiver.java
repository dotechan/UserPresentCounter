package com.example.user.present.counter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import timber.log.Timber;

public class MeasurementReceiver extends BroadcastReceiver {

    private static final String TAG = "MeasurementReceiver";

    public static final String ACTION_START_MEASUREMENT = "com.example.user.present.counter.ACTION_START_MEASUREMENT";
    public static final String ACTION_STOP_MEASUREMENT = "com.example.user.present.counter.ACTION_STOP_MEASUREMENT";

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.d("onReceive");

        final String action = intent.getAction();
        Timber.d("action = " + action);
        if (action.equals(ACTION_START_MEASUREMENT)) {
            Intent startServiceIntent = new Intent(context.getApplicationContext(), MeasurementService.class);
            context.startService(startServiceIntent);
        } else if (action.equals(ACTION_STOP_MEASUREMENT)) {
            Intent stopServiceIntent = new Intent(context.getApplicationContext(), MeasurementService.class);
            context.stopService(stopServiceIntent);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
