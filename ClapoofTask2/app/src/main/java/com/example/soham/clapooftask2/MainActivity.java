package com.example.soham.clapooftask2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView screenOffDectection;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    private Handler handler;
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;
    private long timeInSeconds;
    private long timeInMinutes;
    private long timeInHours;
    private Runnable updateTimerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        screenOffDectection = (TextView)findViewById(R.id.screen_off_detection_text_view);
        broadcastReceiver = new MyScreenReciever();
        intentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(broadcastReceiver, intentFilter);

        handler = new Handler();

        updateTimerThread = new Runnable() {
            @Override
            public void run() {
                timeInMilliseconds = SystemClock.elapsedRealtime() - startTime;
                timeInSeconds = timeInMilliseconds/1000;
                screenOffDectection.setText(String.valueOf(timeInSeconds));
                handler.postDelayed(this, 0);
            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        if(MyScreenReciever.screenOff){
            Log.d("111","Screen off! Starting timer:");
            startTime = SystemClock.elapsedRealtime();
            Log.d("111","startTime is "+startTime);
            handler.postDelayed(updateTimerThread, 0);

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!MyScreenReciever.screenOff){
            Log.d("111","Screen on! Stopping timer...");
        }
        handler.removeCallbacks(updateTimerThread);
        new 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
