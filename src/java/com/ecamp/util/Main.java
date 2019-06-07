
package com.ecamp.util;

import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserBean;
import com.ecamp.service.circular.CircularAdministrator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws ParseException {
        
        CircularAdministrator circularAdministrator = new CircularAdministrator();
        
        
        CircularBean circularBean = new CircularBean();
            circularBean.setCircularId(1);
            //circularBean.setCircularId(2);            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
            
            circularBean.setCircularDate(new java.util.Date());
            circularBean.setTitle("Holiday new");
            circularBean.setSubject("ENJOY new ");
            circularBean.setValidFrom(new java.util.Date());
            circularBean.setValidTo(new java.util.Date());
                        
            
        UserBean userBean = new UserBean();
            userBean.setUserId(1);
            circularBean.setUserBean(userBean);
            
        StatusBean statusBean = new StatusBean();
            statusBean.setStatusId((byte)2);
            circularBean.setStatusBean(statusBean);
        //      addCircular
        System.out.println(circularAdministrator.addCircular(circularBean));
        
        //   updateCircular         
         //System.out.println(circularAdministrator.updateCircular(circularBean));
        
        //  viewCircularByCircular
        
        //System.out.println(circularAdministrator.viewCircularByCircular(0));
        
        // viewCircularByUser
        
        //System.out.println(circularAdministrator.viewCircularByUser(1));
        
        // viewCircular ==> date
        
        //System.out.println(circularAdministrator.viewCircular(new java.util.Date()));
        
        
        // viewCircular ==> title
        
        //System.out.println(circularAdministrator.viewCircular("HOLIDAY"));
        
        // viewCircularValid From
        
        //System.out.println(circularAdministrator.viewCircularValidFrom(new java.util.Date()));
        
        //viewCircularValid To 
        
        //System.out.println(circularAdministrator.viewCircularValidTo(new java.util.Date()));
        
        
        //viewCircular from to to
        //Date date1 = dateFormat.parse("2015/12/13");
        //Date date2 = dateFormat.parse("2020/12/13");
                
        //System.out.println(circularAdministrator.viewCircularByDate(date1, date2));
        
        
        
        /// viewCircular All
        //System.out.println(circularAdministrator.viewCircular());
        
        
        
        
        
        
        
    }
}
