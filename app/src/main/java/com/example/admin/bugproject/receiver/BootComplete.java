package com.example.admin.bugproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;

import com.example.admin.bugproject.Activities.NewsActivity;
import com.example.admin.bugproject.Views.popover.LockView;

/**
 * Created by Admin on 9/14/2017.
 */

public class BootComplete extends BroadcastReceiver {

    private Context mContext;
    private WindowManager windowManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        this.windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        String action = intent.getAction();

//        If the screen was just turned on or it just booted up, start your Lock Activity
//        if (action.equals(Intent.ACTION_SCREEN_OFF) || action.equals(Intent.ACTION_BOOT_COMPLETED)) {
//            Intent i = new Intent(context, NewsActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
//        }
        if (action.equals(Intent.ACTION_SCREEN_OFF) ) {
            System.out.println("XXSout off Screen");
            //offLockScreen();
        }
        if (action.equals(Intent.ACTION_SCREEN_ON) ) {
            System.out.println("XXSout on Screen");
            //onLockScreen();
        }
    }

//    public void onLockScreen(){
//        windowManager.addView(LockView.this, getLayoutParams());
//        setSystemUiVisibility(getSystemUiVisibility());
//    }
//
//    public void offLockScreen(){
//        windowManager.removeView(this);
//    }
}
