/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.servlet.circular;

import com.ecamp.bean.master.UserBean;
import com.ecamp.bean.master.UserGroupBean;
import com.ecamp.bean.master.WorkGroupBean;
import com.ecamp.dao.master.WorkGroupDAO;
import com.ecamp.service.master.UserAdministrator;
import com.ecamp.service.master.UserGroupAdministrator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
public class UserCommonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserCommonServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UserCommonServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession httpSession=null;
        httpSession=request.getSession();
        httpSession.setAttribute("username",username);
        String responseText = "";
        int count = 0;        
        UserBean userBean = new UserBean();
        userBean = new UserAdministrator().viewByUserName(username);        
        if(userBean!=null && !userBean.getPassword().isEmpty()){
                if(userBean.getPassword().equals(password)){                          
                    UserGroupBean userGroupBean = new UserGroupBean();
                    userGroupBean = new UserGroupAdministrator().viewUserGroup("admin");
                    if(userGroupBean != null){
                       ArrayList<WorkGroupBean> workGroupBeanList = new WorkGroupDAO().viewUserGroup(userBean.getUserId());            
                       for(int i=0; i<workGroupBeanList.size(); i++){                                 
                           if(workGroupBeanList.get(i).getUserGroupId() == userGroupBean.getUserGroupId()){
                               count++;
                           }
                       }
                       if(count == 1){                            
                      
                           response.sendRedirect("circular/adminhome.jsp");
                       }
                       else{
                           response.sendRedirect("circular/Compose.jsp");
                       }
                    }  
                    else{
                        httpSession.setAttribute("username",null);
                        responseText = "Invalid";
                        response.sendRedirect("circular/CircularLogin.jsp?message="+responseText+"&Option=Circular");
                    }
                }    
                else{
                    httpSession.setAttribute("username",null);
                    responseText = "Check your username and password";
                    //response.sendRedirect("circular/CircularLogin.jsp?message="+responseText+"&Option=Circular");
                }
        }        
        else{
            httpSession.setAttribute("username",null);
            responseText = "Check your username and password";
            //response.sendRedirect("circular/CircularLogin.jsp?message="+responseText+"&Option=Circular");
        }        
        //response.sendRedirect("login.jsp?message="+responseText);
        
    }                        
        
            
         
         
          
          
        
         
//         
//                 try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserCommonServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UserCommonServlet at " + workGroupBeanList.get(0).getUserGroupId() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
         
         
    

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
