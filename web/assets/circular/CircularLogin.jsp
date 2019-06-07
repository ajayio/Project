<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
<script>
$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
</script>
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.login-page {
  min-width: 500px;
  max-width: 500px;
  padding: 4%;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #c5d5cb;
  max-width: 360px;
  padding: 40px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  min-width: 100%;
  max-width: 100%;
  border: 0;
  border-radius:5%;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  min-width: 100%;
  max-width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
  border-radius:5%;
  margin-top:10px;
}
.form button:hover {background-color: #3e8e41}
body {
  background: -moz-linear-gradient(right, #DDDDDD, #DDDDDD);
  background: -o-linear-gradient(right, #DDDDDD,#DDDDDD);
  background: linear-gradient(to left, #DDDDDD, #DDDDDD);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

h1{
  margin-top:-3px;
} 
input[type=text], input[type=password]
{
   border-radius:10px;
   background-color:white;
}
</style>
    <body>
        <%@include file="../head.html"%>
        <%
            String message=request.getParameter("message");
            String option=request.getParameter("Option");
            HttpSession httpSession=request.getSession();                        
            if(httpSession.getAttribute("username")==null) {
                    %>                           
                        <div class="login-page">
                            <div class="form">
                                <form class="login-form" action="../LoginServlet" method="POST">
                                    <%
                                        if(message!=null){
                                            %>
                                                <h3 align="center" style="color:red;"><%=message%></h3>
                                            <%
                                        }    
                                    %>
                                    <h1 align="center">Login</h1>
                                    <h3 align="left">Username</h3>
                                    <input type="text" placeholder="Username" name="username" id="username" autocomplete="off" required=""/>
                                    <h3 align="left">Password</h3>
                                    <input type="password" placeholder="Password" name="password" id="password" autocomplete="off" required=""/>
                                    <button name="Option" value="<%=option%>">login</button>
                                </form>
                            </div>
                        </div>                
                    <%
               
            }else if(option.equals("Trans")){
            %>
                <jsp:forward page="../index.html"></jsp:forward>
            <%    
            }else if(option.equals("Circular")){
            %>
            <jsp:forward page="../index.html"></jsp:forward>
            <%   
            }
        %>
        <%@include file="../foot.html"%>
        <script src="../mylib/js/jquery.js" type="text/javascript">
        <script src="../mylib/js/bootstrap.min.js" type="text/javascript">
        <script>
            document.getElementById("password").addEventListener('onkeypress', function(e){
                console.log(e);
            });
        </script>
    </body>
</html>