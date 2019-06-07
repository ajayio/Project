
package com.ecamp.dao.circular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ecamp.util.DBUtil;
import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.bean.master.UserGroupBean;
import com.ecamp.bean.circular.ReceiverGroupListBean;
public class ReceiverGroupListDAO {
    
      Connection connection=null;
     PreparedStatement preparedStatement=null;  
     ResultSet resultSet = null;
     ReceiverGroupListBean receiverGroupListBean=null;
     UserGroupBean userGroupBean=null;
     String query;
     String errorMessage;     
     
     public boolean addReceiverGroupList(ReceiverGroupListBean receiverGroupListBean){
         boolean result = false;
         Connection connection = null;
         int count;         
         try{                              
               connection = DBUtil.getDBConnection();            
               if(connection!=null){                   
                   query = "insert into ecamp_tbl_receiverGroupList(receiverGroupId,userGroupId) values(?,?)";
                   preparedStatement = connection.prepareStatement(query);
                   System.out.println(receiverGroupListBean.getReceiverGroupBean().getReceiverGroupId());
                   preparedStatement.setShort(1,receiverGroupListBean.getReceiverGroupBean().getReceiverGroupId());
                   preparedStatement.setInt(2,receiverGroupListBean.getUserGroupBean().getUserGroupId());
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
    
         public ArrayList<ReceiverGroupListBean> viewReceiverGroupListByReceiverGroup(short receiverGroupId){
         ReceiverGroupListBean receiverGroupListBean=null;
         UserGroupBean userGroupBean=null;
         ReceiverGroupBean receiverGroupBean=null;
         connection = null;
         ArrayList<ReceiverGroupListBean> receiverGroupListBeansList = new ArrayList<ReceiverGroupListBean>();
         try{
            connection  =DBUtil.getDBConnection();
            if(connection!=null){
                query = "select userGroupId from ecamp_tbl_receiverGroupList where receiverGroupId= ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, receiverGroupId);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){ 
                     userGroupBean=new UserGroupBean();
                     receiverGroupBean = new ReceiverGroupBean();
                     receiverGroupListBean =new ReceiverGroupListBean();
                     receiverGroupListBean.setReceiverGroupBean(receiverGroupBean);
                     receiverGroupBean.setReceiverGroupId(receiverGroupId);
                     receiverGroupListBean.setUserGroupBean(userGroupBean);
                     userGroupBean.setUserGroupId(resultSet.getShort("userGroupId"));
                  
                     receiverGroupListBeansList.add(receiverGroupListBean);                     
                }
            }
        }
        catch(Exception e){
            System.out.println("Error in ReceiverGroupListDAO || view receiverGroupId");
            e.printStackTrace();
            
        }
        finally {
        	errorMessage = "Error in ReceiverGroupListDAO || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
        }
         return receiverGroupListBeansList; 
    }
 
        public ArrayList<ReceiverGroupListBean> viewReceiverGroupListtByUserGroup(int userGroupId){
            ReceiverGroupListBean receiverGroupListBean=null;
            ReceiverGroupBean receiverGroupBean=null;
            UserGroupBean userGroupBean= null;
            connection = null;
         ArrayList<ReceiverGroupListBean> receiverGroupListBeanList = new ArrayList<ReceiverGroupListBean>();
         try{
            connection  =DBUtil.getDBConnection();
            if(connection!=null){
                query = "select receiverGroupId from ecamp_tbl_receiverGroupList where userGroupId= ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,userGroupId);
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){            
                  userGroupBean =new UserGroupBean();
                  receiverGroupBean=new ReceiverGroupBean();
                  receiverGroupListBean=new ReceiverGroupListBean();
                  receiverGroupListBean.setUserGroupBean(userGroupBean);
                  userGroupBean.setUserGroupId((short)userGroupId);
                 receiverGroupListBean.setReceiverGroupBean(receiverGroupBean);
                 receiverGroupBean.setReceiverGroupId(resultSet.getShort("receiverGroupId"));
                 receiverGroupListBeanList.add(receiverGroupListBean);                  
                }
            }
        }
        catch(Exception e){
            System.out.println("Error in ReceiverGroupListDAO || view userGroupId");
        }
        finally {
        	errorMessage = "Error in ReceiverGroupListDAO || closeDBConection";
        	DBUtil.closeDBConnection(connection, preparedStatement, resultSet, errorMessage);                                                     
        }
         return receiverGroupListBeanList; 
    }
        
        
       public static void main(String[] args) {
           
//        ReceiverGroupListDAO cdao=new ReceiverGroupListDAO();
//        ReceiverGroupListBean receiverGroupListBean=new ReceiverGroupListBean();
//        ReceiverGroupBean receiverGroupBean=new ReceiverGroupBean();
//        receiverGroupBean.setReceiverGroupId((short)29);
//        UserGroupBean userGroupBean=new UserGroupBean();
//        userGroupBean.setUserGroupId((short)5);
//        receiverGroupListBean.setReceiverGroupBean(receiverGroupBean);
//        receiverGroupListBean.setUserGroupBean(userGroupBean);
//         System.out.println(receiverGroupListBean.getReceiverGroupBean().getReceiverGroupId());
//         cdao.addReceiverGroupList(receiverGroupListBean); 
         
    }

}
