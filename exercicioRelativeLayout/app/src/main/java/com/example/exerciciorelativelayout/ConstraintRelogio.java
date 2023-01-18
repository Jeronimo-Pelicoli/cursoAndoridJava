package com.example.exerciciorelativelayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class ConstraintRelogio extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private Runnable mRunnable;

    private BroadcastReceiver mReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            mViewHolder.textBattery.setText(String.format("%s%%",level));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relogio);

        this.mViewHolder.textHourMinute = findViewById(R.id.text_time);
        this.mViewHolder.textSeconds = findViewById((R.id.text_seconds));
        this.mViewHolder.textBattery = findViewById((R.id.text_battery));

        this.registerReceiver(this.mReciver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

//        this.mViewHolder.textHourMinute.setOnClickListener( new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                startActivity( new Intent(getApplicationContext(), DetailsActivity.class));
//            }
//        });

        this.startClock();

    }

    private void startClock() {

        final Handler mHandler = new Handler();

        final Calendar calendar = Calendar.getInstance();
        this.mRunnable = new Runnable() {
            @Override
            public void run() {

                calendar.setTimeInMillis(System.currentTimeMillis());

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                int seconds = calendar.get(Calendar.SECOND);

                mViewHolder.textHourMinute.setText(String .format(Locale.getDefault(),"%02d:%02d", hour,minutes));
                mViewHolder.textSeconds.setText(String .format(Locale.getDefault(),"%02d",seconds));

                long now = SystemClock.elapsedRealtime();
                long next = 1000 + (1000 - (now % 1000));
                mHandler.postAtTime(mRunnable, next);
            }
        };
        this.mRunnable.run();
    }

    private static class ViewHolder {
        TextView textHourMinute;
        TextView textSeconds;
        TextView textBattery;
    }
}
