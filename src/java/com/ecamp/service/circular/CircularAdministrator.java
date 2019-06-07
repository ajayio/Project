package com.ecamp.service.circular;

import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserBean;
import com.ecamp.dao.circular.CircularDAO;
import java.util.Date;
import java.util.ArrayList;

public class CircularAdministrator {
    
    
    
    public String addCircular(CircularBean circularBean){        
        String result="FAILURE";
        
        int circularId = 0;        
        if(circularBean == null || circularBean.getCircularDate() ==null || circularBean.getTitle() ==null || circularBean.getTitle().isEmpty() ||
           circularBean.getSubject()==null || circularBean.getSubject() == null || circularBean.getSubject().isEmpty() ||
           circularBean.getValidFrom() ==null || circularBean.getUserBean().getUserId() <= 0 ||
           circularBean.getStatusBean().getStatusId() <= 0) {
            result = "INVALID";
        }      
        else if(new CircularDAO().addCircular(circularBean)){                       
                result = "SUCCESS";                
        }        
        return result;
    }    
    public String updateCircular(CircularBean circularBean){
        String result = "FAILURE";
        if(circularBean ==null ||  circularBean.getValidFrom() == null || circularBean.getValidTo() == null){
            result = "INVALID";
        }
        else{
            result = new CircularDAO().updateCircular(circularBean);
        }        
        return result;
    }
    
    public CircularBean viewCircularByCircular(int circularId){
        
        CircularBean circularBean = null;
        if(circularId > 0){
            circularBean = new CircularDAO().viewCircularByCircularId(circularId);
        }        
        return circularBean;      
    }
    
    public ArrayList<CircularBean> viewCircularByUser(int userId){
        
        CircularBean circularBean = null;
        ArrayList<CircularBean> circularBeanList = null;
        if(userId > 0){
            circularBeanList =  new CircularDAO().viewCircularByUserId(userId);
        }        
        return circularBeanList;
    }
    
    public ArrayList<CircularBean> viewCircular(Date circularDate){
        
        CircularBean circularBean = null;
        ArrayList<CircularBean> circularBeanList = null;
        if(circularDate != null){
            circularBeanList = new CircularDAO().viewCircularByDate(circularDate);
        }
        return circularBeanList;        
    }
    
    public ArrayList<CircularBean> viewCircular(String title){
        
        CircularBean circularBean = null;
        ArrayList<CircularBean> circularBeanList = null;
        if(title != null && !title.isEmpty()){
            circularBeanList = new CircularDAO().viewCircularByTitle(title);
        }
        return circularBeanList;
    }
    
    public ArrayList<CircularBean> viewCircularValidFrom(Date validFrom){
        
        CircularBean circularBean = null;
        ArrayList<CircularBean> circularBeanList = null;
        if(validFrom !=null){
            circularBeanList = new CircularDAO().viewCircularValidFrom(validFrom);
        }
        return circularBeanList;
    }
    
    public ArrayList<CircularBean> viewCircularValidTo(Date validTo){
        
        CircularBean circularBean = null;
        ArrayList<CircularBean> circularBeanList = null;
        if(validTo !=null){
            circularBeanList = new CircularDAO().viewCircularValidTo(validTo);
        }
        return circularBeanList;
    }
    
    public ArrayList<CircularBean> viewCircularByValidFromTo(Date circularFrom, Date circularTo){
        
        CircularBean circularBean = null;
        ArrayList<CircularBean> circularBeanList = null;
        if(circularFrom!=null && circularTo!=null){
            circularBeanList = new CircularDAO().viewCircularByValidFromTo(circularFrom, circularTo);
        }        
        return circularBeanList;
        
    }
    
    
    public ArrayList<CircularBean> viewCircular(){
        
        return new CircularDAO().viewCircular();        
    }
    
    public static void main(String[] args) {
        
//        CircularAdministrator administrator = new CircularAdministrator();
//        ArrayList<CircularBean> circularBeanList = new ArrayList<CircularBean>();
//        circularBeanList = administrator.viewCircular("TITLE");
//        System.out.println(circularBeanList);
//       
//       CircularBean circularBean = new CircularBean();
//           circularBean.setCircularId(6);                                  
//           circularBean.setCircularDate(new java.util.Date());  
//           circularBean.setTitle("Holiday1");
//            circularBean.setSubject("Tomorrow is a hoilday");            
//            circularBean.setValidFrom(new java.util.Date());
//            circularBean.setValidTo(null);
//        UserBean userBean = new UserBean();
//            userBean.setUserId(2);
//        circularBean.setUserBean(userBean);
//        StatusBean statusBean = new StatusBean();
//            statusBean.setStatusId((byte)2);
//            statusBean.setStatusName("alive");
//            statusBean.setDescription("this is alive");
//        circularBean.setStatusBean(statusBean);        
//        System.out.println(new CircularAdministrator().addCircular(circularBean));
        
    }
    
}
