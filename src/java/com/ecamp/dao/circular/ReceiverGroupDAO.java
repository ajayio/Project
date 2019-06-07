package com.ecamp.dao.circular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ecamp.util.DBUtil;
import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.bean.master.CollegeBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.service.circular.ReceiverGroupAdministrator;



public class ReceiverGroupDAO {
    
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    ReceiverGroupBean receiverGroupBean=null;
    String errorMessage=null;

    public boolean addReceiverGroup(ReceiverGroupBean receiverGroupBean) {
        int count=0;
        boolean result=false;
        
        try {
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            }            
            preparedStatement=connection.prepareStatement("insert into ecamp_tbl_receiverGroup(receiverGroupName,collegeId,statusId)values(?,?,?)");
            preparedStatement.setString(1,receiverGroupBean.getReceiverGroupName());
            preparedStatement.setByte(2,receiverGroupBean.getCollegeBean().getCollegeId());
            preparedStatement.setByte(3,receiverGroupBean.getStatusBean().getStatusId());
            count = preparedStatement.executeUpdate();
            if(count>0){
                result=true;
            }
        } catch (Exception e) {
            System.out.println("Error in ReceiverGroupDAO | Add");
            }
        finally{
            errorMessage="Error in ReceiverGroupDAO| Add | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return result;
        }
    }
    public String updateReceiverGroup(ReceiverGroupBean receiverGroupBean)  {
        String result="FAILURE";
        
        try {
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement=connection.prepareStatement("update ecamp_tbl_receiverGroup set statusId = ? where receiverGroupName = ? AND collegeId = ?");
            preparedStatement.setByte(1,receiverGroupBean.getStatusBean().getStatusId());
            preparedStatement.setString(2,receiverGroupBean.getReceiverGroupName());
            preparedStatement.setByte(3,receiverGroupBean.getCollegeBean().getCollegeId());
            if(preparedStatement.executeUpdate()>0){
                result="SUCCESS";
                }
            } catch (Exception e) {
                System.out.println("Error in ReceiverGroupDAO | Update");
                }
        finally{
            errorMessage="Error in ReceiverGroupDAO | Update | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return result;
        }
    }
    public ReceiverGroupBean viewReceiverGroup(short receiverGroupId) {
        receiverGroupBean=null;
        
        try {
            connection=DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select receiverGroupName,collegeId,statusId  from ecamp_tbl_receiverGroup where receiverGroupId = ?");
            preparedStatement.setShort(1,receiverGroupId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                receiverGroupBean = new ReceiverGroupBean();
                receiverGroupBean.setReceiverGroupId(receiverGroupId);
                receiverGroupBean.setReceiverGroupName(resultSet.getString("receiverGroupName"));
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId( resultSet.getByte("collegeId"));
                receiverGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                receiverGroupBean.setStatusBean(statusBean);
                }
                
        } catch (Exception e) {
            System.out.println("Error in ReceiverGroupDAO | View | Id:"+ e);
            }
        finally{
            errorMessage="Error in ReceiverGroupDAO | View | ReceiverGroupId | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return receiverGroupBean;
        }
    }
    public ReceiverGroupBean viewReceiverGroup(String ReceiverGroupName){
        
        receiverGroupBean=null;
        try {
            connection=DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement=connection.prepareStatement("select receiverGroupId,collegeId,statusId  from ecamp_tbl_receiverGroup where receiverGroupName=?");
            preparedStatement.setString(1, ReceiverGroupName);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                receiverGroupBean = new ReceiverGroupBean();
                receiverGroupBean.setReceiverGroupId(resultSet.getByte("receiverGroupId"));        
                receiverGroupBean.setReceiverGroupName(ReceiverGroupName);
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId( resultSet.getByte("collegeId"));
                receiverGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                receiverGroupBean.setStatusBean(statusBean);
            }
        } catch (Exception e) {
            System.out.println("Error in ReceiverGroupDAO | View | Name");
            }
        finally{
            errorMessage="Error in ReceiverGroupDAO | View | ReceiverGroupName | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet);
            return receiverGroupBean;
        }
    }

    public ArrayList<ReceiverGroupBean>viewReceiverGroup(byte collegeId){
        receiverGroupBean = null;
        
        ArrayList<ReceiverGroupBean> receiverGroupBeanList = new ArrayList<ReceiverGroupBean>();
        try{ 
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select receiverGroupId, receiverGroupName, statusId from ecamp_tbl_receiverGroup where collegeId = ?");
            preparedStatement.setByte(1,collegeId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                receiverGroupBean = new ReceiverGroupBean();                
                receiverGroupBean.setReceiverGroupId(resultSet.getByte("receiverGroupId"));
                receiverGroupBean.setReceiverGroupName(resultSet.getString("receiverGroupName"));
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId(collegeId);
                receiverGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                receiverGroupBean.setStatusBean(statusBean);
                receiverGroupBeanList.add(receiverGroupBean);
            }
        }
        catch(Exception e){
            System.out.println("Error in ReceiverGroupDAO | View | COLLEGEID");
            }
        finally{
            errorMessage="Error in ReceiverGroupDAO | View | All | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet,"");
            return receiverGroupBeanList;      
        }
        
    }
    public ArrayList<ReceiverGroupBean>viewReceiverGroup(){
        receiverGroupBean = null;
        
        ArrayList<ReceiverGroupBean> receiverGroupBeanList = new ArrayList<ReceiverGroupBean>();
        try{ 
            connection = DBUtil.getDBConnection();
            if(connection == null){
                System.out.println("db is not ceonnected");
            } 
            preparedStatement = connection.prepareStatement("select receiverGroupId, receiverGroupName, collegeId, statusId from ecamp_tbl_receiverGroup");
            
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                receiverGroupBean = new ReceiverGroupBean();
                receiverGroupBean.setReceiverGroupId(resultSet.getByte("receiverGroupId"));
                receiverGroupBean.setReceiverGroupName(resultSet.getString("receiverGroupName"));
                CollegeBean collegeBean = new CollegeBean();
                collegeBean.setCollegeId( resultSet.getByte("collegeId"));
                receiverGroupBean.setCollegeBean(collegeBean);
                StatusBean statusBean = new StatusBean();
                statusBean.setStatusId(resultSet.getByte("statusId"));
                receiverGroupBean.setStatusBean(statusBean);
                receiverGroupBeanList.add(receiverGroupBean);
            }
        }
        catch(Exception e){
            System.out.println("Error in ReceiverGroupDAO | View | All");
            }
        finally{
            errorMessage="Error in ReceiverGroupDAO | View | All | DB Connection close";
            DBUtil.closeDBConnection(connection,preparedStatement, resultSet,"");
            return receiverGroupBeanList;      
        }
    }
    public static void main(String[] args) {
//        ReceiverGroupBean receiverGroupBean = new ReceiverGroupBean();
//        receiverGroupBean.setReceiverGroupId((short)3);
//        receiverGroupBean.setReceiverGroupName("Studen2");
//        CollegeBean collegeBean = new CollegeBean();
//        collegeBean.setCollegeId((byte)1);
//        receiverGroupBean.setCollegeBean(collegeBean);
//        StatusBean statusBean = new StatusBean();
//        statusBean.setStatusId((byte)2);
//        receiverGroupBean.setStatusBean(statusBean);        
        //System.out.println(new ReceiverGroupDAO().addReceiverGroup(receiverGroupBean));
//        //System.out.println(new ReceiverGroupDAO().updateReceiverGroup(receiverGroupBean));
        //receiverGroupBean = new ReceiverGroupAdministrator().viewReceiverGroup("first year cse a");        
        
//        //System.out.println(new ReceiverGroupDAO().viewReceiverGroup("Student").getStatusBean().getStatusId());
//        ArrayList<ReceiverGroupBean> receiverGroupBeanList = new ReceiverGroupDAO().viewReceiverGroup((byte)3);
//        for(int i=0;i<receiverGroupBeanList.size();i++){
//            System.out.println(receiverGroupBeanList.get(i).getReceiverGroupName());
//        }
//        //System.out.println(new ReceiverGroupDAO().addReceiverGroup(receiverGroupBean));

    }
}


