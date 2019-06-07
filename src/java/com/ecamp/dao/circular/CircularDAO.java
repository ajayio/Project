package com.ecamp.dao.circular;

import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserBean;

import com.ecamp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;



public class CircularDAO {
     Connection connection=null;
     PreparedStatement preparedStatement=null;       
     ResultSet resultSet = null;
     //private CircularDAO circularDAO;
     CircularBean circularBean = null;     
     String errorMessage;
     String query;
     ResultSet rs = null;     

    
     public boolean addCircular(CircularBean circularBean){
         boolean result = false;              
         Connection connection = null;                  
         int count = 0;         
         int circularId = 0;
         try{                              
               connection = DBUtil.getDBConnection();      
               if(connection!=null){                   
                   query = "insert into ecamp_tbl_circular(circularDate, title, subject,validFrom, validTo, userId, statusid) values(?,?,?,?,?,?,?)";
                   preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);                                                         
                   preparedStatement.setTimestamp(1,new java.sql.Timestamp(circularBean.getCircularDate().getTime()));
                   preparedStatement.setString(2, circularBean.getTitle());
                   preparedStatement.setString(3, circularBean.getSubject());                   
                   preparedStatement.setTimestamp(4, new java.sql.Timestamp(circularBean.getValidFrom().getTime()));                   
                   if(circularBean.getValidTo() == null){
                       preparedStatement.setTimestamp(5,null);
                   }
                   else{
                       preparedStatement.setTimestamp(5, new java.sql.Timestamp(circularBean.getValidTo().getTime()));
                   }
                   preparedStatement.setInt(6, circularBean.getUserBean().getUserId());
                   preparedStatement.setByte(7, circularBean.getStatusBean().getStatusId());                   
                   count = preparedStatement.executeUpdate();
                   if(count == 1){
                       rs = preparedStatement.getGeneratedKeys();
                       
                       if(rs.next()){
                           circularId = rs.getInt(1); 
                           circularBean.setCircularId(circularId);                            
                           System.out.println(circularId);
                       }                                         
                       result = true;
                   }
               }
         }
         catch(Exception e){
             System.out.println("Error in CircularDao || addCircular");
             e.printStackTrace();
         }
         finally{
             errorMessage = "Error in CircularDAO || Add || closeDBConnection";
             DBUtil.closeDBConnection(connection, preparedStatement, null, errorMessage);
         }
         return result;
     }
     
     
     public String updateCircular(CircularBean circularBean){
         String result = "FAILURE";
         try{
            connection = DBUtil.getDBConnection();
            if(connection!=null){
                 query = "update ecamp_tbl_circular set validFrom=?, validTo=? where circularId=?";
                 preparedStatement = connection.prepareStatement(query);
                 preparedStatement.setTimestamp(1, new java.sql.Timestamp(circularBean.getValidFrom().getTime()));
                 preparedStatement.setTimestamp(2, new java.sql.Timestamp(circularBean.getValidTo().getTime()));
                 preparedStatement.setInt(3, circularBean.getCircularId());
                 if(preparedStatement.executeUpdate() > 0){
                     result = "SUCCESS";
                 }                 
            }
         }  
         catch(Exception e){
             System.out.println("Error in CircularDAO || update");
         }
         finally{
             errorMessage = "Error in CircularDAO || Update || closeDBConnection";
             DBUtil.closeDBConnection(connection, preparedStatement, null, errorMessage);
         }        
         return result;
         
     }
     
     
     
     public CircularBean viewCircularByCircularId(int circularId){         
         try{
             connection  =DBUtil.getDBConnection();
             if(connection!=null){
                 query = "select circularDate, title, subject, validFrom, validTo,userId,statusId from ecamp_tbl_circular where circularId = ?";
                 preparedStatement = connection.prepareStatement(query);
                 preparedStatement.setInt(1, circularId);
                 resultSet = preparedStatement.executeQuery();
                 if(resultSet.next()){
                     circularBean = new CircularBean();
                     circularBean.setCircularId(circularId);
                     circularBean.setCircularDate(resultSet.getDate("circularDate"));                     
                     circularBean.setTitle(resultSet.getString("title"));
                     circularBean.setSubject(resultSet.getString("subject"));
                     circularBean.setValidFrom(resultSet.getDate("validFrom"));
                     circularBean.setValidTo(resultSet.getDate("validTo"));
                        UserBean userBean = new UserBean();
                        userBean.setUserId(resultSet.getInt("userId"));
                     circularBean.setUserBean(userBean);
                        StatusBean statusBean = new StatusBean();
                        statusBean.setStatusId(resultSet.getByte("statusId"));
                     circularBean.setStatusBean(statusBean);                     
                 }
             }
         }
         catch(Exception e){
             System.out.println("Error in circularDAO || view By Circular");
         }
         finally{
             errorMessage = "Error in CircularDAO || view By circular || closeDBConnection";
             DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
         }
         
         return circularBean;         
     }
        
     
      public ArrayList<CircularBean> viewCircularByUserId(int userId){
          
         connection = null;
         ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
         try{
            connection  =DBUtil.getDBConnection();
            if(connection!=null){
                query = "select circularId, circulardate, title, subject, validFrom, validTo, statusId from ecamp_tbl_circular where userId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, userId);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){                	
                    circularBean = new CircularBean();
                    circularBean.setCircularId(resultSet.getInt("circularId"));
                    circularBean.setCircularDate(resultSet.getDate("circularDate"));                     
                    circularBean.setTitle(resultSet.getString("title"));
                    circularBean.setSubject(resultSet.getString("subject"));
                    circularBean.setValidFrom(resultSet.getDate("validFrom"));
                    circularBean.setValidTo(resultSet.getDate("validTo"));
                       UserBean userBean = new UserBean();
                       userBean.setUserId(userId);
                    circularBean.setUserBean(userBean);
                       StatusBean statusBean = new StatusBean();
                       statusBean.setStatusId(resultSet.getByte("statusId"));
                    circularBean.setStatusBean(statusBean);
                    circularBeanList.add(circularBean);
                }
            }
        }
        catch(Exception e){
            System.out.println("Error in CircularDAO || view userId || circularBean");
        }
        finally {
        	errorMessage = "Error in CircularDAO || viewCircular ||  user || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
        }
         return circularBeanList; 
    }
      
      
      public ArrayList<CircularBean> viewCircularByDate(Date circularDate){
                    
          ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
          System.out.println(new java.sql.Timestamp(circularDate.getTime()));
           try{
                   connection  =DBUtil.getDBConnection();
                   if(connection!=null){
                         if(circularDate != null){                              
                            query = "select circularId, title, subject, validFrom, validTo, userId, statusId from ecamp_tbl_circular where date(circularDate)=date(?)";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setTimestamp(1,new java.sql.Timestamp(circularDate.getTime()));
                            resultSet = preparedStatement.executeQuery();
                            while(resultSet.next()){                	
                                 circularBean = new CircularBean();
                                 circularBean.setCircularId(resultSet.getInt("circularId"));
                                 circularBean.setCircularDate(circularDate);                     
                                 circularBean.setTitle(resultSet.getString("title"));
                                 circularBean.setSubject(resultSet.getString("subject"));
                                 circularBean.setValidFrom(resultSet.getDate("validFrom"));
                                 circularBean.setValidTo(resultSet.getDate("validTo"));
                                    UserBean userBean = new UserBean();
                                    userBean.setUserId(resultSet.getInt("userId"));
                                 circularBean.setUserBean(userBean);
                                    StatusBean statusBean = new StatusBean();
                                    statusBean.setStatusId(resultSet.getByte("statusId"));
                                 circularBean.setStatusBean(statusBean);
                                 circularBeanList.add(circularBean);
                      }
                   }
                }                        
                
          }
         catch(Exception e){
                System.out.println("Error in CircularDAO || viewCircular || date");
         } 
         finally {
        	errorMessage = "Error in CircularDAO || viewCircular || date || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
         }
         return circularBeanList;
      }
      
      
        public ArrayList<CircularBean> viewCircularByTitle(String title){
                    
          ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
           try{
                   connection  =DBUtil.getDBConnection();
                   if(connection!=null){                         
                            query = "select circularId, circularDate,  subject, validFrom, validTo, userId, statusId from ecamp_tbl_circular where title = ?";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1,title);
                            resultSet = preparedStatement.executeQuery();
                            while(resultSet.next()){                	
                                 circularBean = new CircularBean();
                                 circularBean.setCircularId(resultSet.getInt("circularId"));
                                 circularBean.setTitle(title);
                                 circularBean.setSubject(resultSet.getString("subject"));                                                                                       
                                 circularBean.setValidFrom(resultSet.getDate("validFrom"));
                                 circularBean.setValidTo(resultSet.getDate("validTo"));
                                    UserBean userBean = new UserBean();
                                    userBean.setUserId(resultSet.getInt("userId"));
                                 circularBean.setUserBean(userBean);
                                    StatusBean statusBean = new StatusBean();
                                    statusBean.setStatusId(resultSet.getByte("statusId"));
                                 circularBean.setStatusBean(statusBean);
                                 circularBeanList.add(circularBean);                      
                   }
                }                        
                
          }
         catch(Exception e){
                System.out.println("Error in CircularDAO || viewCircular || title");
         } 
         finally {
        	errorMessage = "Error in CircularDAO ||viewCircular || title || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
         }
         return circularBeanList;
      }
        
       
        public ArrayList<CircularBean> viewCircularValidFrom(Date validFrom){
          
          circularBean = null;
          ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
           try{
                   connection  =DBUtil.getDBConnection();
                   if(connection!=null){
                         if(validFrom != null){                              
                            query = "select circularId,circularDate,  title, subject, validTo, userId, statusId from ecamp_tbl_circular where date(validFrom)=date(?)";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setTimestamp(1,new java.sql.Timestamp(validFrom.getTime()));
                            resultSet = preparedStatement.executeQuery();
                            while(resultSet.next()){                	
                                 circularBean = new CircularBean();
                                 circularBean.setCircularId(resultSet.getInt("circularId"));
                                 circularBean.setCircularDate(resultSet.getDate("circularDate"));                     
                                 circularBean.setTitle(resultSet.getString("title"));
                                 circularBean.setSubject(resultSet.getString("subject"));
                                 circularBean.setValidFrom(validFrom);
                                 circularBean.setValidTo(resultSet.getDate("validTo"));
                                    UserBean userBean = new UserBean();
                                    userBean.setUserId(resultSet.getInt("userId"));
                                 circularBean.setUserBean(userBean);
                                    StatusBean statusBean = new StatusBean();
                                    statusBean.setStatusId(resultSet.getByte("statusId"));
                                 circularBean.setStatusBean(statusBean);
                                 circularBeanList.add(circularBean);
                      }
                   }
                }                        
                
          }
         catch(Exception e){
                System.out.println("Error in CircularDAO || viewCircular || ValidFrom");
         } 
         finally {
        	errorMessage = "Error in CircularDAO || viewCircular || validFrom || closeDBConection";
         	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
         }
         return circularBeanList;
      }
        
        public ArrayList<CircularBean> viewCircularValidTo(Date validTo){
          
          circularBean = null;
          ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
           try{
                   connection  =DBUtil.getDBConnection();
                   if(connection!=null){
                         if(validTo != null){                              
                            query = "select circularId,circularDate, title, subject, validFrom, userId, statusId from ecamp_tbl_circular where date(validTo)=date(?)";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setTimestamp(1,new java.sql.Timestamp(validTo.getTime()));
                            resultSet = preparedStatement.executeQuery();
                            while(resultSet.next()){                	
                                 circularBean = new CircularBean();
                                 circularBean.setCircularId(resultSet.getInt("circularId"));
                                 circularBean.setCircularDate(resultSet.getDate("circularDate"));                     
                                 circularBean.setTitle(resultSet.getString("title"));
                                 circularBean.setSubject(resultSet.getString("subject"));
                                 circularBean.setValidFrom(resultSet.getDate("validFrom"));
                                 circularBean.setValidTo(validTo);
                                    UserBean userBean = new UserBean();
                                    userBean.setUserId(resultSet.getInt("userId"));
                                 circularBean.setUserBean(userBean);
                                    StatusBean statusBean = new StatusBean();
                                    statusBean.setStatusId(resultSet.getByte("statusId"));
                                 circularBean.setStatusBean(statusBean);
                                 circularBeanList.add(circularBean);
                      }
                   }
                }                                        
          }
         catch(Exception e){
                System.out.println("Error in CircularDAO || viewCircular || ValidTo");
         } 
         finally {
        	errorMessage = "Error in CircularDAO || viewCircular || validTo || closeDBConection";
         	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
         }
         return circularBeanList;
      }
        
        public ArrayList<CircularBean> viewCircularByValidFromTo(Date circularFrom, Date circularTo){
            ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
            try{
                connection = DBUtil.getDBConnection();
                if(connection!=null){
                    query = "select circularId, circularDate, title, subject, validFrom, validTo, userId, statusId from ecamp_tbl_circular where circularDate between date(?) and date(?)";
                    preparedStatement = connection.prepareStatement(query);
                    System.out.println(circularFrom+" "+ circularTo);
                    preparedStatement.setTimestamp(1, new java.sql.Timestamp(circularFrom.getTime()));
                    System.out.println(new java.sql.Timestamp(circularFrom.getTime()));
                    preparedStatement.setTimestamp(2, new java.sql.Timestamp(circularTo.getTime()));
                    resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        circularBean = new CircularBean();
                            circularBean.setCircularId(resultSet.getInt("circularId"));
                            circularBean.setCircularDate(resultSet.getDate("circularDate"));                     
                            circularBean.setTitle(resultSet.getString("title"));
                            circularBean.setSubject(resultSet.getString("subject"));
                            circularBean.setValidFrom(resultSet.getDate("validFrom"));
                            circularBean.setValidTo(resultSet.getDate("validTo"));
                        UserBean userBean = new UserBean();
                            userBean.setUserId(resultSet.getInt("userId"));
                        circularBean.setUserBean(userBean);
                           StatusBean statusBean = new StatusBean();
                           statusBean.setStatusId(resultSet.getByte("statusId"));
                        circularBean.setStatusBean(statusBean);
                        circularBeanList.add(circularBean);
                    }
                }
            }
            catch(Exception e){
                System.out.println("Error in CircularDAO || viewCircular || circularFrom to circularTo");
                e.printStackTrace();
            }
            finally {
        	errorMessage = "Error in CircularDAO || viewCircular || circularFrom  to circularTo || closeDBConection";
         	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
            }
            
            return circularBeanList;
        }
        
        
        
       public ArrayList<CircularBean> viewCircular(){
         
         connection = null;
         ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
        try{
            connection  =DBUtil.getDBConnection();
            if(connection!=null){
                query = "select circularId, circulardate, title, subject, validFrom, validTo,userId, statusId from ecamp_tbl_circular";
                preparedStatement = connection.prepareStatement(query);                
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){                	
                    circularBean = new CircularBean();
                    circularBean.setCircularId(resultSet.getInt("circularId"));
                    circularBean.setCircularDate(resultSet.getDate("circularDate"));                     
                    circularBean.setTitle(resultSet.getString("title"));
                    circularBean.setSubject(resultSet.getString("subject"));
                    circularBean.setValidFrom(resultSet.getDate("validFrom"));
                    circularBean.setValidTo(resultSet.getDate("validTo"));
                       UserBean userBean = new UserBean();
                       userBean.setUserId(resultSet.getInt("userId"));
                    circularBean.setUserBean(userBean);
                       StatusBean statusBean = new StatusBean();
                       statusBean.setStatusId(resultSet.getByte("statusId"));
                    circularBean.setStatusBean(statusBean);
                    circularBeanList.add(circularBean);
                }
            }
        }
        catch(Exception e){
            System.out.println("Error in CircularDAO || viewCircular  || All");
            e.printStackTrace();
        }
        finally {
        	errorMessage = "Error in CircularDAO || viewCircular || All || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);
        }
        
        return circularBeanList;         
    }
          
       
     public static void main(String[] args) {
//        
       CircularDAO cdao = new CircularDAO();
       CircularBean circularBean = new CircularBean();
           circularBean.setCircularId(6);                                  
           circularBean.setCircularDate(new java.util.Date());  
           circularBean.setTitle("Holiday1");
            circularBean.setSubject("Tomorrow is a hoilday");            
            circularBean.setValidFrom(new java.util.Date());
            circularBean.setValidTo(new java.util.Date());
        UserBean userBean = new UserBean();
            userBean.setUserId(2);
        circularBean.setUserBean(userBean);
        StatusBean statusBean = new StatusBean();
            statusBean.setStatusId((byte)2);
            statusBean.setStatusName("alive");
            statusBean.setDescription("this is alive");
        circularBean.setStatusBean(statusBean);                
         System.out.println(cdao.addCircular(circularBean));     
         //System.out.println(new CircularDAO().circularId);
         
            
                //System.out.println(circularDAO.getCircularId());
//         CircularDAO  circularDAO = new CircularDAO().getCircularDAO();
//            System.out.println(circularDAO);
                
                
                
                
        //cdao.updateCircular(circularBean);
        //cdao.viewCircular();
        //CircularBean circularBean1  =new CircularBean();
         //System.out.println(circularBean.getTitle());
    }
}
