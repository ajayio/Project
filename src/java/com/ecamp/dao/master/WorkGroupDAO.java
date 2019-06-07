/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.dao.master;

import com.ecamp.bean.master.WorkGroupBean;
import com.ecamp.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class WorkGroupDAO {
    
      Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    WorkGroupBean workGroupBean=null;
    String errorMessage=null;
    
    public ArrayList<WorkGroupBean> viewUserGroup(short userGroupId) {
        ArrayList<WorkGroupBean> workGroupBeanList = new ArrayList<WorkGroupBean>();
        workGroupBean=null;
        
        try {
            connection=DBUtil.getDBConnection();           
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select userGroupId, userId, statusId  from ecamp_tbl_workgroup where userGroupId = ?");
            preparedStatement.setShort(1,userGroupId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){                
               WorkGroupBean workGroupBean = new WorkGroupBean();
                workGroupBean.setUserGroupId(resultSet.getShort("userGroupId"));
                workGroupBean.setUserId(resultSet.getInt("userId"));
                workGroupBean.setStatusId(resultSet.getByte("statusId"));               
                workGroupBeanList.add(workGroupBean);                
            }
                
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in WorkGroupDAO | View | Id:"+ e);
            }
        finally{
            errorMessage="Error in WorkGroupDAO | View | userGroupId | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return workGroupBeanList;
        }
    }
    
    public ArrayList<WorkGroupBean> viewUserGroup(int userId) {
        ArrayList<WorkGroupBean> workGroupBeanList = new ArrayList<WorkGroupBean>();
        workGroupBean=null;
        
        try {
            connection=DBUtil.getDBConnection();           
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select userGroupId, userId, statusId  from ecamp_tbl_workgroup where userId = ?");
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){                
               WorkGroupBean workGroupBean = new WorkGroupBean();
                workGroupBean.setUserGroupId(resultSet.getShort("userGroupId"));
                workGroupBean.setUserId(resultSet.getInt("userId"));
                workGroupBean.setStatusId(resultSet.getByte("statusId"));               
                workGroupBeanList.add(workGroupBean);                
            }
                
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in WorkGroupDAO | View | userId:"+ e);
            }
        finally{
            errorMessage="Error in WorkGroupDAO | View | workGroupId | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return workGroupBeanList;
        }
    }
    
    
    public static void main(String[] args) {
        
        //ArrayList<WorkGroupBean> workGroupBeanList = (ArrayList<WorkGroupBean>)new WorkGroupDAO().viewUserGroup(3);
        //System.out.println(workGroupBeanList.size());
    }
    
}
