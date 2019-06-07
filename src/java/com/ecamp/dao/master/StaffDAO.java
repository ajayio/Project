package com.ecamp.dao.master;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ecamp.bean.master.StaffBean;

import com.ecamp.bean.master.UserBean;
import com.ecamp.util.DBUtil;
import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.master.DepartmentBean;
import com.ecamp.bean.master.StatusBean;

import com.ecamp.util.DBUtil;
public class StaffDAO {
    StaffBean staffBean = null;
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;
    String errorMessage = null;
    public boolean addStaff(StaffBean staffBean)  {
        int count=0;
        boolean result=false;
        try {
            connection=DBUtil.getDBConnection();	    	
            preparedStatement=connection.prepareStatement("insert into ecamp_tbl_staff(userId,staffName,departmentId,statusId) values(?,?,?,?)");
            preparedStatement.setInt(1,staffBean.getUserBean().getUserId());
            preparedStatement.setString(2,staffBean.getStaffName());
            preparedStatement.setByte(3,staffBean.getDepartmentBean().getDepartmentId());
            preparedStatement.setByte(4,staffBean.getStatusBean().getStatusId());
            count=preparedStatement.executeUpdate();
            if(count>0){
                    result=true;
            }
            System.out.println("Count "+ count);
        } catch (Exception e) {
            System.out.println("Error in StaffDAO | Add");
	  }
        finally{
            errorMessage="Error in StaffDAO| Add | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
            return result;
	 }
    }
    public String updateStatus(StaffBean staffBean) {
        String result="FAILURE";
        try {
            connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("update ecamp_tbl_staff set statusId  = ? where staffName = ?");
            //System.out.println(staffBean.getStatusBean().getStatusId());
            //System.out.println(staffBean.getStaffName());
            preparedStatement.setByte(1,staffBean.getStatusBean().getStatusId());
            preparedStatement.setString(2,staffBean.getStaffName());
            if(preparedStatement.executeUpdate()>0){
                result="SUCCESS";
            }
 	}
        catch (Exception e) {
            System.out.println("Error in StaffDAO | UpdateStatus");
        }
 	finally{
            errorMessage="Error in StaffDAO| UpdateStatus | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
            return result;
 	}
    }
	
	public String updateDepartment(StaffBean staffBean) {
		String result="FAILURE";
 		try {
 			connection=DBUtil.getDBConnection();
 			preparedStatement=connection.prepareStatement("update ecamp_tbl_staff set departmentId = ? where staffName = ?");
 			preparedStatement.setInt(1,staffBean.getDepartmentBean().getDepartmentId());
 			preparedStatement.setString(2,staffBean.getStaffName());
                        
 			if(preparedStatement.executeUpdate()>0){
 				result="SUCCESS";
 			}
 		} catch (Exception e) {
 			System.out.println("Error in StaffDAO | UpdateDepartment");
 			}
 		finally{
 			errorMessage="Error in StaffDAO| UpdateDepartment | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return result;
 			}
	}


	public StaffBean viewStaffById(int userId)  {
            staffBean=null;
            try {
                connection=DBUtil.getDBConnection();
                preparedStatement=connection.prepareStatement("select staffName, departmentId, statusId from ecamp_tbl_staff where userId=?");
 		preparedStatement.setInt(1,userId);
 		resultSet=preparedStatement.executeQuery();
 		if(resultSet.next()){
                    staffBean=new StaffBean();
                    UserBean userBean = new UserBean();
                    staffBean.setUserBean(userBean);
                    userBean.setUserId(userId);
                    //System.out.println(userBean.getUserId());		
                    staffBean.setStaffName(resultSet.getString("staffName"));
                    DepartmentBean departmentBean = new DepartmentBean(); 
                    staffBean.setDepartmentBean(departmentBean);
                    departmentBean.setDepartmentId(resultSet.getByte("departmentId"));
                    //System.out.println(departmentBean.getDepartmentId());
                    StatusBean statusBean = new StatusBean(); 
                    staffBean.setStatusBean(statusBean);
                    statusBean.setStatusId(resultSet.getByte("statusId"));
                    //System.out.println(statusBean.getStatusId());
 			
                }
            } catch (Exception e) {
                System.out.println("Error in StaffDAO | View | Id");
            }
            finally{
                errorMessage="Error in StaffDAO| viewStaffById | DB Connection close";
                DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 		return staffBean;
            }
	}
        public ArrayList<StaffBean> viewStaffByName(String staffName)  {
            staffBean=null;
            ArrayList<StaffBean> staffList=new ArrayList<StaffBean>();
            try{
                connection=DBUtil.getDBConnection();
                preparedStatement=connection.prepareStatement("select userId , departmentId, statusId from ecamp_tbl_staff where staffName=?");
 		preparedStatement.setString(1,staffName);
 		resultSet=preparedStatement.executeQuery();
 		while(resultSet.next()){
                    staffBean=new StaffBean();
                    UserBean userBean = new UserBean();
                    staffBean.setUserBean(userBean);
                    userBean.setUserId(resultSet.getInt("userId"));
                    //System.out.println(userBean.getUserId());
 				 				
                    staffBean.setStaffName(staffName);
                    
                    DepartmentBean departmentBean = new DepartmentBean(); 
                    staffBean.setDepartmentBean(departmentBean);
                    departmentBean.setDepartmentId(resultSet.getByte("departmentId"));
                    //System.out.println(departmentBean.getDepartmentId());
 				
                    StatusBean statusBean = new StatusBean(); 
                    staffBean.setStatusBean(statusBean);
                    statusBean.setStatusId(resultSet.getByte("statusId"));
                    //System.out.println(statusBean.getStatusId());
 				
                    staffList.add(staffBean);
                }
            }
            catch(Exception e){
                System.out.println("Error in StaffDAO | viewStaffByName| All");
            }
            finally{
                errorMessage="Error in StaffDAO| viewStaffByName | DB Connection close";
 		DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
                return staffList;
            }
	}
	
