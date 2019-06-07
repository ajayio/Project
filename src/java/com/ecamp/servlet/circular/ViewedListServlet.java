package com.ecamp.servlet.circular;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecamp.bean.circular.ViewedListBean;
import com.ecamp.service.circular.ViewedListAdministrator;
import java.util.ArrayList;

/**
 *
 * @author Student
 */
public class ViewedListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        String option = request.getParameter("option");
        String target;  
        String submitForm = request.getParameter("submit");
        ArrayList<ViewedListBean> viewedListLists = null; 
        PrintWriter out = response.getWriter();
        String responseText = "";    
        if(submitForm.equals("Submit")){
            if(option!=null){
                if(option.equals("circularId")){
                    target = request.getParameter("target");
                    int circularId = Integer.parseInt(target);
                    //out.println(circularId);
                    //out.println(viewedListLists);
                    viewedListLists = new ViewedListAdministrator().viewViewedListByCircular(circularId);
                    //out.println(viewedListLists);
                    //out.println(!viewedListLists.isEmpty());
                    if(!viewedListLists.isEmpty()){
                        for(int i=0;i<viewedListLists.size();i++){
                            responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                            responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                            responseText+=viewedListLists.get(i).getViewedTime()+"|";
                            responseText+=viewedListLists.get(i).getMarked()+"|";
                        }
                    }
                    else{
                        responseText += "NOT AVAILABLE";
                    }
                    out.println(responseText);
                }    
                if(option.equals("userId")){
                    target = request.getParameter("target");
                    int userId = Integer.parseInt(target);
                    //out.println(userId);
                    //out.println(viewedListLists);
                    viewedListLists = new ViewedListAdministrator().viewViewedListByUser(userId);
                    //out.println(viewedListLists);
                    //out.println(!viewedListLists.isEmpty());
                    if(!viewedListLists.isEmpty()){
                        for(int i=0;i<viewedListLists.size();i++){
                            responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                            responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                            responseText+=viewedListLists.get(i).getViewedTime()+"|";
                            responseText+=viewedListLists.get(i).getMarked()+"|";
                        }
                    }
                    else{
                        responseText += "NOT AVAILABLE";
                    }
                    out.println(responseText);
                }
                    
                if(option.equals("marked")){
                    String markedOption = request.getParameter("markedOption");
                    if(!markedOption.equals("selectOption")){
                        int marked = markedOption.equals("yes")?1:0;
                        //out.println(marked);
                        viewedListLists = new ViewedListAdministrator().viewViewedListByMarked((byte)marked);
                        //out.println(viewedListLists);
                        //out.println(!viewedListLists.isEmpty());
                        if(!viewedListLists.isEmpty()){
                            for(int i=0;i<viewedListLists.size();i++){
                                responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                                responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                                responseText+=viewedListLists.get(i).getViewedTime()+"|";
                                responseText+=viewedListLists.get(i).getMarked()+"|";
                            }
                        }
                        else{
                            responseText += "NOT AVAILABLE";
                        }
                    }
                    out.println(responseText);
                }
                    
                if(option.equals("viewAll")){
                    viewedListLists = new ViewedListAdministrator().viewViewedList();
                    //out.println(viewedListLists);
                    //out.println(!viewedListLists.isEmpty());
                    if(!viewedListLists.isEmpty()){
                        for(int i=0;i<viewedListLists.size();i++){
                            responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                            responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                            responseText+=viewedListLists.get(i).getViewedTime()+"|";
                            responseText+=viewedListLists.get(i).getMarked()+"|";
                        }
                    }
                    else{
                        responseText += "NOT AVAILABLE";
                    }
                    out.println(responseText);
                }
            }
        }            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
        response.setContentType("text/html");
        ArrayList<ViewedListBean> viewedListLists = null;
        //Iterator<ViewedListBean> iterator=null;  
        
        PrintWriter out = response.getWriter();
        String responseText = "";
        String submitForm = request.getParameter("submit");
        
        if(submitForm.equals("viewlist")){
            String viewListOption = request.getParameter("viewListOption");
            out.println(viewListOption);
            if(viewListOption.equals("selectOption")){
                response.sendRedirect("circular/adminhome.jsp");
            }
            else{
                if(viewListOption!=null){
                    if(viewListOption.equals("circularId")){
                        int circularId=Integer.parseInt(request.getParameter("value"));
                        out.println(circularId);
                        out.println(viewedListLists);
                        viewedListLists = new ViewedListAdministrator().viewViewedListByCircular(circularId);
                        out.println(viewedListLists);
                        out.println(!viewedListLists.isEmpty());
                        if(!viewedListLists.isEmpty()){
                            //iterator = viewedListByCircularList.iterator();
                            for(int i=0;i<viewedListLists.size();i++){
                                responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                                responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                                responseText+=viewedListLists.get(i).getViewedTime()+"|";
                                responseText+=viewedListLists.get(i).getMarked()+"|";
                            }
                            response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                        }
                        else{
                            responseText += "NOT AVAILABLE";
                            response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                        }
                    }
                    if(viewListOption.equals("userId")){
                        int userId = Integer.parseInt(request.getParameter("value"));
                        out.println(userId);
                        out.println(viewedListLists);
                        viewedListLists = new ViewedListAdministrator().viewViewedListByUser(userId);
                        out.println(viewedListLists);
                        out.println(!viewedListLists.isEmpty());
                        if(!viewedListLists.isEmpty()){
                            //iterator = viewedListByCircularList.iterator();
                            for(int i=0;i<viewedListLists.size();i++){
                                responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                                responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                                responseText+=viewedListLists.get(i).getViewedTime()+"|";
                                responseText+=viewedListLists.get(i).getMarked()+"|";
                            }
                            response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                        }
                        else{
                            responseText += "NOT AVAILABLE";
                            response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                        }
                    }
                    if(viewListOption.equals("marked")){
                        String markedOption = request.getParameter("markedOption");
                        if(markedOption.equals("selectOption")){
                            response.sendRedirect("circular/adminhome.jsp");
                        }else{
                            int marked = markedOption.equals("yes")?1:0;
                            out.println(marked);
                            out.println(viewedListLists);
                            viewedListLists = new ViewedListAdministrator().viewViewedListByMarked((byte)marked);
                            out.println(viewedListLists);
                            out.println(!viewedListLists.isEmpty());
                            if(!viewedListLists.isEmpty()){
                                //iterator = viewedListByCircularList.iterator();
                                for(int i=0;i<viewedListLists.size();i++){
                                    responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                                    responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                                    responseText+=viewedListLists.get(i).getViewedTime()+"|";
                                    responseText+=viewedListLists.get(i).getMarked()+"|";
                                }
                                response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                            }
                            else{
                                responseText += "NOT AVAILABLE";
                                response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                            }
                        }
                    }
                    if(viewListOption.equals("viewAll")){
                        out.println(viewedListLists);
                        viewedListLists = new ViewedListAdministrator().viewViewedList();
                        out.println(viewedListLists);
                        out.println(!viewedListLists.isEmpty());
                        if(!viewedListLists.isEmpty()){
                            //iterator = viewedListByCircularList.iterator();
                            for(int i=0;i<viewedListLists.size();i++){
                                responseText+=viewedListLists.get(i).getCircularBean().getCircularId()+"|";
                                responseText+=viewedListLists.get(i).getUserBean().getUserId()+"|";
                                responseText+=viewedListLists.get(i).getViewedTime()+"|";
                                responseText+=viewedListLists.get(i).getMarked()+"|";
                            }
                            response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                        }
                        else{
                            responseText += "NOT AVAILABLE";
                            response.sendRedirect("circular/content/viewedlist.jsp?Message="+responseText);
                        }
                    }
                }
            }
        }
        else{
            response.sendRedirect("circular/adminhome.jsp");
        }
    }
}
