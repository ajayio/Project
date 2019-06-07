package com.ecamp.dao.master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ecamp.bean.master.BatchBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.StudentBean;
import com.ecamp.bean.master.UserBean;
import com.ecamp.service.master.StatusAdministrator;
import com.ecamp.service.master.StudentAdministrator;
import com.ecamp.util.DBUtil;

public class StudentDAO {
	StudentBean studentBean = null;
	Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;
    String errorMessage = null;
    public boolean addStudent(StudentBean studentBean)  {
		int count=0;
	    boolean result=false;
	    
	    
	    try {
	    	connection=DBUtil.getDBConnection();	    	
	    		preparedStatement=connection.prepareStatement("insert into ecamp_tbl_student(userId,studentName,batchId,statusId) values(?,?,?,?)");
		    	preparedStatement.setInt(1,studentBean.getUserBean().getUserId());
		    	preparedStatement.setString(2,studentBean.getStudentName());
		    	preparedStatement.setShort(3,studentBean.getBatchBean().getBatchId());
		    	preparedStatement.setByte(4,studentBean.getStatusBean().getStatusId());
		    	count=preparedStatement.executeUpdate();
		    	if(count>0){
		    		result=true;
		    	}
	    } catch (Exception e) {
	    	System.out.println("Error in studentDAO | Add");
	    }
	    finally{
	    	errorMessage="Error in studentDAO| Add | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return result;
	    	}
	}
    public String updateStatus(StudentBean studentBean) {
		String result="FAILURE";
 		
		try {
 	 		connection=DBUtil.getDBConnection();
 			preparedStatement=connection.prepareStatement("update ecamp_tbl_student set statusId  = ? where studentName = ?");
 			preparedStatement.setByte(1,studentBean.getStatusBean().getStatusId());
 			preparedStatement.setString(2,studentBean.getStudentName());
 			if(preparedStatement.executeUpdate()>0){
 				result="SUCCESS";
 			}
 		} catch (Exception e) {
 			System.out.println("Error in StudentDAO | UpdateStatus");
 			}
 		finally{
 			errorMessage="Error in StudentDAO| updateStatus | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return result;
 			}
	}
    
    public String updateBatch(StudentBean studentBean) {
		String result="FAILURE";
 		
 		try {
 			connection=DBUtil.getDBConnection();
 			preparedStatement=connection.prepareStatement("update ecamp_tbl_student  set batchId = ? where studentName = ?");
 			preparedStatement.setInt(1,studentBean.getBatchBean().getBatchId());
 			preparedStatement.setString(2,studentBean.getStudentName());
 			if(preparedStatement.executeUpdate()>0){
 				result="SUCCESS";
 			}
 		} catch (Exception e) {
 			System.out.println("Error in StudentDAO | UpdateBatch");
 			}
 		finally{
 			errorMessage="Error in StudentDAO| UpdateBatch | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return result;
 			}
	}
    
public StudentBean viewStudentById(int userId)  {
 		
		studentBean=null;
 		try {
 			connection=DBUtil.getDBConnection();
 			preparedStatement=connection.prepareStatement("select studentName, batchId, statusId from ecamp_tbl_student where userId=?");
 			preparedStatement.setInt(1,userId);
 			resultSet=preparedStatement.executeQuery();
 			if(resultSet.next()){
 				studentBean=new StudentBean();
 				UserBean userBean = new UserBean();
 				studentBean.setUserBean(userBean);
 				userBean.setUserId(userId);
 				//System.out.println(userBean.getUserId());
 				
 				studentBean.setStudentName(resultSet.getString("studentName"));
 				
 				BatchBean batchBean = new BatchBean(); 
 				studentBean.setBatchBean(batchBean);
 				batchBean.setBatchId(resultSet.getByte("batchId"));
 				//System.out.println(batchBean.getBatchId());
 				
 				StatusBean statusBean = new StatusBean(); 
 				studentBean.setStatusBean(statusBean);
 				statusBean.setStatusId(resultSet.getByte("statusId"));
 				//System.out.println(statusBean.getStatusId());
 				
 			}
 		} catch (Exception e) {
 			System.out.println("Error in StudentDAO | View | Id");
 			}
 		finally{
 			errorMessage="Error in StudentDAO| ViewStudentById | DB Connection close";
 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
 			return studentBean;
 			}
	}
public ArrayList<StudentBean> viewStudentByName(String studentName)  {
	studentBean=null;
	ArrayList<StudentBean> studentList=new ArrayList<StudentBean>();
		try{
	 		connection=DBUtil.getDBConnection();
			preparedStatement=connection.prepareStatement("select userId ,  batchId, statusId from ecamp_tbl_student where studentName=?");
			preparedStatement.setString(1,studentName);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				studentBean=new StudentBean();
				UserBean userBean = new UserBean();
				studentBean.setUserBean(userBean);
				userBean.setUserId(resultSet.getInt("userId"));
				//System.out.println(userBean.getUserId());
				 				
				studentBean.setStudentName(studentName);
				
				BatchBean batchBean = new BatchBean(); 
				studentBean.setBatchBean(batchBean);
				batchBean.setBatchId(resultSet.getByte("batchId"));
				//System.out.println(batchBean.getBatchId());
				
				StatusBean statusBean = new StatusBean(); 
				studentBean.setStatusBean(statusBean);
				statusBean.setStatusId(resultSet.getByte("statusId"));
				//System.out.println(statusBean.getStatusId());
				
				studentList.add(studentBean);

			}
		}
		catch(Exception e){
			System.out.println("Error in StudentDAO | viewStuduentName| view");
                        e.printStackTrace();
			}
		finally{
			errorMessage="Error in StuduentDAO| viewStuduentByName | DB Connection close";
			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
			return studentList;
			}
}
public ArrayList<StudentBean> viewStudentByBatch(byte batchId) {
	studentBean=null;
		
		ArrayList<StudentBean> studentList=new ArrayList<StudentBean>();
		try{
			connection=DBUtil.getDBConnection();
			preparedStatement=connection.prepareStatement("select userId , studentName from ecamp_tbl_student where batchId=?");
			preparedStatement.setByte(1,batchId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				studentBean=new StudentBean();
				UserBean userBean = new UserBean();
				studentBean.setUserBean(userBean);
				userBean.setUserId(resultSet.getInt("userId"));
				//System.out.println(userBean.getUserId());
		 		
				studentBean.setStudentName(resultSet.getString("studentName"));
				
				BatchBean batchBean = new BatchBean(); 
				studentBean.setBatchBean(batchBean);
				batchBean.setBatchId(batchId);
				//System.out.println(batchBean.getBatchId());
				
				StatusBean statusBean = new StatusBean(); 
				studentBean.setStatusBean(statusBean);
				statusBean.setStatusId(resultSet.getByte("statusId"));
				//System.out.println(statusBean.getStatusId());
				
				studentList.add(studentBean);

			}
		}
		catch(Exception e){
			System.out.println("Error in StudentDAO | viewStudentByBatchtId | All");
			}
		finally{
			errorMessage = "Error in StudentDAO | viewStudentByBatchId | All | DB Connection close";
			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
			return studentList;
			}
}

