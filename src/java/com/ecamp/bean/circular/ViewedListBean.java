package com.ecamp.bean.circular;

import java.util.Date;

import com.ecamp.bean.master.UserBean;

public class ViewedListBean {
    
    private CircularBean circularBean;
    private UserBean userBean;
    private Date viewedTime;
    private byte marked;

    public CircularBean getCircularBean() {
        return circularBean;
    }

    public void setCircularBean(CircularBean circularBean) {
        this.circularBean = circularBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Date getViewedTime() {
        return viewedTime;
    }

    public void setViewedTime(Date viewedTime) {
        this.viewedTime = viewedTime;
    }

    public byte getMarked() {
        return marked;
    }

    public void setMarked(byte marked) {
        this.marked = marked;
    }
    
}
