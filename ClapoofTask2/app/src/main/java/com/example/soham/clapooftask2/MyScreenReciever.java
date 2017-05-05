package com.example.soham.clapooftask2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Soham on 04-May-17.
 */

public class MyScreenReciever extends BroadcastReceiver {
    public static boolean screenOff;
    @Override
    public void onReceive(Context context, Intent intent){
        if((intent.getAction().equals(Intent.ACTION_SCREEN_OFF))){
            screenOff = true;
        }
        else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            screenOff = false;
        }

    }
}
