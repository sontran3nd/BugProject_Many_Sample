package com.example.admin.bugproject.Services;

/**
 * Created by Admin on 9/14/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.admin.bugproject.Activities.NewsActivity;
import com.example.admin.bugproject.Views.popover.LockView;

public class LockScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LockView lockView = new LockView(context);
//        If the screen was just turned on or it just booted up, start your Lock Activity
        if(action.equals(Intent.ACTION_SCREEN_OFF) || action.equals(Intent.ACTION_BOOT_COMPLETED) || action.equals(Intent.ACTION_SCREEN_ON))
        {
//            Intent i = new Intent(context, NewsActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
            if (action.equals(Intent.ACTION_SCREEN_OFF) ) {
                lockView.offLockScreen();
            }
            if (action.equals(Intent.ACTION_SCREEN_ON) ) {
                lockView.onLockScreen();
            }
        }
    }
}