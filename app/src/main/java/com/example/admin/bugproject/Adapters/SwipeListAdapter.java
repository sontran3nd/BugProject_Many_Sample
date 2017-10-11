package com.example.admin.bugproject.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.bugproject.R;
import com.example.admin.bugproject.Utils.GestureHandler;
import com.example.admin.bugproject.Utils.GestureListener;
import com.example.admin.bugproject.Utils.OnSwipeTouchListener;

import java.util.ArrayList;

/**
 * Created by Admin on 9/12/2017.
 */

public class SwipeListAdapter extends BaseAdapter {

    private ArrayList<String> dataList = new ArrayList<>();
    private Context context;

    public SwipeListAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.dataList = data;
    }

    @Override
    public int getCount() {
        return this.dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private TextView txtContent;
    private GestureDetector mDetector;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_swipe_list, viewGroup, false);
        }
        //mDetector = new GestureDetector(context, new MyGestureListener());
        txtContent = view.findViewById(R.id.item_swipe_list_txt_title);
        String item = (String) getItem(i);
        txtContent.setText(item);
        //txtContent.setOnTouchListener(touchListener);
        return view;
    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // pass the events to the gesture detector
            // a return value of true means the detector is handling it
            // a return value of false means the detector didn't recognize the event
            return mDetector.onTouchEvent(event);

        }
    };
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            // don't return false here or else none of the other gestures will work
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.disappear);
            txtContent.startAnimation(animation);
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            return true;
        }
    }
}
