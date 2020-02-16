package com.example.user.present.counter.count;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import androidx.annotation.Nullable;
import com.example.user.present.counter.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import timber.log.Timber;

public class RecordService extends Service {

    private static final String TAG = "RecordService";

    @Override
    public void onCreate() {
        Timber.d("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("onStartCommand");

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final int originalUnlockCount =
                sharedPreferences.getInt(getString(R.string.saved_unlock_count_key),
                        getResources().getInteger(R.integer.initial_unlock_count));
        final int currentUnlockCount = originalUnlockCount + 1;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.saved_unlock_count_key), currentUnlockCount);
        editor.commit();

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }
}
