package com.example.admin.bugproject.Views.popover;

import android.graphics.drawable.Drawable;

/**
 * Created by Admin on 8/22/2017.
 */

public class PopupItem {
    private int actionId = -1;
    private String title;
    private boolean selected;
    private Drawable icon;
    private boolean sticky;

    public PopupItem(int actionId, String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
        this.actionId = actionId;
    }

    public PopupItem() {
        this(-1, null, null);
    }

    public PopupItem(int actionId, String title) {
        this(actionId, title, null);
    }

    public PopupItem(Drawable icon) {
        this(-1, null, icon);
    }

    public PopupItem(int actionId, Drawable icon) {
        this(actionId, null, icon);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public boolean isSticky() {
        return sticky;
    }

}