	public ArrayList<StaffBean> viewStaffByDepartment(byte departmentId) {
		staffBean=null;
 		
 		ArrayList<StaffBean> staffList=new ArrayList<StaffBean>();
 		try{
 			connection=DBUtil.getDBConnection();
 			preparedStatement=connection.prepareStatement("select userId , staffName,  statusId from ecamp_tbl_staff where departmentId=?");
 			preparedStatement.setByte(1,departmentId);
 			resultSet=preparedStatement.executeQuery();
 			while(resultSet.next()){
 				staffBean=new StaffBean();
 				UserBean userBean = new UserBean();
 				staffBean.setUserBean(userBean);
 				userBean.setUserId(resultSet.getInt("userId"));
 				//System.out.println(userBean.getUserId());
 		 		
 				staffBean.setStaffName(resultSet.getString("staffName"));
 				
 				DepartmentBean departmentBean = new DepartmentBean(); 
 				staffBean.setDepartmentBean(departmentBean);
 				departmentBean.setDepartmentId(departmentId);
 				//System.out.println(departmentBean.getDepartmentId());
 				
 				StatusBean statusBean = new StatusBean(); 
 				staffBean.setStatusBean(statusBean);
 				statusBean.setStatusId(resultSet.getByte("statusId"));
 				//System.out.println(statusBean.getStatusId());
 				
 				staffList.add(staffBean);

 			}
 		}
 		catch(Exception e){
 			System.out.println("Error in StaffDAO | ViewviewStaffByDepartmentId | All");
 			}
 		finally{
 			errorMessage = "Error in StaffDAO | viewStaffByDepartmentId | All | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return staffList;
 			}
	}
	
	public ArrayList<StaffBean> viewStaffByStatus(byte statusId)  {
            staffBean=null;
            ArrayList<StaffBean> staffList=new ArrayList<StaffBean>();
            try{
                connection=DBUtil.getDBConnection();
 		preparedStatement=connection.prepareStatement("select staffName, departmentId, statusId from ecamp_tbl_staff where statusId=?");
 		preparedStatement.setByte(1,statusId);
 		resultSet=preparedStatement.executeQuery();
 		while(resultSet.next()){
                    staffBean=new StaffBean();
                    UserBean userBean = new UserBean();
                    staffBean.setUserBean(userBean);
                    userBean.setUserId(resultSet.getInt("userId"));
                    //System.out.println(userBean.getUserId());
 		 		
                    DepartmentBean departmentBean = new DepartmentBean(); 
                    staffBean.setDepartmentBean(departmentBean);
                    departmentBean.setDepartmentId(resultSet.getByte("departmentId"));
                    //System.out.println(departmentBean.getDepartmentId());
 				
                    StatusBean statusBean = new StatusBean(); 
                    staffBean.setStatusBean(statusBean);
                    statusBean.setStatusId(statusId);
                    //System.out.println(statusBean.getStatusId());
 				
                    staffList.add(staffBean);
                }
 	}
 		catch(Exception e){
 			System.out.println("Error in StaffDAO | viewStaffByStatus | All");
 			}
 		finally{
 			errorMessage="Error in StaffDAO | viewStaffByStatus | All | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return staffList;
 			}
	}
	public ArrayList<StaffBean> viewStaff()  {
            staffBean=null;
 		
            ArrayList<StaffBean> staffList=new ArrayList<StaffBean>();
            try{
            connection=DBUtil.getDBConnection();
            preparedStatement=connection.prepareStatement("select userId, staffName, departmentId,statusId from ecamp_tbl_staff");
            resultSet=preparedStatement.executeQuery();
       while(resultSet.next()){
                staffBean=new StaffBean();
 		UserBean userBean = new UserBean();
 		staffBean.setUserBean(userBean);
 		userBean.setUserId(resultSet.getInt("userId"));
 		//System.out.println(userBean.getUserId());
 				
 		staffBean.setStaffName(resultSet.getString("staffName"));
 				
 		DepartmentBean departmentBean = new DepartmentBean(); 
 		staffBean.setDepartmentBean(departmentBean);
 		departmentBean.setDepartmentId(resultSet.getByte("departmentId"));
 		//System.out.println(departmentBean.getDepartmentId());
 				
 		StatusBean statusBean = new StatusBean(); 
 		staffBean.setStatusBean(statusBean);
 		statusBean.setStatusId(resultSet.getByte("statusId"));
 		//System.out.println(statusBean.getStatusId());
 				
 		staffList.add(staffBean);
            }
           }
 		catch(Exception e){
 			System.out.println("Error in StaffDAO | viewStaff | All");
 			}
 		finally{
 			errorMessage="Error in StaffDAO |viewStaff| All | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return staffList;
 			}
	}
	
	public static void main(String[] args) {
		
		
                
               
                
	}
}
