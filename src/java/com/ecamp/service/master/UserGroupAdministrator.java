
package com.ecamp.service.master;


import java.util.ArrayList;

import com.ecamp.bean.master.UserGroupBean;
import com.ecamp.bean.master.CollegeBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.dao.master.UserGroupDAO;

public class UserGroupAdministrator {

    public String addUserGroup(UserGroupBean userGroupBean) {
            String result="FAILURE";
            if (userGroupBean==null || userGroupBean.getUserGroupId() == 0 || userGroupBean.getUserGroupName()==null || userGroupBean.getUserGroupName().isEmpty() || userGroupBean.getCollegeBean().getCollegeId() == 0){
                result = "addINVALID";
            }
            else if(new UserGroupDAO().addUserGroup(userGroupBean)){
                result = "SUCCESS";
            }
            return result;
        }
    
        public String updateUserGroup(UserGroupBean userGroupBean)  {
            String result = "upadateINVALID";

            if(userGroupBean != null && userGroupBean.getUserGroupName() != null && !userGroupBean.getUserGroupName().isEmpty() && userGroupBean.getCollegeBean().getCollegeId()>0){
                    result = new UserGroupDAO().updateUserGroup(userGroupBean);
            }
            return result;
        }
        
        public UserGroupBean viewUserGroup(short userGroupId) {
            UserGroupBean userGroupBean = null;
        
            if (userGroupId > 0) {
                userGroupBean = new UserGroupDAO().viewUserGroup(userGroupId);
            }
            return userGroupBean;
        }

        public UserGroupBean viewUserGroup(String userGroupName) {
            UserGroupBean userGroupBean = null;
        
            if (userGroupName != null) {
                userGroupBean = new UserGroupDAO().viewUserGroup(userGroupName);
            }
            return userGroupBean;
        }
        
        public ArrayList<UserGroupBean> viewUserGroup(byte collegeId) {
            
            ArrayList<UserGroupBean> userGroupBeanList = null;
        
            if (collegeId > 0) {
                userGroupBeanList = new UserGroupDAO().viewUserGroup(collegeId);
            }
            return userGroupBeanList;
        }

        public ArrayList<UserGroupBean> viewUserGroup(){
            return new UserGroupDAO().viewUserGroup();
        }
        
        public static void main(String[] args) {
            UserGroupBean userGroupBean = new UserGroupBean();
            //System.out.println(new UserGroupAdministrator().viewUserGroup("ACTIVE").getUserGroupBean());
            //userGroupBean.setUserGroupBean(new UserGroupAdministrator().viewUserGroup("ACTIVE").getUserGroupBean());
            userGroupBean.setUserGroupId((short)1);
            userGroupBean.setUserGroupName("admin");
            CollegeBean collegeBean = new CollegeBean();
            collegeBean.setCollegeId((byte)1);
            userGroupBean.setCollegeBean(collegeBean);
            StatusBean statusBean = new StatusBean();
            statusBean.setStatusId((byte)2);
            userGroupBean.setStatusBean(statusBean);

           // System.out.println(new UserGroupAdministrator().addUserGroup(userGroupBean));
//            //System.out.println(new UserGroupAdministrator().updateUserGroup(userGroupBean));
//            //System.out.println(new UserGroupAdministrator().viewUserGroup((short)8).getUserGroupName());
            System.out.println(new UserGroupAdministrator().viewUserGroup(""));
//            ArrayList<UserGroupBean> userGroupBeanList = new UserGroupAdministrator().viewUserGroup();
//            for(int i=0;i<userGroupBeanList.size();i++){
//                System.out.println(userGroupBeanList.get(i).getUserGroupName());
//            }
//            //System.out.println(new UserGroupAdministrator().viewUserGroup().iterator().next().getUserGroupName());
//            //System.out.println(new UserGroupAdministrator().viewUserGroup());
 } 
}
