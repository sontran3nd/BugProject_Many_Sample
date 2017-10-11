package com.example.admin.bugproject.Views.popover.WaveSwipe;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Admin on 9/22/2017.
 */

public class DisplayUtil {

    private DisplayUtil(){}
    /**
     *
     *
     * @param context {@link Context}
     * @return
     */
    public static boolean isOver600dp(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels / displayMetrics.density >= 600;
    }
}
