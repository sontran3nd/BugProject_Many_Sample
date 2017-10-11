package com.example.admin.bugproject.Views.popover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Admin on 9/22/2017.
 */

public class DrawViews extends View {

    private static final int SHADOW_COLOR = 0xFF000000;

    public DrawViews(Context context) {
        super(context);
    }

    public DrawViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path pathA = new Path();
        Paint pathB = new Paint();
        pathB.setShadowLayer(10.0f, 0.0f, 2.0f, SHADOW_COLOR);
        pathA.moveTo(0, 100);
        canvas.drawPath(pathA, pathB);
        pathA.rewind();
    }
}
