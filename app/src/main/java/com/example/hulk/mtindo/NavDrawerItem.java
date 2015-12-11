package com.example.hulk.mtindo;

/**
 * Created by maureen on 12/9/15.
 */
/**
 * Class defines each item in navigation drawer
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    //Constructor
    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

