
package com.ecamp.service.circular;
import java.util.ArrayList;

import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.bean.master.CollegeBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.dao.circular.ReceiverGroupDAO;

    public class ReceiverGroupAdministrator {
    
        public String addReceiverGroup(ReceiverGroupBean receiverGroupBean) {
            String result="FAILURE";
            if (receiverGroupBean==null || receiverGroupBean.getReceiverGroupId() == 0 || receiverGroupBean.getReceiverGroupName()==null || receiverGroupBean.getReceiverGroupName().isEmpty() || receiverGroupBean.getCollegeBean().getCollegeId() == 0){
                result = "addINVALID";
            }
            else if(new ReceiverGroupDAO().addReceiverGroup(receiverGroupBean)){
                result = "SUCCESS";
            }
            return result;
        }
    
        public String updateReceiverGroup(ReceiverGroupBean receiverGroupBean)  {
            String result = "upadateINVALID";

            if(receiverGroupBean != null && receiverGroupBean.getReceiverGroupName() != null && !receiverGroupBean.getReceiverGroupName().isEmpty() && receiverGroupBean.getCollegeBean().getCollegeId()>0){
                    result = new ReceiverGroupDAO().updateReceiverGroup(receiverGroupBean);
            }
            return result;
        }
        
        public ReceiverGroupBean viewReceiverGroup(short receiverGroupId) {
            ReceiverGroupBean receiverGroupBean = null;
        
            if (receiverGroupId > 0) {
                receiverGroupBean = new ReceiverGroupDAO().viewReceiverGroup(receiverGroupId);
            }
            return receiverGroupBean;
        }

        public ReceiverGroupBean viewReceiverGroup(String receiverGroupName) {
            ReceiverGroupBean receiverGroupBean = null;
        
            if (receiverGroupName != null) {
                receiverGroupBean = new ReceiverGroupDAO().viewReceiverGroup(receiverGroupName);
            }
            return receiverGroupBean;
        }
        
        public ArrayList<ReceiverGroupBean> viewReceiverGroup(byte collegeId) {
            
            ArrayList<ReceiverGroupBean> receiverGroupBeanList = null;
        
            if (collegeId > 0) {
                receiverGroupBeanList = new ReceiverGroupDAO().viewReceiverGroup(collegeId);
            }
            return receiverGroupBeanList;
        }

        public ArrayList<ReceiverGroupBean> viewReceiverGroup(){
            return new ReceiverGroupDAO().viewReceiverGroup();
        }
        
        public static void main(String[] args) {
//            ReceiverGroupBean receiverGroupBean = new ReceiverGroupBean();
//            //System.out.println(new ReceiverGroupAdministrator().viewReceiverGroup("ACTIVE").getReceiverGroupBean());
//            //receiverGroupBean.setReceiverGroupBean(new ReceiverGroupAdministrator().viewReceiverGroup("ACTIVE").getReceiverGroupBean());
//            receiverGroupBean.setReceiverGroupId((short)1);
//            receiverGroupBean.setReceiverGroupName("staff");
//            CollegeBean collegeBean = new CollegeBean();
//            collegeBean.setCollegeId((byte)1);
//            receiverGroupBean.setCollegeBean(collegeBean);
//            StatusBean statusBean = new StatusBean();
//            statusBean.setStatusId((byte)1);
//            receiverGroupBean.setStatusBean(statusBean);
//
//            System.out.println(new ReceiverGroupAdministrator().addReceiverGroup(receiverGroupBean));
//            //System.out.println(new ReceiverGroupAdministrator().updateReceiverGroup(receiverGroupBean));
//            //System.out.println(new ReceiverGroupAdministrator().viewReceiverGroup((short)1));
//            //System.out.println(new ReceiverGroupAdministrator().viewReceiverGroup((short)5).getReceiverGroupName());
//            //System.out.println(new ReceiverGroupAdministrator().viewReceiverGroup("test").getStatusBean().getStatusId());
//            ArrayList<ReceiverGroupBean> receiverGroupBeanList = new ReceiverGroupAdministrator().viewReceiverGroup((byte)4);
//            for(int i=0;i<receiverGroupBeanList.size();i++){
//                System.out.println(receiverGroupBeanList.get(i).getStatusBean().getStatusId());
//            }
//            //System.out.println(new ReceiverGroupAdministrator().viewReceiverGroup().iterator().next().getReceiverGroupName());
//            //System.out.println(new ReceiverGroupAdministrator().viewReceiverGroup());
 } 

    }
