package com.example.admin.bugproject.Views.popover;

/**
 * Created by Admin on 8/22/2017.
 */

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.admin.bugproject.R;

import java.util.ArrayList;
import java.util.List;

public class PopupOver extends PopupWindows implements OnDismissListener{

    private View mRootView;
    private ImageView mArrowUp;
    private ImageView mArrowDown;
    private LayoutInflater mInflater;
    private ViewGroup mTrack;
    private ScrollView mScroller;
    private OnActionItemClickListener mItemClickListener;
    private OnDismissListener mDismissListener;

    private List<PopupItem> popupItems= new ArrayList<PopupItem>();

    private boolean mDidAction;

    private int mChildPos;
    private int mInsertPos;
    private int mAnimStyle;
    private int mOrientation;
    private int rootWidth=0;

    public static final int ANIM_GROW_FROM_LEFT = 1;
    public static final int ANIM_GROW_FROM_RIGHT = 2;
    public static final int ANIM_GROW_FROM_CENTER = 3;
    public static final int ANIM_REFLECT = 4;
    public static final int ANIM_AUTO = 5;

    /**
     * Constructor.
     *
     * @param context Context
     */
    public PopupOver(Context context) {
        super(context);
        mInflater 	 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setRootViewId(R.layout.popupover);
        mAnimStyle 	= ANIM_AUTO;
        mChildPos 	= 0;
    }

    public PopupItem getActionItem(int index) {
        return popupItems.get(index);
    }

    public void setRootViewId(int id) {
        mRootView	= (ViewGroup) mInflater.inflate(id, null);
        mTrack 		= (ViewGroup) mRootView.findViewById(R.id.tracks);

        mArrowDown 	= (ImageView) mRootView.findViewById(R.id.arrow_down);
        mArrowUp 	= (ImageView) mRootView.findViewById(R.id.arrow_up);

        mScroller	= (ScrollView) mRootView.findViewById(R.id.scroller);

        //This was previously defined on show() method, moved here to prevent force close that occured
        //when tapping fastly on a view to show quickaction dialog.
        //Thanx to zammbi (github.com/zammbi)
        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(mRootView);
    }

    public void setAnimStyle(int mAnimStyle) {
        this.mAnimStyle = mAnimStyle;
    }

    public void setOnActionItemClickListener(OnActionItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void addPopupItem(PopupItem action) {
        popupItems.add(action);

        String title 	= action.getTitle();
        Drawable icon 	= action.getIcon();

        View container = mInflater.inflate(R.layout.popup_item, null);

        TextView text 	= (TextView) container.findViewById(R.id.popup_item_txt_title);

        if (title != null) {
            text.setText(title);
        } else {
            text.setVisibility(View.GONE);
        }

        final int pos 		=  mChildPos;
        final int actionId 	= action.getActionId();

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(PopupOver.this, pos, actionId);
                }

                if (!getActionItem(pos).isSticky()) {
                    mDidAction = true;

                    dismiss();
                }
            }
        });

        container.setFocusable(true);
        container.setClickable(true);

        if (mChildPos != 0) {
            View separator = mInflater.inflate(R.layout.verti_separator, null);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

            separator.setLayoutParams(params);
            separator.setPadding(5, 0, 5, 0);

            mTrack.addView(separator, mInsertPos);

            mInsertPos++;
        }

        mTrack.addView(container, mInsertPos);

        mChildPos++;
        mInsertPos++;
    }

    public void show (View anchor) {
        preShow();

        int xPos, yPos, arrowPos;

        mDidAction 			= false;

        int[] location 		= new int[2];

        anchor.getLocationOnScreen(location);

        Rect anchorRect 	= new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1]
                + anchor.getHeight());

        //mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        mRootView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int rootHeight 		= mRootView.getMeasuredHeight();

        if (rootWidth == 0) {
            rootWidth		= mRootView.getMeasuredWidth();
        }

        int screenWidth 	= mWindowManager.getDefaultDisplay().getWidth();
        int screenHeight	= mWindowManager.getDefaultDisplay().getHeight();

        //automatically get X coord of popup (top left)
        if ((anchorRect.left + rootWidth) > screenWidth) {
            xPos 		= anchorRect.left - (rootWidth-anchor.getWidth());
            xPos 		= (xPos < 0) ? 0 : xPos;

            arrowPos 	= anchorRect.centerX()-xPos;

        } else {
            if (anchor.getWidth() > rootWidth) {
                xPos = anchorRect.centerX() - (rootWidth/2);
            } else {
                xPos = anchorRect.left;
            }

            arrowPos = anchorRect.centerX()-xPos;
        }

        int dyTop			= anchorRect.top;
        int dyBottom		= screenHeight - anchorRect.bottom;

        boolean onTop		= (dyTop > dyBottom) ? true : false;

        if (onTop) {
            if (rootHeight > dyTop) {
                yPos 			= 15;
                ViewGroup.LayoutParams l 	= mScroller.getLayoutParams();
                l.height		= dyTop - anchor.getHeight();
            } else {
                yPos = anchorRect.top - rootHeight;
            }
        } else {
            yPos = anchorRect.bottom;

            if (rootHeight > dyBottom) {
                ViewGroup.LayoutParams l 	= mScroller.getLayoutParams();
                l.height		= dyBottom;
            }
        }

        showArrow(((onTop) ? R.id.arrow_down : R.id.arrow_up), arrowPos);

        setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);

        mWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);

        //add dim background
        View container;
        if (mWindow.getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                container = (View) mWindow.getContentView().getParent();
            } else {
                container = mWindow.getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) mWindow.getContentView().getParent().getParent();
            } else {
                container = (View) mWindow.getContentView().getParent();
            }
        }

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        wm.updateViewLayout(container, p);
    }

    private void showArrow(int whichArrow, int requestedX) {
        final View showArrow = (whichArrow == R.id.arrow_up) ? mArrowUp : mArrowDown;
        final View hideArrow = (whichArrow == R.id.arrow_up) ? mArrowDown : mArrowUp;

        final int arrowWidth = mArrowUp.getMeasuredWidth();

        showArrow.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)showArrow.getLayoutParams();

        param.leftMargin = requestedX - arrowWidth / 2;

        hideArrow.setVisibility(View.INVISIBLE);
    }

    private void setAnimationStyle(int screenWidth, int requestedX, boolean onTop) {
        int arrowPos = requestedX - mArrowUp.getMeasuredWidth()/2;

        switch (mAnimStyle) {
            case ANIM_GROW_FROM_LEFT:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
                break;

            case ANIM_GROW_FROM_RIGHT:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
                break;

            case ANIM_GROW_FROM_CENTER:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
                break;

            case ANIM_REFLECT:
                mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Reflect : R.style.Animations_PopDownMenu_Reflect);
                break;

            case ANIM_AUTO:
                if (arrowPos <= screenWidth/4) {
                    mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
                } else if (arrowPos > screenWidth/4 && arrowPos < 3 * (screenWidth/4)) {
                    mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Center : R.style.Animations_PopDownMenu_Center);
                } else {
                    mWindow.setAnimationStyle((onTop) ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
                }

                break;
        }
    }



    @Override
    public void onDismiss() {
        if (!mDidAction && mDismissListener != null) {
            mDismissListener.onDismiss();
        }
    }

    public void setOnDismissListener(PopupOver.OnDismissListener listener) {
        setOnDismissListener(this);
        mDismissListener = listener;
    }

    public interface OnActionItemClickListener {
        public abstract void onItemClick(PopupOver source, int pos, int actionId);
    }

    public interface OnDismissListener {
        public abstract void onDismiss();
    }
}
