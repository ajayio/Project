<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="assets/ckeditor/ckeditor.js"></script>
        <link href="assets/bootstrap-4/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/Compose.css" />
        
        <title></title>
        <style>

        </style>
        
    </head>
    <body>
        <%
                HttpSession httpSession=null;
                httpSession=request.getSession();
                boolean val = httpSession.getAttribute("username") == null;                                
                if(!val){                                    
                    String username = (String)httpSession.getAttribute("username");
                    if(!username.isEmpty() && !username.equals("") && !val){
                %>                 
            <div class="composeMain">                       
                <form action="CommonServlet" method="POST" autocomplete="off">
                    <label for=""></label>
                    <input list="ReceiverGroupInput" id="receiverList" name ="ReceiverGroupInput"
                             onkeyup="loadList1('UserGroup', 'Name', 'ReceiverGroupInput','receiverList');">
                        <datalist id="ReceiverGroupInput"></datalist>  
                        <button type="button" onclick="addReceiver();" onblur="addValToInput()" id="Add">Add</button>
                        <p id="error"></p>
                        <br />
                        <div class="list">
                            <ul id="listUL" name="listUL">                                                        
                                 
                            </ul>
                         </div>                      
                        <p id="message1"></p>                                                                
                        <input name="receiverGroupListVal" id="listValues" />   
                        <button type="button">New UserGroup</button>
                        <br />
                        <br />
                        <label for="validFrom">Valid From</label>
                        <input type="date" name="validFrom" id="validFrom" onblur="checkValidFrom()" required/>
                        <br /><br />
                        <label for="validFrom">Valid To</label>
                        <input type="date" name="validTo" id="validTo" onblur="checkValidTo()">
                        <br /><br />
                        <p id="dateError"></p>
                        <label for="title">Title</label>
                        <input type="text" name="title" id="title" onblur="document.getElementById('subject').focus()" required=""/>
                        <br /><br />
                        <label for="subject">Subject</label>
                        <textarea class="subject" name="subject" id="subject" rows="10" cols="100"></textarea>
                        <br />
                        <br />
                        <input type="reset" value="Clear"/>
                        <input type="submit" value="send" id="send" name="option" onfocus="checkList()"/>
                </form>
            </div>
            <a href="CircularLogout.jsp">Logout </a>    
                <%
                }
               }
              else{        
            %>
            <a href="CircularLogin.jsp">Login To continue</a>    
            <%
              }  
            %>     
            <script src="assets/ComposeScript.js" ></script>    
        <script src="assets/Compose.js"></script>   
    </body>
</html>
