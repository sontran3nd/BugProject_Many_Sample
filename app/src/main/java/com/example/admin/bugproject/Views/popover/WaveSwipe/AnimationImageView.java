package com.example.admin.bugproject.Views.popover.WaveSwipe;

import android.content.Context;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by Admin on 9/22/2017.
 */

public class AnimationImageView extends ImageView {

    /**
     * AnimationのStartとEnd時にListenerにアレする
     */
    private Animation.AnimationListener mListener;

    /**
     * コンストラクタ
     * {@inheritDoc}
     */
    public AnimationImageView(Context context) {
        super(context);
    }

    /**
     * {@link AnimationImageView#mListener} のセット
     *
     * @param listener {@link android.view.animation.Animation.AnimationListener}
     */
    public void setAnimationListener(Animation.AnimationListener listener) {
        mListener = listener;
    }

    /**
     * ViewのAnimationのStart時にセットされたListenerの {@link android.view.animation.Animation.AnimationListener#onAnimationStart(Animation)}
     * を呼ぶ
     */
    @Override public void onAnimationStart() {
        super.onAnimationStart();
        if (mListener != null) {
            mListener.onAnimationStart(getAnimation());
        }
    }

    /**
     * ViewのAnimationのEnd時にセットされたListenerの {@link android.view.animation.Animation.AnimationListener#onAnimationEnd(Animation)}
     * (Animation)} を呼ぶ
     */
    @Override public void onAnimationEnd() {
        super.onAnimationEnd();
        if (mListener != null) {
            mListener.onAnimationEnd(getAnimation());
        }
    }
}
