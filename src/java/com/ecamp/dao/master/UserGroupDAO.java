
package com.ecamp.dao.master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ecamp.util.DBUtil;
import com.ecamp.bean.master.UserGroupBean;
import com.ecamp.bean.master.CollegeBean;
import com.ecamp.bean.master.StatusBean;

public class UserGroupDAO {
    
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    UserGroupBean userGroupBean=null;
    String errorMessage=null;

    public boolean addUserGroup(UserGroupBean userGroupBean) {
        int count=0;
        boolean result=false;        
        try {
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            }            
            preparedStatement=connection.prepareStatement("insert into ecamp_tbl_usergroup(userGroupName,collegeId,statusId)values(?,?,?)");
            preparedStatement.setString(1,userGroupBean.getUserGroupName());
            preparedStatement.setByte(2,userGroupBean.getCollegeBean().getCollegeId());
            preparedStatement.setByte(3,userGroupBean.getStatusBean().getStatusId());
            count = preparedStatement.executeUpdate();
            if(count>0){
                result=true;
            }
        } catch (Exception e) {
            System.out.println("Error in UserGroupDAO | Add");
            }
        finally{
            errorMessage="Error in UserGroupDAO| Add | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return result;
        }
    }
    public String updateUserGroup(UserGroupBean userGroupBean)  {
        String result="FAILURE";
        
        try {
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement=connection.prepareStatement("update ecamp_tbl_usergroup set statusId = ? where userGroupName = ? AND collegeId = ?");
            preparedStatement.setByte(1,userGroupBean.getStatusBean().getStatusId());
            preparedStatement.setString(2,userGroupBean.getUserGroupName());
            preparedStatement.setByte(3,userGroupBean.getCollegeBean().getCollegeId());
            if(preparedStatement.executeUpdate()>0){
                result="SUCCESS";
                }
            } catch (Exception e) {
                System.out.println("Error in UserGroupDAO | Update");
                }
        finally{
            errorMessage="Error in UserGroupDAO | Update | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return result;
        }
    }
    public UserGroupBean viewUserGroup(short userGroupId) {
        userGroupBean=null;
        
        try {
            connection=DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select userGroupName,collegeId,statusId  from ecamp_tbl_userGroup where userGroupId = ?");
            preparedStatement.setShort(1,userGroupId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userGroupBean = new UserGroupBean();
                userGroupBean.setUserGroupId(userGroupId);
                userGroupBean.setUserGroupName(resultSet.getString("userGroupName"));
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId( resultSet.getByte("collegeId"));
                userGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                userGroupBean.setStatusBean(statusBean);
                }
                
        } catch (Exception e) {
            System.out.println("Error in UserGroupDAO | View | Id:"+ e);
            }
        finally{
            errorMessage="Error in UserGroupDAO | View | userGroupId | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return userGroupBean;
        }
    }
    public UserGroupBean viewUserGroup(String userGroupName){
        
        userGroupBean=null;
        try {
            connection=DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement=connection.prepareStatement("select userGroupId,collegeId,statusId  from ecamp_tbl_userGroup where userGroupName=?");
            preparedStatement.setString(1, userGroupName);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                userGroupBean = new UserGroupBean();
                userGroupBean.setUserGroupId(resultSet.getByte("userGroupId"));        
                userGroupBean.setUserGroupName(userGroupName);
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId( resultSet.getByte("collegeId"));
                userGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                userGroupBean.setStatusBean(statusBean);
            }
        } catch (Exception e) {
            System.out.println("Error in UserGroupDAO | View | Name");
            }
        finally{
            errorMessage="Error in UserGroupDAO | View | userGroupName | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return userGroupBean;
        }
    }

    public ArrayList<UserGroupBean>viewUserGroup(byte collegeId){
        userGroupBean = null;
        
        ArrayList<UserGroupBean> userGroupBeanList = new ArrayList<UserGroupBean>();
        try{ 
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select userGroupId, userGroupName, statusId from ecamp_tbl_userGroup where collegeId = ?");
            preparedStatement.setByte(1,collegeId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                userGroupBean = new UserGroupBean();                
                userGroupBean.setUserGroupId(resultSet.getByte("userGroupId"));
                userGroupBean.setUserGroupName(resultSet.getString("userGroupName"));
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId(collegeId);
                userGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                userGroupBean.setStatusBean(statusBean);
                userGroupBeanList.add(userGroupBean);
            }
        }
        catch(Exception e){
            System.out.println("Error in UserGroupDAO | View | COLLEGEID");
            }
        finally{
            errorMessage="Error in UserGroupDAO | View | COLLEGEID | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return userGroupBeanList;      
        }
        
    }
    public ArrayList<UserGroupBean>viewUserGroup(){
        userGroupBean = null;
        
        ArrayList<UserGroupBean> userGroupBeanList = new ArrayList<UserGroupBean>();
        try{ 
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select userGroupId, userGroupName, collegeId, statusId from ecamp_tbl_userGroup");
            
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                userGroupBean = new UserGroupBean();
                userGroupBean.setUserGroupId(resultSet.getByte("userGroupId"));
                userGroupBean.setUserGroupName(resultSet.getString("userGroupName"));
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId( resultSet.getByte("collegeId"));
                userGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                userGroupBean.setStatusBean(statusBean);
                userGroupBeanList.add(userGroupBean);
            }
        }
        catch(Exception e){
            System.out.println("Error in UserGroupDAO | View | All");
            }
        finally{
            errorMessage="Error in UserGroupDAO | View | All | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return userGroupBeanList;      
        }
    }
    
     public static void main(String[] args) {
        UserGroupBean userGroupBean = new UserGroupBean();
        userGroupBean.setUserGroupId((short)1);
        userGroupBean.setUserGroupName("Staff");
        CollegeBean collegeBean = new CollegeBean();
        collegeBean.setCollegeId((byte)4);
        userGroupBean.setCollegeBean(collegeBean);
        StatusBean statusBean = new StatusBean();
        statusBean.setStatusId((byte)2);
        userGroupBean.setStatusBean(statusBean);
        
//        UserGroupBean userGroupBean1 = new UserGroupDAO().viewUserGroup("admin");            
//        System.out.println(new UserGroupDAO().addUserGroup(userGroupBean));
//        System.out.println(new UserGroupDAO().updateUserGroup(userGroupBean));
//        System.out.println(new UserGroupDAO().viewUserGroup((short)4).getUserGroupName());
//        System.out.println(new UserGroupDAO().viewUserGroup("Staff").getStatusBean().getStatusId());
//        ArrayList<UserGroupBean> userGroupBeanList = new UserGroupDAO().viewUserGroup();
//        for(int i=0;i<userGroupBeanList.size();i++){
//            System.out.println(userGroupBeanList.get(i).getCollegeBean().getCollegeId());
//        }
//        System.out.println(new UserGroupDAO().addUserGroup(UserGroupBean));

    }
}
