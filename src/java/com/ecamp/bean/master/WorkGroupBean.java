/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.bean.master;

/**
 *
 * @author student
 */
public class WorkGroupBean {
    
    private short userGroupId;
    private int userId;
    private byte statusId;

    public short getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(short userGroupId) {
        this.userGroupId = userGroupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte getStatusId() {
        return statusId;
    }

    public void setStatusId(byte statusId) {
        this.statusId = statusId;
    }    
}
