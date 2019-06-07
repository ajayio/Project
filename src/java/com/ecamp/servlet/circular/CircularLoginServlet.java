/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.servlet.circular;

import com.ecamp.bean.master.UserBean;
import com.ecamp.service.master.UserAdministrator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Student
 */
public class CircularLoginServlet extends HttpServlet {

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
        UserBean userBean = new UserBean();
        userBean = new UserAdministrator().viewByUserName(username);        
        if(userBean!=null && !userBean.getPassword().isEmpty()){
            if(userBean.getPassword().equals(password)){
                //responseText = "Logged In";                        
                response.sendRedirect("circular/Compose.jsp");
            }    
            else{
                responseText = "Check your username and password";
                response.sendRedirect("circular/CircularLogin.jsp?message="+responseText);
            }
        }        
        else{
            responseText = "Check your username and password";
            response.sendRedirect("circular/CircularLogin.jsp?message="+responseText);
        }        
        //response.sendRedirect("login.jsp?message="+responseText);
        
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
