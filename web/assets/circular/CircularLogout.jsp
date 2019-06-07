<%-- 
    Document   : logout
    Created on : 19 Dec, 2018, 11:04:28 PM
    Author     : Student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <<%
                HttpSession httpSession=request.getSession();
                if(httpSession!=null){
                    httpSession.setAttribute("username",null);                    
                    out.print(httpSession.getAttribute("username")+"Logged out successfully...");
                }
          %>
          <jsp:forward page="CircularLogin.jsp">
            <jsp:param name="message" value="Logout Successfully..." />
            <jsp:param name="Option" value="Circular" />
        </jsp:forward>
    </body>
</html>
