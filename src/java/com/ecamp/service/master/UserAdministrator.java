
package com.ecamp.service.master;

import com.ecamp.bean.master.ContactBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserBean;
import com.ecamp.dao.master.UserDAO;

public class UserAdministrator {
    
    public String addUser(UserBean userBean ){
        
        String result = "FAILURE";                
        if(userBean == null | userBean.getUserName().isEmpty() | userBean.getUserName() == null | userBean.getPassword().isEmpty() | userBean.getPassword() == null | userBean.getContactBean().getContactId() <= 0 |
                userBean.getStatusBean().getStatusId() <= 0){
                result = "INVALID";
        }
        else if(new UserDAO().addUser(userBean)){            
            result = "SUCCESS";            
        }        
        return result;
    }
    
    
    public UserBean viewByUserName(String userName){
        
        UserBean userBean = new UserBean();
        if(!userName.isEmpty() && userName!=null){
            userBean = new UserDAO().viewByUserName(userName);
        }        
        return userBean;
    }
    
    
     public static void main(String[] argumensts){
         
//          UserDAO userDAO = new UserDAO();
//        UserBean userBean = new UserBean();
//            userBean.setUserName("name new");
//            userBean.setPassword("password new");
//            userBean.setLastUpdated(new java.util.Date());
//        ContactBean contactBean = new ContactBean();
//            contactBean.setContactId(3);
//            userBean.setContactBean(contactBean);
//        StatusBean statusBean = new StatusBean();
//            statusBean.setStatusId((byte)4);
//            userBean.setStatusBean(statusBean);                
//            
//          System.out.println(new UserAdministrator().addUser(userBean));
//            UserBean userBean = new UserBean();
//            userBean = new UserAdministrator().viewByUserName("");
//            System.out.println(userBean.getPassword());
            
          
         
         
     }
    
}
