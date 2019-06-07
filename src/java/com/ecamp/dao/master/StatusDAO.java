package com.ecamp.dao.master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.util.DBUtil;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDAO {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	StatusBean statusBean=null;
	String errorMessage=null;
	public boolean addStatus(StatusBean statusBean){
		int count=0;
	    boolean result=false;
	   
	    try {
	    	 connection=DBUtil.getDBConnection();
	    	preparedStatement=connection.prepareStatement("insert into ecamp_tbl_status(statusName,description) values(?,?)");
	    	preparedStatement.setString(1,statusBean.getStatusName());
	    	preparedStatement.setString(2,statusBean.getDescription());
	    	count=preparedStatement.executeUpdate();
	    	if(count>0){
	    		result=true;
	    	}
	    } catch (Exception e) {
	    	System.out.println("Error in StatusDAO | Add");
	    }
	    finally{
	    	errorMessage="Error in StatusDAO | Add | DB Connection close";
	    	DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
	    	return result;
	    	}
	    }
	 	public String updateStatus(StatusBean statusBean){
	 		String result="FAILURE";
	 		
	 		try {
	 			connection=DBUtil.getDBConnection();
	 			preparedStatement=connection.prepareStatement("update ecamp_tbl_status set description = ?where statusName = ?");
	 			preparedStatement.setString(1,statusBean.getDescription());
	 			preparedStatement.setString(2,statusBean.getStatusName());
	 			if(preparedStatement.executeUpdate()>0){
	 				result="SUCCESS";
	 			}
	 		} catch (Exception e) {
	 			System.out.println("Error in StatusDAO | Update");
	 			}
	 		finally{
	 			errorMessage="Error in StatusDAO| Update | DB Connection close";
	 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
	 			return result;
	 			}
	 		}
	 	public StatusBean viewStatus(byte statusId){
	 		statusBean=null;
	 		try {
		 		connection=DBUtil.getDBConnection();
	 			preparedStatement=connection.prepareStatement("select statusName, description from ecamp_tbl_status where statusid=?");
	 			preparedStatement.setByte(1,statusId);
	 			resultSet=preparedStatement.executeQuery();
	 			if(resultSet.next()){
	 				statusBean=new StatusBean();
	 				statusBean.setStatusId(statusId);
	 				statusBean.setStatusName(resultSet.getString("statusName"));
	 				String description =resultSet.getString("description");
	 				if(description==null)
	 					statusBean.setDescription("");
	 				else
	 					statusBean.setDescription(description);
	 				}
	 		} catch (Exception e) {
	 			System.out.println("Error in StatusDAO | View | Id");
	 			}
	 		finally{
	 			errorMessage="Error in StatusDAO | View | StatusId | DB Connection close";
	 			DBUtil.closeDBConnection(connection,
	 			preparedStatement, resultSet, errorMessage);
	 			return statusBean;
	 			}
	 		}
	 	public StatusBean viewStatus(String statusName){
	 		statusBean=null;
	 		try {
	 			connection=DBUtil.getDBConnection();
	 			preparedStatement=connection.prepareStatement("select statusId, description from ecamp_tbl_status where statusName=?");
	 			preparedStatement.setString(1, statusName);
	 			resultSet=preparedStatement.executeQuery();
	 			if(resultSet.next()){
	 				statusBean=new StatusBean();
	 				statusBean.setStatusId(resultSet.getByte("statusId"));
	 				statusBean.setStatusName(statusName);
	 				String description = resultSet.getString("description");
	 				if(description==null)
	 					statusBean.setDescription("");
	 				else
	 					statusBean.setDescription(description);
	 				}
		 } catch (Exception e) {
			 System.out.println("Error in StatusDAO | View | Name");
			 }
	 		finally{
	 			errorMessage="Error in StatusDAO| View | StatusName | DB Connection close";
	 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
	 			return statusBean;
	 			}
		  }
	 	public ArrayList<StatusBean>viewStatus(){
	 		statusBean=null;
	 		
	 		ArrayList<StatusBean> statusList=new ArrayList<StatusBean>();
	 		try{
	 			connection=DBUtil.getDBConnection();
	 			preparedStatement=connection.prepareStatement("select statusId, statusName, description from ecamp_tbl_status");
	 			resultSet=preparedStatement.executeQuery();
	 			while(resultSet.next()){
	 				statusBean=new StatusBean();
	 				statusBean.setStatusId(resultSet.getByte("statusId"));
	 				statusBean.setStatusName(resultSet.getString("statusName"));
	 				String description = resultSet.getString("description");
	 				if(description==null)
	 					statusBean.setDescription("");
	 				else
	 					statusBean.setDescription(description);
	 					statusList.add(statusBean);
	 			}
	 		}
	 		catch(Exception e){
	 			System.out.println("Error in StatusDAO | View | All");
	 			}
	 		finally{
	 			errorMessage="Error in StatusDAO | View | All | DB Connection close";
	 			DBUtil.closeDBConnection(connection,preparedStatement, resultSet, errorMessage);
	 			return statusList;
	 			}
	 		}
	 	public static void main(String[] args) {
	 		//StatusBean statusBean=new StatusBean();
	 		//statusBean.setStatusId((byte)1);
	 		//statusBean.setStatusName("TEST");

	 		//statusBean.setDescription("")
	 		//System.out.println(newStatusDAO().addStatus(statusBean));
	 		//System.out.println(newStatusDAO().updateStatus(statusBean));
	 		//System.out.println(newStatusDAO().viewStatus((byte)1).getDescription());
	 		//System.out.println(newStatusDAO().viewStatus("TEST").getDescription());
		   //System.out.println(newStatusDAO().viewStatus().iterator().next().getDescription());
		   //System.out.println(new StatusDAO().viewStatus());
		  }
}
