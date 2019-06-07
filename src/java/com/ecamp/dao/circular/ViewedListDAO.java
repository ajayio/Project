package com.ecamp.dao.circular;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import com.ecamp.util.DBUtil;
import com.ecamp.bean.master.UserBean;
import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.circular.ViewedListBean;
import java.text.ParseException;

public class ViewedListDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ViewedListBean viewedListBean = null;
    CircularBean circularBean = null;
    UserBean userBean = null;
    String errorMessage = null;
    
    public boolean addViewedList (ViewedListBean viewedListBean){
        
        int count;
        boolean result = false;
        
        try{
            
            connection = DBUtil.getDBConnection();
            String query = "insert into ecamp_tbl_circularviewedlist (circularId,userId,viewedTime,marked) values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, viewedListBean.getCircularBean().getCircularId());
            preparedStatement.setInt(2, viewedListBean.getUserBean().getUserId());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(viewedListBean.getViewedTime().getTime()));
            preparedStatement.setInt(4, viewedListBean.getMarked());
            count = preparedStatement.executeUpdate();
            
            if(count == 1){
                result = true;
            }
        }
        catch(Exception e){
            
            System.out.println("Error in ViewedListDAO | Add");
            
        }
        finally{
            
            errorMessage = "Error in ViewedListDAO | Add | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            
        }
        
        return result;
        
    }
    
    public ArrayList<ViewedListBean> viewViewedListByCircular(int circularId){
        
        viewedListBean = null;
        userBean = null;
        circularBean = null;
        ArrayList<ViewedListBean> viewedListBeanByCircularList= new ArrayList<ViewedListBean>();

        try{
            
            connection = DBUtil.getDBConnection();
            String query = "select userId, viewedTime, marked from ecamp_tbl_circularviewedlist where circularId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, circularId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                viewedListBean = new ViewedListBean();
                circularBean = new CircularBean();
                userBean = new UserBean();
                viewedListBean.setCircularBean(circularBean);
                circularBean.setCircularId(circularId);
                viewedListBean.setUserBean(userBean);
                userBean.setUserId(resultSet.getInt("userId"));
                viewedListBean.setViewedTime(resultSet.getTimestamp("viewedTime"));
                viewedListBean.setMarked(resultSet.getByte("marked"));
                viewedListBeanByCircularList.add(viewedListBean);
            }
            
        }
        catch(Exception e){
            System.out.println("Error in ViewedListDAO | ViewByCircular | View");
            
        }
        finally{
            
            errorMessage = "Error in ViewedListDAO | ViewByCircular | View | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            
        }
        
        return viewedListBeanByCircularList;
        
    }
    
    public ArrayList<ViewedListBean> viewViewedListByUser(int userId){
        
        viewedListBean = null;
        userBean = null;
        circularBean = null;
        ArrayList<ViewedListBean> viewedListBeanByUserList= new ArrayList<ViewedListBean>();
        
        try{
            
            connection = DBUtil.getDBConnection();
            String query = "select circularId, viewedTime, marked from ecamp_tbl_circularviewedlist where userId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                viewedListBean = new ViewedListBean();
                userBean = new UserBean();
                circularBean = new CircularBean();
                viewedListBean.setUserBean(userBean);
                userBean.setUserId(userId);
                viewedListBean.setCircularBean(circularBean);
                circularBean.setCircularId(resultSet.getInt("circularId"));
                viewedListBean.setViewedTime(resultSet.getTimestamp("viewedTime"));
                viewedListBean.setMarked(resultSet.getByte("marked"));
                viewedListBeanByUserList.add(viewedListBean);
            }
            
        }
        catch(Exception e){
            
            System.out.println("Error in ViewedListDAO | ViewByUser | View");
            
        }
        finally{
            
            errorMessage = "Error in ViewedListDAO | ViewByUser | View | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            
        }
        
        return viewedListBeanByUserList;
        
    }
    
