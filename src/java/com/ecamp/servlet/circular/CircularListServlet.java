/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.servlet.circular;

import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.circular.CircularListBean;
import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.service.circular.CircularListAdministrator;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
public class CircularListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String responseText="";        
        
        String Option = (String) request.getAttribute("option");
        CircularListBean circularListBean = new CircularListBean();
            CircularBean circularBean = new CircularBean();
                circularBean.setCircularId(Integer.parseInt(request.getAttribute("circularId").toString()));                                
                circularListBean.setCircularBean(circularBean);
            ReceiverGroupBean receiverGroupBean = new ReceiverGroupBean();
                receiverGroupBean.setReceiverGroupId((short)2);
            circularListBean.setReceiverGroupBean(receiverGroupBean);
          CircularListAdministrator circularListAdministrator = new CircularListAdministrator();
            String result = circularListAdministrator.addCircularList(circularListBean);
            
            
             try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Circular List Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet1 at " + result + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }                  
//        if(Option.equals("send")){
//           String result = new CircularListAdministrator().addCircularList(circularListBean);      
//
//           switch(result){
//              case "SUCCESS":
//                responseText = "success";
//                break;
//              case "INVALID":
//                responseText = "invalid";
//                break;
//              case "FAILURE":
//                responseText = "failure";
//                break; 
//           }                     
//           response.sendRedirect("Compose.jsp?Message="+responseText);
//        }
//        else if(Option.equals("Update")){
            //String result = new CircularAdministrator().updateCircular(circularBean);
//            switch(result){
//                case "SUCCESS":
//                  responseText = "success update";
//                  break;
//                case "INVALID":
//                  responseText = "invalid update";
//                  break;
//                case "FAILURE":
//                  responseText = "failure update";
//                  break; 
//           }
        //}                                
        //response.sendRedirect("CommonServlet");
    }
}

