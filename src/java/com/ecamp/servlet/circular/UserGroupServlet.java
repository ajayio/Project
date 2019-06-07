/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamp.servlet.circular;

import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.master.StatusBean;
import com.ecamp.bean.master.UserGroupBean;
import com.ecamp.service.circular.CircularAdministrator;
import com.ecamp.service.master.StatusAdministrator;
import com.ecamp.service.master.UserGroupAdministrator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
public class UserGroupServlet extends HttpServlet {



 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
          response.setContentType("text/html");        
        ArrayList<UserGroupBean> userGroupListList=null;
        Iterator<UserGroupBean> iterator=null;
        
        //String column=request.getParameter("column");        
        //String target=request.getParameter("target");
            //response.getWriter().print(column);
        String column = "Name";
        String target = "";      
            String responseText="";
            userGroupListList=new UserGroupAdministrator().viewUserGroup();
                if(!userGroupListList.isEmpty()){
                    iterator=userGroupListList.iterator();          
                    switch(column){           
                        case "Name" :
                            while(iterator.hasNext()){
                                String title=iterator.next().getUserGroupName();
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
                            //UserGroupBean userGroupBean = new UserGroupAdministrator().viewUserGroup(target);
                            //responseText=statusBean.getStatusName()+"|"+statusBean.getDescription();
                            break;
                    }
                }
            response.getWriter().print(responseText);  
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