//    public ArrayList<ViewedListBean> viewViewedListByDate( Date dateFrom, Date dateTo){
//        
//        viewedListBean = null;
//        userBean = null;
//        circularBean = null;
//        ArrayList<ViewedListBean> viewedListBeanByDateList= new ArrayList<ViewedListBean>();
//        
//        try{
//            
//            connection = DBUtil.getDBConnection();
//            String query = "select circularId, userId, viewedTime, marked from ecamp_tbl_circularviewedlist where viewedTime between date(?) and date(?)";
//            preparedStatement = connection.prepareStatement(query);
//           // System.out.println(dateFrom);
//           // System.out.println(dateTo);
//           System.out.println(new java.sql.Timestamp(dateFrom.getTime()));
//           System.out.println(new java.sql.Timestamp(dateTo.getTime()));
//            preparedStatement.setTimestamp(1, new java.sql.Timestamp(dateFrom.getTime()));
//            preparedStatement.setTimestamp(2, new java.sql.Timestamp(dateTo.getTime()));
//            
//            resultSet = preparedStatement.executeQuery();
//            
//            while(resultSet.next()){
//                viewedListBean = new ViewedListBean();
//                userBean = new UserBean();
//                circularBean = new CircularBean();
//                viewedListBean.setUserBean(userBean);
//                userBean.setUserId(resultSet.getInt("userId"));
//                viewedListBean.setCircularBean(circularBean);
//                circularBean.setCircularId(resultSet.getInt("circularId"));
//                viewedListBean.setViewedTime(resultSet.getTimestamp("viewedTime"));
//                viewedListBean.setMarked(resultSet.getByte("marked"));
//                viewedListBeanByDateList.add(viewedListBean);
//            }
//            
//        }
//        catch(Exception e){
//            
//            System.out.println("Error in ViewedListDAO | ViewByDate | View");
//        }
//        finally{
//            
//            errorMessage = "Error in ViewedListDAO | ViewByDate | View | DB Connection close";
//            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
//            
//        }
//        
//        return viewedListBeanByDateList;
//        
//    }
    
    public ArrayList<ViewedListBean> viewViewedListByMarked(byte marked){
        viewedListBean = null;
        userBean = null;
        circularBean = null;
        ArrayList<ViewedListBean> viewedListBeanMarkedList = new ArrayList<ViewedListBean>();
        try{
            connection = DBUtil.getDBConnection();
            String query = "select circularId, userId, viewedTime from ecamp_tbl_circularviewedlist where marked = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setByte(1, marked);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                viewedListBean = new ViewedListBean();
                circularBean = new CircularBean();
                userBean = new UserBean();                
                viewedListBean.setMarked(marked);
                viewedListBean.setCircularBean(circularBean);
                circularBean.setCircularId(resultSet.getInt("circularId"));
                viewedListBean.setUserBean(userBean);
                userBean.setUserId(resultSet.getInt("userId"));
                viewedListBean.setViewedTime(resultSet.getTimestamp("viewedTime"));
                viewedListBeanMarkedList.add(viewedListBean);
            }
            
        }
        catch(Exception e){
            
            System.out.println("Error in ViewedListDAO | ViewByMarked | View");
            
        }finally{
            
            errorMessage = "Error in ViewedListDAO | ViewByMarked | View | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            
        }
        
        return viewedListBeanMarkedList;
        
    }
    
    public ArrayList<ViewedListBean> viewViewedList () {
        viewedListBean = null;
        userBean = null;
        circularBean = null;
        ArrayList<ViewedListBean> viewedListBeanList = new ArrayList<ViewedListBean>();
        
        try{
            
            connection = DBUtil.getDBConnection();
            String query = "select circularId, userId, viewedTime, marked from ecamp_tbl_circularviewedlist";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                viewedListBean = new ViewedListBean();
                circularBean = new CircularBean();
                userBean = new UserBean();                
                viewedListBean.setCircularBean(circularBean);
                circularBean.setCircularId(resultSet.getInt("circularId"));
                viewedListBean.setUserBean(userBean);
                userBean.setUserId(resultSet.getInt("userId"));
                viewedListBean.setViewedTime(resultSet.getTimestamp("viewedTime"));
                viewedListBean.setMarked(resultSet.getByte("marked"));
                viewedListBeanList.add(viewedListBean);
            }
            
        }
        catch(Exception e){
            
            System.out.println("Error in ViewedListDAO | View");
            
        }
        finally{
            
            errorMessage = "Error in ViewedListDAO | View | DB Connection close";
            DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
            
        }
        
        return viewedListBeanList;
        
    }
    
    public static void main(String[] args) throws ParseException {
        
        ViewedListDAO viewedListDAO  = new ViewedListDAO();
        
        ViewedListBean viewedListBean = new ViewedListBean();
        UserBean userBean = new UserBean();
        CircularBean circularBean = new CircularBean();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/DD");
        ArrayList<ViewedListBean> viewedListBeanList= new ArrayList<ViewedListBean>();
        
        /* addViewList */
        
         viewedListBean.setCircularBean(circularBean);    
        circularBean.setCircularId(10); 
        userBean.setUserId(21003);
        viewedListBean.setUserBean(userBean);
        viewedListBean.setViewedTime(new java.util.Date());
        viewedListBean.setMarked((byte)1);
        viewedListDAO.addViewedList(viewedListBean); 
        
        
        
        /* View (Test by uncomment each view method call) */
        
        //viewedListBeanList = viewedListDAO.viewViewedListByCircular(130); //Block Test viewViewedListByCircular
        // viewedListBeanList = viewedListDAO.viewViewedListByUser(10203); //Block Test viewViewedListByUser
        // viewedListBeanList = viewedListDAO.viewViewedListByMarked((byte)0); //Block Test viewViewedListByMarked
        // viewedListBeanList = viewedListDAO.viewViewedList(); //Block Test viewViewedList
        
//        Date dateFrom = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse("2013-3-22 10:13:00");
//        Date dateTo = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse("2019-3-22 10:13:00");
        
//        viewedListBeanList = viewedListDAO.viewViewedListByDate(dateFrom, dateTo);
        
//        System.out.println(viewedListBeanList);
//        System.out.println(viewedListBeanList.size());
//        
//        for(int i=0;i<viewedListBeanList.size();i++){
//             System.out.println(viewedListBeanList.get(i).getCircularBean().getCircularId());
//             System.out.println(viewedListBeanList.get(i).getUserBean().getUserId());
//             System.out.println(viewedListBeanList.get(i).getViewedTime());
//             System.out.println(viewedListBeanList.get(i).getMarked());
//        } 
        
    }
}
