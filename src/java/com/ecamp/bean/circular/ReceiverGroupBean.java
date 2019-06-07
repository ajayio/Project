package com.ecamp.bean.circular;

import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.CollegeBean;

public class ReceiverGroupBean {
    
    private short receiverGroupId;
    private String receiverGroupName;
    private CollegeBean collegeBean;
    private StatusBean statusBean;

    public short getReceiverGroupId() {
        return receiverGroupId;
    }

    public void setReceiverGroupId(short receiverGroupId) {
        this.receiverGroupId = receiverGroupId;
    }

    public String getReceiverGroupName() {
        return receiverGroupName;
    }

    public void setReceiverGroupName(String receiverGroupName) {
        this.receiverGroupName = receiverGroupName;
    }

    public CollegeBean getCollegeBean() {
        return collegeBean;
    }

    public void setCollegeBean(CollegeBean collegeBean) {
        this.collegeBean = collegeBean;
    }

    public StatusBean getStatusBean() {
        return statusBean;
    }

    public void setStatusBean(StatusBean statusBean) {
        this.statusBean = statusBean;
    }
    
}
