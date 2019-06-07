package com.ecamp.service.circular;

//import java.util.Date;

import com.ecamp.bean.master.UserBean;
import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.circular.ViewedListBean;
import com.ecamp.dao.circular.ViewedListDAO;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewedListAdministrator {
    
    public String addViewedList (ViewedListBean viewedListBean){
        String result = "FAILURE";
        if( viewedListBean == null || viewedListBean.getCircularBean().getCircularId() <= 0 || viewedListBean.getUserBean().getUserId() <= 0 || viewedListBean.getViewedTime() == null ||
                (viewedListBean.getMarked() != 0 && viewedListBean.getMarked() != 1) ){
                result = "INVALID";
        }
        else if(new ViewedListDAO().addViewedList(viewedListBean)){
            result = "SUCCESS";
        }
        return result;
    }
    
    public ArrayList<ViewedListBean> viewViewedListByCircular (int circularId){
        ArrayList<ViewedListBean> viewedListByCircularList= null;
        if(circularId > 0){
            viewedListByCircularList = new ViewedListDAO().viewViewedListByCircular(circularId);
        }
        return viewedListByCircularList;
    }
    
    public ArrayList<ViewedListBean> viewViewedListByUser (int userId){
        ArrayList<ViewedListBean> viewedListByUserList= null;
        if(userId > 0){
            viewedListByUserList = new ViewedListDAO().viewViewedListByUser(userId);
        }
        return viewedListByUserList;
    }
    
//    public ArrayList<ViewedListBean> viewViewedListByDate (Date dateFrom, Date dateTo){
//        ArrayList<ViewedListBean> viewedListByDateList= null;
//        if(dateFrom != null && dateTo != null){
//            viewedListByDateList = new ViewedListDAO().viewViewedListByDate(dateFrom, dateTo);
//        }
//        return viewedListByDateList;
//    }
    
    public ArrayList<ViewedListBean> viewViewedListByMarked (byte marked){
        ArrayList<ViewedListBean> viewedListByMarkedList = null;
        if(marked == 0 || marked == 1){
            viewedListByMarkedList = new ViewedListDAO().viewViewedListByMarked(marked);
        }
        return viewedListByMarkedList;
    }
    
    public ArrayList<ViewedListBean> viewViewedList () {
        return new ViewedListDAO().viewViewedList();
    }
    
    public static void main(String[] args) {
        ViewedListAdministrator viewedListAdministrator = new ViewedListAdministrator();
        ViewedListBean viewedListBean = new ViewedListBean();
        CircularBean ciruclarBean = new CircularBean();
        UserBean userBean = new UserBean();
        ArrayList<ViewedListBean> viewedListServiceList = new ArrayList<ViewedListBean>();
        
        
        /* Block Test addViewedList */
        
        viewedListBean.setCircularBean(ciruclarBean);
        ciruclarBean.setCircularId(1234);
        viewedListBean.setUserBean(userBean);
        userBean.setUserId(4321);
        viewedListBean.setViewedTime(new java.util.Date());
        viewedListBean.setMarked((byte)0); 
        
        System.out.println(viewedListAdministrator.addViewedList(viewedListBean)); 
        
        
        
        viewedListServiceList  = viewedListAdministrator.viewViewedListByCircular(100);
        //viewedListServiceList = viewedListAdministrator.viewViewedListByUser(10203);
        //viewedListServiceList = viewedListAdministrator.viewViewedListByMa+rked((byte) 0);
        //viewedListServiceList = viewedListAdministrator.viewViewedList();
        
        // Date dateFrom = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse("2013-3-22 10:13:00");
        // Date dateTo = new SimpleDateFormat("yy-MM-dd HH:mm:ss").parse("2019-3-22 10:13:00");
        
       // viewedListServiceList = viewedListAdministrator.viewViewedListByDate(dateFrom, dateTo);  // Date Time
        
        System.out.println(viewedListServiceList );
        System.out.println(viewedListServiceList .size());
        
        for(int i=0;i<viewedListServiceList .size();i++){
             System.out.println(viewedListServiceList.get(i).getCircularBean().getCircularId());
             System.out.println(viewedListServiceList.get(i).getUserBean().getUserId());
             System.out.println(viewedListServiceList.get(i).getViewedTime());
             System.out.println(viewedListServiceList.get(i).getMarked());
        } 
    }
    
}
