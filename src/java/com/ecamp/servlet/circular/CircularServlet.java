/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.servlet.circular;

import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserBean;
import com.ecamp.service.circular.CircularAdministrator;
import com.ecamp.service.master.StatusAdministrator;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Student
 */
public class CircularServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         response.setContentType("text/html");        
        ArrayList<CircularBean> circularList=null;
        Iterator<CircularBean> iterator=null;

        
        String column=request.getParameter("column");        
        String target=request.getParameter("target");
            //response.getWriter().print(column);
        //String column = "Name";
        //String target = "";      
            String responseText="";
            circularList=new CircularAdministrator().viewCircular();
                if(!circularList.isEmpty()){
                    iterator=circularList.iterator();          
                    switch(column){           
                        case "Name" :
                            while(iterator.hasNext()){
                                String title=iterator.next().getTitle();
                                if(target!=null){
                                    if(title.startsWith(target)){
                                        if(iterator.hasNext()){
                                            responseText=responseText+title+"|";
                                        }else{
                                            responseText=responseText+title;
                                        }
                                    }
                                }else if(iterator.hasNext()){
                                    responseText=responseText+title+"|";
                                }
                                else{
                                    responseText=responseText+title;
                                }
                            }
                            break;
                        case "All" :                            
                            StatusBean statusBean=new StatusAdministrator().viewStatus(target);     
                            responseText=statusBean.getStatusName()+"|"+statusBean.getDescription();
                            break;
                    }
                }
            response.getWriter().print(responseText);        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //validFrom, validTo, title, subject, circularDate, userId, statusId,     
        
        //valid From must be lesser tham valid To
        
        
        response.setContentType("text/html");
        String responseText="";        
        
        String Option = request.getParameter("option");
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date validFrom = null, validTo = null;
        try{
            validFrom = simpleDateFormat.parse(request.getParameter("validFrom"));
            if(request.getParameter("validTo") == ""){
                validTo = null;
            }
            else{
                validTo = simpleDateFormat.parse(request.getParameter("validTo"));
            }            
        }   
        catch(ParseException e){
            System.out.println(e);            
        }
        String title = request.getParameter("title");
       String subject = request.getParameter("subject");
        Date circularDate = new Date();        
        CircularBean circularBean = new CircularBean();
        circularBean.setCircularId(1);
            circularBean.setCircularDate(circularDate);
            circularBean.setTitle(title);
            circularBean.setSubject(subject);            
            if(validTo != null){                           
                if(validFrom.compareTo(validTo)<=0){
                    circularBean.setValidFrom(validFrom);
                    circularBean.setValidTo(validTo);
                }            
            }
            else{
                circularBean.setValidFrom(validFrom);
                circularBean.setValidTo(validTo);
            }
        UserBean userBean = new UserBean();
            userBean.setUserId(1);
           circularBean.setUserBean(userBean);
        StatusBean statusBean = new StatusBean();
            statusBean.setStatusId((byte)1);
           circularBean.setStatusBean(statusBean);
           String redirectFrom = null;
        if(Option.equals("send")){
           String result = new CircularAdministrator().addCircular(circularBean); 
           int circularId = circularBean.getCircularId();
           redirectFrom = "CircularServlet";
           switch(result){
              case "SUCCESS":
                responseText = "success";                
                request.setAttribute("circularId", circularId);
                request.setAttribute("redirectFrom", redirectFrom);
                break;
              case "INVALID":
                responseText = "invalid";
                break;
              case "FAILURE":
                responseText = "failure";
                break; 
           }
           
           String receiverGroupList[] = (String[]) request.getAttribute("receiverGroupList");                                
        }
        else if(Option.equals("Update")){
            String result = new CircularAdministrator().updateCircular(circularBean);
            switch(result){
                case "SUCCESS":
                  responseText = "success update";
                  break;
                case "INVALID":
                  responseText = "invalid update";
                  break;
                case "FAILURE":
                  responseText = "failure update";
                  break; 
           }
        }                                
        //response.sendRedirect("Compose.jsp?Message="+responseText +""+redirectFrom);                
        RequestDispatcher rd = request.getRequestDispatcher("CommonServlet"); 
        rd.forward(request,response); 
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    

}
