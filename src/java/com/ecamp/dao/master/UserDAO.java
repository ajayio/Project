/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.dao.master;

import com.ecamp.bean.master.ContactBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserBean;
import com.ecamp.dao.circular.CircularDAO;
import com.ecamp.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Student
 */
public class UserDAO {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public boolean addUser(UserBean userBean){
        
        boolean result = false;
        int count = 0;
        try{
            connection = DBUtil.getDBConnection();
            if(connection !=null){
                preparedStatement = connection.prepareStatement("insert into user (userName, password, lastUpdated, contactId, statusId) values(?,?,?,?,?)");                
                preparedStatement.setString(1, userBean.getUserName());
                preparedStatement.setString(2, userBean.getPassword());
                preparedStatement.setTimestamp(3, new java.sql.Timestamp(userBean.getLastUpdated().getTime()));
                preparedStatement.setInt(4, userBean.getContactBean().getContactId());
                preparedStatement.setByte(5, userBean.getStatusBean().getStatusId());
                count = preparedStatement.executeUpdate();
                if(count == 1){
                    result = true;
                }                
            }
        }        
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            DBUtil.closeDBConnection(connection, preparedStatement, null, "");            
        }
        return result;
    }
    
    
    public UserBean viewByUserName(String userName){
        
        UserBean userBean = null;        
        try{
            connection = DBUtil.getDBConnection();
            if(connection !=null){
                preparedStatement = connection.prepareStatement("select userId, userName, password from ecamp_tbl_user where userName=?");                                
                preparedStatement.setString(1, userName);                    
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){ 
                    userBean = new UserBean();
                    userBean.setUserName(resultSet.getString("userName"));
                    userBean.setPassword(resultSet.getString("password"));      
                    userBean.setUserId(resultSet.getInt("userId"));
                }                
            }
        }        
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            DBUtil.closeDBConnection(connection, preparedStatement, null, "");            
        }
        
        return userBean;
    }
    
    
    
    public static void main(String[] args) {
        
        UserDAO userDAO = new UserDAO();
        UserBean userBean = new UserBean();
            userBean.setUserName("name");
            userBean.setPassword("password");
//            userBean.setLastUpdated(new java.util.Date());
//        ContactBean contactBean = new ContactBean();
//            contactBean.setContactId(3);
//            userBean.setContactBean(contactBean);
//        StatusBean statusBean = new StatusBean();
//            statusBean.setStatusId((byte)4);
//            userBean.setStatusBean(statusBean);        
//        System.out.println(userDAO.addUser(userBean));     
        //System.out.println(new UserDAO().viewByUserName("madhan").getUserId());       

        

        
        
        
    }
    
    
}
