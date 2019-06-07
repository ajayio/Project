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
import com.ecamp.service.circular.ReceiverGroupAdministrator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
public class CommonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String redirectFrom = (String)request.getAttribute("redirectFrom") +"";
        if(!redirectFrom.isEmpty()){
            doPost(request, response);
        }                    
//        response.setContentType("text/html");          
//        String values = request.getParameter("receiverGroupListVal");
//        String val[] = values.split("%");
        
//         try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet NewServlet1</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet NewServlet1 at ");            
//            out.println("<p>"+val.length+"</p>");             
//            out.println("</h1>");
//            out.println("</body>");
//            out.println("</html>");
//           }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        response.setContentType("text/html");         
        
            String redirectFrom = (String)request.getAttribute("redirectFrom") +"";
            if(redirectFrom.equals("CircularServlet")){
                int circularId = (int)request.getAttribute("circularId");          
                String values = request.getParameter("receiverGroupListVal");
                String val[] = values.split("%");
                request.setAttribute("reciverGroup", val);
                
                ReceiverGroupAdministrator receiverGroupAdministrator = new ReceiverGroupAdministrator();
                ReceiverGroupBean receiverGroupBean = new ReceiverGroupBean();                                
                CircularListBean circularListBean = new CircularListBean();
                CircularListAdministrator circularListAdministrator = new CircularListAdministrator();
                CircularBean circularBean = new CircularBean();                
                int receiverArr[] = new int[val.length];
                for(int i=0; i<receiverArr.length; i++){                    
                    receiverGroupBean = receiverGroupAdministrator.viewReceiverGroup(val[i]);                    
                    if(receiverGroupBean != null ){
                         circularBean.setCircularId(circularId);
                         receiverGroupBean.setReceiverGroupId(receiverGroupBean.getReceiverGroupId());
                         circularListBean.setCircularBean(circularBean);
                         circularListBean.setReceiverGroupBean(receiverGroupBean);
                         circularListAdministrator.addCircularList(circularListBean);
                    }                    
                }         
            }            
            else{                      
                request.setAttribute("option", request.getParameter("option"));
                RequestDispatcher rd = request.getRequestDispatcher("CircularServlet");
                rd.forward(request,response);                            
            }           
                                                                         
//         if(request.getAttribute("redirectFrom").toString().equals("") && request.getAttribute("redirectFrom").toString().equals("CircularServlet")){
//             try (PrintWriter out = response.getWriter()) {            
//                    out.println("<!DOCTYPE html>");
//                    out.println("<html>");
//                    out.println("<head>");
//                    out.println("<title>Servlet NewServlet1</title>");            
//                    out.println("</head>");
//                    out.println("<body>");
//                    out.println("<h1>Servlet NewServlet1 at ");            
//                    out.println("</h1>");
//                    out.println("</body>");
//                    out.println("</html>");
//            }
//         }
//         
              
//        rd = request.getRequestDispatcher("CircularListServlet");
//        rd.forward(request,response);             
        
        
        
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