public ArrayList<StudentBean> viewStudentByStatus(byte statusId)  {
	studentBean=null;
		
		ArrayList<StudentBean> studentList=new ArrayList<StudentBean>();
		try{
			connection=DBUtil.getDBConnection();
			preparedStatement=connection.prepareStatement("select studentName, batchId, statusId from ecamp_tbl_student where statusId=?");
			preparedStatement.setByte(1,statusId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				studentBean=new StudentBean();
				UserBean userBean = new UserBean();
				studentBean.setUserBean(userBean);
				userBean.setUserId(resultSet.getInt("userId"));
				//System.out.println(userBean.getUserId());
				
				studentBean.setStudentName(resultSet.getString("studentName"));
		 		
				BatchBean batchBean = new BatchBean(); 
				studentBean.setBatchBean(batchBean);
				batchBean.setBatchId(resultSet.getByte("batchId"));
				//System.out.println(batchBean.getBatchId());
				
				StatusBean statusBean = new StatusBean(); 
				studentBean.setStatusBean(statusBean);
				statusBean.setStatusId(statusId);
				//System.out.println(statusBean.getStatusId());
				
				studentList.add(studentBean);

			}
		}
		catch(Exception e){
			System.out.println("Error in StudentDAO | viewStudentByStatus | All");
			}
		finally{
			errorMessage="Error in StudentDAO | viewStudentByStatusId  | DB Connection close";
			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
			return studentList;
			}
}
public ArrayList<StudentBean> viewStudent()  {
	studentBean=null;
		
		ArrayList<StudentBean> studentList=new ArrayList<StudentBean>();
		try{
			connection=DBUtil.getDBConnection();
			preparedStatement=connection.prepareStatement("select userId, studentName, batchId, statusId from ecamp_tbl_student");
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				studentBean=new StudentBean();
				UserBean userBean = new UserBean();
				studentBean.setUserBean(userBean);
				userBean.setUserId(resultSet.getInt("userId"));
				//System.out.println(userBean.getUserId());
				
				studentBean.setStudentName(resultSet.getString("studentName"));
		 		
				BatchBean batchBean = new BatchBean(); 
				studentBean.setBatchBean(batchBean);
				batchBean.setBatchId(resultSet.getByte("batchId"));
				//System.out.println(batchBean.getBatchId());
				
				StatusBean statusBean = new StatusBean(); 
				studentBean.setStatusBean(statusBean);
				statusBean.setStatusId(resultSet.getByte("statusId"));
				//System.out.println(statusBean.getStatusId());
				
				studentList.add(studentBean);
			}
		}
		catch(Exception e){
			System.out.println("Error in StudentDAO | viewStudent | All");
                        e.printStackTrace();
			}
		finally{
			errorMessage="Error in StudentDAO |viewStudent| All | DB Connection close";
			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
			return studentList;
			}
}
public static void main(String[] args) {
	//StudentDAO studentDAO = new StudentDAO();
        //ArrayList<StudentBean> list= new ArrayList<StudentBean>();
        
        // list=studentDAO.viewStudent();
        //list=studentDAO.viewStudentByName("Gowtham");
        
        //System.out.println(studentDAO.viewStudentById(4).getStudentName());
        //System.out.println(list);
        
//        for(int i=0;i<list.size();i++){
//            System.out.println("UserId : "+ list.get(i).getUserBean().getUserId());
//            System.out.println("Name : "+ list.get(i).getStudentName());
//            System.out.println("BatchId : "+ list.get(i).getBatchBean().getBatchId());
//            System.out.println("StatusId : "+ list.get(i).getStatusBean().getStatusId());
//            System.out.println("-------------------------------------");
//        }
        
        
    }
}

