package com.example.user.present.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MeasurementReceiver mMeasurementReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: start");
                startMeasurement();
            }
        });

        Button stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: stop");
                stopMeasurement();
            }
        });

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: reset");
                resetUnlockCount();
                updateUnlockCountView();
            }
        });

        registerMeasurementReceiver();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();

        updateUnlockCountView();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");

        unregisterMeasurementReceiver();

        super.onDestroy();
    }

    private void registerMeasurementReceiver() {
        mMeasurementReceiver = new MeasurementReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MeasurementReceiver.ACTION_START_MEASUREMENT);
        intentFilter.addAction(MeasurementReceiver.ACTION_STOP_MEASUREMENT);
        registerReceiver(mMeasurementReceiver, intentFilter);
    }

    private void unregisterMeasurementReceiver() {
        unregisterReceiver(mMeasurementReceiver);
    }

    private void startMeasurement() {
        Intent intent = new Intent(getApplicationContext(), MeasurementReceiver.class);
        intent.setAction(MeasurementReceiver.ACTION_START_MEASUREMENT);
        sendBroadcast(intent);
    }

    private void stopMeasurement() {
        Intent intent = new Intent(getApplicationContext(), MeasurementReceiver.class);
        intent.setAction(MeasurementReceiver.ACTION_STOP_MEASUREMENT);
        sendBroadcast(intent);
    }

    private void resetUnlockCount() {
        // TODO: SharedPreferencesへのアクセスを行うクラスに切り出した方がいい
        // TODO: SharedPreferencesの更新後に画面表示の更新処理も行う必要あり
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.saved_unlock_count_key),
                getResources().getInteger(R.integer.initial_unlock_count));
        editor.commit();
    }

    private void updateUnlockCountView() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        final int originalUnlockCount =
                sharedPreferences.getInt(getString(R.string.saved_unlock_count_key),
                        getResources().getInteger(R.integer.initial_unlock_count));

        TextView countTextView = findViewById(R.id.user_present_count);
        countTextView.setText("count = " + originalUnlockCount);
    }
}
