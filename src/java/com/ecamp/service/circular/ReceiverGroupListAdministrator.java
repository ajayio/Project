
package com.ecamp.service.circular;
import com.ecamp.bean.circular.ReceiverGroupListBean;
import com.ecamp.dao.circular.ReceiverGroupListDAO;
import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.bean.master.UserGroupBean;
import java.util.ArrayList;

public class ReceiverGroupListAdministrator {
    public String addReceiverGroupList(ReceiverGroupListBean receiverGroupListBean){
        String result="FAILURE";
        if(null ==receiverGroupListBean || receiverGroupListBean.getReceiverGroupBean().getReceiverGroupId()> 0 || receiverGroupListBean.getUserGroupBean().getUserGroupId()> 0) {
            result = "INVALID";
        }               
        else if(new ReceiverGroupListDAO().addReceiverGroupList(receiverGroupListBean)){
            result = "SUCCESS";
        }
        return result;
    }
    
     public ArrayList<ReceiverGroupListBean> viewReceiverGroupListByReceiverGroup(short receiverGroupId){
           ReceiverGroupListBean receiverGroupListBean=null;
           ArrayList<ReceiverGroupListBean>receiverGroupListBeansList=null;
           if(receiverGroupId>0){
               receiverGroupListBeansList=new ReceiverGroupListDAO().viewReceiverGroupListByReceiverGroup((short)receiverGroupId);
           }
            return receiverGroupListBeansList;
    } 
       public ArrayList<ReceiverGroupListBean> viewReceiverGroupListByUserGroup(int userGroupId){
        ReceiverGroupListBean receiverGroupListBean=null;
        ArrayList<ReceiverGroupListBean> receiverGroupListBeansList =null;
        if(userGroupId>0){
            receiverGroupListBeansList=new ReceiverGroupListDAO().viewReceiverGroupListtByUserGroup(userGroupId);
        }
        return receiverGroupListBeansList;
    }    
    public static void main(String[] args) {
//        ReceiverGroupListBean receiverGroupListBean=new ReceiverGroupListBean();
//        ReceiverGroupBean receiverGroupBean = new ReceiverGroupBean();
//        receiverGroupBean.setReceiverGroupId((short)12);
//        receiverGroupListBean.setReceiverGroupBean(receiverGroupBean);
//        UserGroupBean userGroupBean=new UserGroupBean();
//        userGroupBean.setUserGrouppId(3);
//        receiverGroupListBean.setUserGroupBean(userGroupBean);
//          ArrayList<ReceiverGroupListBean> list = new ArrayList<ReceiverGroupListBean>();
//          list =new ReceiverGroupListAdministrator().viewReceiverGroupListByReceiverGroup((short)20);
//          System.out.println(list);        
//          ReceiverGroupListDAO receiverGroupListDAO=new ReceiverGroupListDAO();
//          for(int i=0;i<list.size();i++){
//              System.out.println(list.get(i).getUserGroupBean().getUserGrouppId());
//          }
////            list=new ReceiverGroupListAdministrator().viewReceiverGroupListByUserGroup(5);
////            System.out.println(list);
////            ReceiverGroupListDAO receiverGroupListDAO=new ReceiverGroupListDAO();
////            for(int i=0;i<list.size();i++){
////                System.out.println(list.get(i).getReceiverGroupBean().getReceiverGroupId());
//            }
          
    }
}


