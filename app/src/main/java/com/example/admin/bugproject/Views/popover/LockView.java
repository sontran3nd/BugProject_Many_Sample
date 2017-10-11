package com.example.admin.bugproject.Views.popover;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.admin.bugproject.R;

/**
 * Created by Admin on 9/14/2017.
 */

public class LockView extends RelativeLayout {
    private Context mContext;

    public LockView(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.look_view  , this, true);
    }

    public LockView(Context context, ViewGroup viewGroup) {
        super(context);
    }

    public LockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 0,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,
                PixelFormat.TRANSPARENT);
    }

    public int getSystemUiVisibility() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    }

    public void onLockScreen(){
        WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        windowManager.addView(LockView.this, getLayoutParams());
        setSystemUiVisibility(getSystemUiVisibility());
    }

    public void offLockScreen(){
        WindowManager windowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
        windowManager.removeView(this);
    }
}
