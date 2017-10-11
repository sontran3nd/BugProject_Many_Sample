package com.example.admin.bugproject.Fragments.Dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.bugproject.R;

/**
 * Created by Admin on 9/13/2017.
 */

public class ShowSelectionDialogFragment extends DialogFragment{

    public ShowSelectionDialogFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_show_selection, container, false);

        this.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics metrics = new DisplayMetrics();

        WindowManager manager = getActivity().getWindowManager();

        if (manager != null) {
            manager.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            rootView.setMinimumWidth(width);
            rootView.setMinimumHeight(height);
        }

        setupViews(rootView);
        return rootView;
    }

    private ImageView imgArrow;
    private TextView txtRed, txtGreen, txtBlue, txtYellow, txtSample;
    private LinearLayout llLayoutCt;

    private void setupViews(View view)
    {
        imgArrow = view.findViewById(R.id.fragment_dialog_show_select_up_arrow);
        txtSample = view.findViewById(R.id.sampletxt);
        txtRed  = view.findViewById(R.id.idred);
        llLayoutCt = view.findViewById(R.id.llContent);
        txtRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location 		= new int[2];
                txtRed.getLocationInWindow(location);
                txtSample.setTextColor(Color.RED);
                ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)imgArrow.getLayoutParams();
                imgArrow.setX(location[0]);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.shaking);
                txtRed.startAnimation(animation);
            }
        });
        txtGreen  = view.findViewById(R.id.idgreen);
        txtGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location 		= new int[2];
                txtGreen.getLocationInWindow(location);
                txtSample.setTextColor(Color.GREEN);
                ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)imgArrow.getLayoutParams();
                imgArrow.setX(location[0]);
            }
        });
        txtBlue  = view.findViewById(R.id.idblue);
        txtBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location 		= new int[2];
                txtBlue.getLocationInWindow(location);
                txtSample.setTextColor(Color.BLUE);
                ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)imgArrow.getLayoutParams();
                imgArrow.setX(location[0]);
            }
        });
        txtYellow  = view.findViewById(R.id.idyellow);
        txtYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location 		= new int[2];
                txtYellow.getLocationInWindow(location);
                txtSample.setTextColor(Color.YELLOW);
                ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)imgArrow.getLayoutParams();
                imgArrow.setX(location[0]);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(50);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int[] location 		= new int[2];
                            txtGreen.getLocationInWindow(location);
                            txtSample.setTextColor(Color.GREEN);
                            ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)imgArrow.getLayoutParams();
                            imgArrow.setX(location[0]);
                            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.content_appear);
                            llLayoutCt.startAnimation(animation);
                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.out);
                }
            }
        }
        ).start();
    }
}
