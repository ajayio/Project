
package com.ecamp.bean.master;

import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.CollegeBean;

public class UserGroupBean {
    private short userGroupId;
    private String userGroupName;
    private CollegeBean collegeBean;
    private StatusBean statusBean;

    public short getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(short userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
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
