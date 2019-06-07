
package com.ecamp.dao.circular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ecamp.util.DBUtil;
import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.circular.CircularListBean;

public class CircularListDAO {
      Connection connection=null;
     PreparedStatement preparedStatement=null;  
     ResultSet resultSet = null;
     CircularListBean circularListBean = null;
     ReceiverGroupBean ReceiverGroupBean=null;
     String query;
     String errorMessage;     
     
     public boolean addCircularList(CircularListBean circularListBean){
         boolean result = false;
         Connection connection = null;
         int count;         
         try{                              
               connection = DBUtil.getDBConnection();            
               if(connection!=null){                   
                   query = "insert into ecamp_tbl_circularlist (circularId,receiverGroupId) values(?,?)";
                   preparedStatement = connection.prepareStatement(query);
                  //System.out.println(circularListBean.getReceiverGroupBean().getReceiverGroupId());
                   preparedStatement.setInt(1, circularListBean.getCircularBean().getCircularId());                   
                   preparedStatement.setShort(2, circularListBean.getReceiverGroupBean().getReceiverGroupId());
                   count = preparedStatement.executeUpdate();
                   if(count == 1){
                       result = true;
                   }
               }
         }
         catch(Exception e){
             e.printStackTrace();
         }
         finally{
             DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);             
         }
         return result;
     }
     
     
        public ArrayList<CircularListBean> viewCircularListByCircular(int circularId){
            
         CircularListBean circularListBean = null;
         ReceiverGroupBean receiverGroupBean=null;
         CircularBean circularBean  = null;
         connection = null;
         ArrayList<CircularListBean> circularListBeanList = new ArrayList<CircularListBean>();
         try{
            connection  =DBUtil.getDBConnection();
             System.out.println(circularId);
            if(connection!=null){
             query = "select receiverGroupId from ecamp_tbl_circularList where circularId= ?";
                
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,circularId);                
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){                   
                    
                    circularBean= new CircularBean();
                    receiverGroupBean=new ReceiverGroupBean();
                    
                    circularListBean = new CircularListBean();
                    circularListBean.setCircularBean(circularBean);                    
                    circularBean.setCircularId(circularId);
                    
                    circularListBean.setReceiverGroupBean(receiverGroupBean);
                    receiverGroupBean.setReceiverGroupId(resultSet.getShort("receiverGroupId"));                            
                    
                   circularListBeanList.add(circularListBean);
                }
            }
        }
        catch(Exception e){
            System.out.println("Error in CircularListDAO || view circularId");
            e.printStackTrace();
        }
        finally {
        	errorMessage = "Error in CircularListDAO || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
        }
         return circularListBeanList; 
    }
        
         public ArrayList<CircularListBean> viewCircularListByReceiverGroup(short receiverGroupId){
         CircularListBean circularListBean = null;
         CircularBean circularBean=null;
         ReceiverGroupBean receiverGroupBean=null;
         connection = null;
         ArrayList<CircularListBean> circularListBeanList = new ArrayList<CircularListBean>();
         try{
            connection  =DBUtil.getDBConnection();
            if(connection!=null){
                query = "select circularId from ecamp_tbl_circularList where receiverGroupId= ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, receiverGroupId);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){       
                     circularBean= new CircularBean();
                    receiverGroupBean=new ReceiverGroupBean();
                    
                    circularListBean = new CircularListBean();
                    circularListBean.setReceiverGroupBean(ReceiverGroupBean);
                    receiverGroupBean.setReceiverGroupId(receiverGroupId);
                    circularListBean.setCircularBean(circularBean);
                    circularBean.setCircularId(resultSet.getShort("circularId"));                            
                    
                   circularListBeanList.add(circularListBean);
                }
            }
        }
        catch(Exception e){
            System.out.println("Error in CircularListDAO || view receiverGroupId");
            e.printStackTrace();
        }
        finally {
        	errorMessage = "Error in CircularListDAO || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
        }
         return circularListBeanList; 
    }
       public static void main(String[] args) {
//        
//       CircularListDAO cdao = new CircularListDAO();
//       CircularListBean circularListBean = new CircularListBean();
//       CircularBean circularBean=new CircularBean();
//       circularBean.setCircularId(288);
//       ReceiverGroupBean receiverGroupBean=new ReceiverGroupBean();
//       receiverGroupBean.setReceiverGroupId((short)6);
//       circularListBean.setCircularBean(circularBean);
//      circularListBean.setReceiverGroupBean(receiverGroupBean);
//       System.out.println(circularListBean.getReceiverGroupBean().getReceiverGroupId()); 
//       cdao.addCircularList(circularListBean);

//        CircularListDAO circularListDAO = new CircularListDAO();
//       ArrayList<CircularListDAO> list = new ArrayList<CircularListDAO>();

        
      
    }

}
