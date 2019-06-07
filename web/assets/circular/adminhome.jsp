<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat, java.util.Date"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Admin Panel</title>
      <link href="./assets/css/adminhome.css" type="text/css" rel="stylesheet" />
      <link rel="stylesheet" href="./assets/fontawesome/css/all.css">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
   <body>
       <%
                HttpSession httpSession=null;
                httpSession=request.getSession();
                boolean val = httpSession.getAttribute("username") == null;     
                SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy"); 
                Date date = new Date();                                
                if(!val){                                    
                    String username = (String)httpSession.getAttribute("username");
                    if(!username.isEmpty() && !username.equals("") && !val){
                %> 
      <header>
         <div class="header-content">
            <a href=""><img src="assets/images/ksrei.png" class="ksrei-logo"/></a>
            <h1>K.S.R Educational Institutions</h1>
            <a href=""><img src="assets/images/ecampus.jpg" class="ecampus-logo"/></a>
         </div>
      </header>
      <nav>         
         <div class="navbar">
            <div class="admin-panel">
               <div class="left-admin-panel">
                  <div>
                     <p><i class="fas fa-user"></i>&ensp;Welcome <%= username %></p>
                  </div>
               </div>
               <div class="center-admin-panel">
                  <p>Admini Panel Board</p>
               </div>
               <div class="right-admin-panel">
                  <div>
                     <p><i class="fas fa-calendar-week"></i>&ensp;<%= simpleDateFormat.format(date)%></p>
                  </div>
                  <div>
                     <p><i class="far fa-clock"></i>&ensp;<span id="time"></span></p>
                  </div>
                  <div>
                     <button type="submit"><i class="fas fa-sliders-h"></i>&nbsp;Settings</button>
                  </div>
                  <div class="logout">                      
                      <a href="CircularLogout.jsp"><button type="submit" value="logout"><i class="fas fa-sign-out-alt"></i>&nbsp;Logout</button></a>
                  </div>
               </div>
            </div>
            <div class="side-nav" id="loadURL">
               <ul>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/status1.png">Status<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/deg.png">degree<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/course.png">course<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/major.png">major<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/dept.png">department<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/batch.jpg">batch<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/staff.jpg">staff<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/student.jpg">student<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/ug.png">usergroup<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/work.png">workgroup<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/Receiver.jpg">receiver group<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/ReceiverGroupList.jpg">receiver group list<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/circular.jpg">circular<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="#" class="link"><img src="assets/images/side-nav/list.png">circular list<i class="fas fa-angle-right"></i></a></li>
                  <li><a href="viewedlist.jsp" class="link"><img src="assets/images/side-nav/circularList.png">circular viewed list<i class="fas fa-angle-right"></i></a></li>
                  
               </ul>
            </div>
         </div>
      </nav>
      <footer>
         <div class="container">
            <div class="left-panel">
               <p>Developed by department of computer science and engineering 2016 - 2020</p>
            </div>
            <div class="right-panel">
               <p>
                  <i class="fab fa-facebook-f"></i>
                  <i class="fas fa-blog"></i>
                  <i class="fab fa-instagram"></i>
                  <i class="fab fa-twitter"></i>
                  <i class="far fa-envelope"></i>
               </p>
            </div>
         </div>
      </footer>
      <div id="content"></div>
      <%
                }
               }
              else{        
            %>
            <a href="CircularLogin.jsp">Login To continue</a>            
            <%
              }  
            %> 
      <script>
         function checkTime(i) {
             if (i < 10) {
                 i = "0" + i;
             }
             return i;
         }
         function startTime() {
             var today = new Date();
             var h = today.getHours();
             var m = today.getMinutes();
             var s = today.getSeconds();
             m = checkTime(m);
             s = checkTime(s);
             document.getElementById('time').innerHTML = h + ":" + m + ":" + s;
             t = setTimeout(function() {
                 startTime()
             }, 500);
         }
         startTime();
         
        var navbar = document.getElementById("loadURL");
        var navbarList = navbar.getElementsByClassName("link");
        for (var i = 0; i < navbarList.length; i++) {
            navbarList[i].addEventListener("click", function() {
                var current = document.getElementsByClassName("link-active");
                if (current.length > 0) { 
                    current[0].className = current[0].className.replace(" link-active", "");
                }
                this.className += " link-active";
            });
        }
    </script> 
    <script src="assets/js/jquery.js"></script>
    <script src="ajax/general.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
   </body>
</html>



<div class="navbar">
            <div class="admin-panel">
               <div class="left-admin-panel">
                  <div>
                     <p><i class="fas fa-user"></i>&ensp;Welcome Username</p>
                  </div>
               </div>
               <div class="center-admin-panel">
                  <p>Admini Panel Board</p>
               </div>
               <div class="right-admin-panel">
                  <div>
                     <p><i class="fas fa-calendar-week"></i>&ensp;<%= simpleDateFormat.format(date)%></p>
                  </div>
                  <div>
                     <p><i class="far fa-clock"></i>&ensp;<span id="time"></span></p>
                  </div>
                  <div>
                     <button type="submit">Settings</button>
                  </div>
                  <div class="logout">
                     <form action="" method="">
                        <button type="submit" value="logout" name="logout">Logout</button>
                     </form>
                  </div>
               </div>
            </div>