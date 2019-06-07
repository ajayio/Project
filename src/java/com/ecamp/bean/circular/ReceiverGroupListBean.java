package com.ecamp.bean.circular;

import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.bean.master.UserGroupBean;

public class ReceiverGroupListBean {
    
    private ReceiverGroupBean receiverGroupBean;
    private UserGroupBean userGroupBean;
    
    public ReceiverGroupBean getReceiverGroupBean() {
        return receiverGroupBean;
    }

    public void setReceiverGroupBean(ReceiverGroupBean receiverGroupBean) {
        this.receiverGroupBean = receiverGroupBean;
    }

    public UserGroupBean getUserGroupBean() {
        return userGroupBean;
    }

    public void setUserGroupBean(UserGroupBean userGroupBean) {
        this.userGroupBean = userGroupBean;
    }
    
}
