
package com.ecamp.bean.circular;

import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserBean;

import java.util.Date;

public class CircularBean {
    
    private int circularId;
    private Date circularDate;
    private String title;
    private String subject;
    private Date validFrom;
    private Date validTo;    
    private UserBean userBean;
    private StatusBean statusBean;
    

    public int getCircularId() {
        return circularId;
    }

    public void setCircularId(int circularId) {
        this.circularId = circularId;
    }

    public Date getCircularDate() {
        return circularDate;
    }

    public void setCircularDate(Date circularDate) {
        this.circularDate = circularDate;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
    
     public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    public StatusBean getStatusBean() {
        return statusBean;
    }

    public void setStatusBean(StatusBean statusBean) {
        this.statusBean = statusBean;
    }
    
}
