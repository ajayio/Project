<%@page import="com.ecamp.bean.circular.ViewedListBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <style>
        #dataTable {
            border-collapse: collapse;
            width: 100%;
        }

        #dataTable td, #dataTable th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #dataTable tr:nth-child(even){
            background-color: #f2f2f2;
        }

        #dataTable tr:hover {
            background-color: #ddd;
        }

        #dataTable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #0093d1;
            color: white;
        }
        
        #form{
            display: block;
            padding: 10px 20px;
            background:  #0093d1
        }
        
        #viewListOption{
            padding: 5px 10px;
        }
        
        #markedOption{
            padding: 5px 10px;
        }
        
        #viewListInput{
            padding: 5px;
        }
        
        #submit{
            border: none;
            padding: 5px 10px;
            color: #fff;    
            cursor: pointer;
            background: #000;
            
        }
        
        #form button {
            background: #f00;
            border: none;
            padding: 5px 10px;
            color: #fff;
            cursor: pointer;
        }
        
        .dt-buttons{
            margin: 10px 0;
        }
        
        .dt-button{
            border: none;
            padding: 7.5px 15px;
            margin: 0 10px;
            background: #4caf50;
            color: #fff;
            border-radius: 25px;
            cursor: pointer;
        }
        
        .dt-button:hover{
            background: #f00;
        }
        
        .dataTables_filter{
            margin: 10px 0;
        }
        
        .dataTables_filter lable{
            display: none;
        }
        #dummy{
            
        }
    </style>
    
    <script>
        
        function noEnter() {
            return !(window.event && window.event.keyCode === 13);
        }
        
        function doGetServlet(){            
            var select = document.getElementById('viewListOption');
            //alert(select);
            var option = select.options[select.selectedIndex].value;
            var target;
            var submit = document.getElementById("submit").value;
            if(option !== null){
                switch(option){
                    case "circularId" :
                        target = document.getElementById("viewListInput").value;
                        alert("Target : "+target+" Option :"+option+" Submit : "+submit);
                        url = "../ViewedListServlet?option="+option+"&target="+target+"&submit="+submit;
                        var request = new XMLHttpRequest();
                        request.onreadystatechange = function () {
                        if((request.readyState === 4)&&(request.status === 200)){                           
                            responseText = request.responseText;
                            display(responseText);
                            }
                        }
                        break;
                    case "userId" :
                        target = document.getElementById("viewListInput").value;
                        alert("Target : "+target+" Option :"+option+" Submit : "+submit);
                        url = "../ViewedListServlet?option="+option+"&target="+target+"&submit="+submit;
                        var request = new XMLHttpRequest();
                        request.onreadystatechange = function () {
                        if((request.readyState === 4)&&(request.status === 200)){                           
                            responseText = request.responseText;
                            display(responseText);
                            }
                        }
                        break;
                    case "marked" :
                        var marked = document.getElementById("markedOption");
                        var markedOption = marked.options[marked.selectedIndex].value;
                        alert("Marked Option : "+markedOption+" Option :"+option+" Submit : "+submit);
                        url = "../ViewedListServlet?option="+option+"&submit="+submit+"&markedOption="+markedOption;
                        var request = new XMLHttpRequest();
                        request.onreadystatechange = function () {
                        if((request.readyState === 4)&&(request.status === 200)){                           
                            responseText = request.responseText;
                            display(responseText);
                            }
                        }
                        break;
                    case "viewAll" :
                        alert("Option :"+option+" Submit : "+submit);
                        url = "../ViewedListServlet?option="+option+"&submit="+submit;
                        var request = new XMLHttpRequest();
                        request.onreadystatechange = function () {
                        if((request.readyState === 4)&&(request.status === 200)){                           
                            responseText = request.responseText;
                            display(responseText);
                            }
                        }
                        break;
                    default :
                        alert("Select option");
                        break;
                }
                request.open("GET",url,true);
                request.send();
            }
        }
        
        function display (responseText) {
            alert(responseText.length);
            responseText = responseText.substring(0, responseText.length-3);
            var list = responseText.split("|");
            alert(list.length);
            var j = 0;
            var table = "";
            for(var i=0;i<list.length;i++){
                table+= '<tr>'+
                            '<td>'+ (++j) +'</td>'+
                            '<td>'+ list[i++] +'</td>'+
                            '<td>'+ list[i++] +'</td>'+
                            '<td>'+ list[i++] +'</td>'+
                            '<td>'+ list[i] +'</td>'+
                        '</tr>';
            }
            document.getElementById("loadData").innerHTML = table;
        }
    </script>
    <body>
        
        <div id="form">            
            <form  onkeypress="return noEnter();" autocomplete="off">
                    <select name="viewListOption" id="viewListOption">
                        <option value="selectOption">Select Option</option>
                        <option value="circularId">Circular Id</option>
                        <option value="userId">User Id</option>
                        <option value="marked">Marked</option>
                        <option value="viewAll">View All</option>
                    </select>
                    <input type="text" name="value" id="viewListInput" maxlength="10" size="10">
                    <select name="markedOption" id="markedOption">
                        <option value="selectOption">Select Option</option>
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                    </select>
                    <input type="button" id="submit" onClick ="doGetServlet();" name="submit" value="Submit" />
                    <button type="reset" value="reset">Clear</button>
            </form>
        </div>
        <table id="dataTable" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Sl.No</th>
                    <th>Circular Id</th>
                    <th>User Id</th>
                    <th>Time Viewed</th>
                    <th>Viewed</th>
                </tr>
            </thead>
            <tbody id="loadData">
                
            </tbody>
        </table>        
        <% 
            String message=request.getParameter("responseText");
            String[] list = null;
            if(message!=null&&!message.equals("NOT AVAILABLE")){
                message = message.substring(0,message.length()-1);
                list = message.split("\\|");
            }
         %>
         <% 
            if(message!=null&&list!=null){
                %>
                        <table id="dataTable" class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Sl.No</th>
                                    <th>Circular Id</th>
                                    <th>User Id</th>
                                    <th>Time Viewed</th>
                                    <th>Viewed</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int j = 0;
                                    for(int i=0;i<list.length;i++){
                                %>      <tr>
                                            <td><%= ++j%></td>
                                            <td><%= list[i++]%></td>
                                            <td><%= list[i++]%></td>
                                            <td><%= list[i++]%></td>
                                            <td><%= list[i].equals("1")?"Yes":"No" %></td>
                                        </tr>
                                <%   }
                                %>
                            </tbody>
                        </table>       
                <%
            }
            else {
                if(message!=null && message.equals("NOT AVAILABLE")){ %>
                    <%= "DATA " +message%>
                <%}
            }%>
            
            <script type="text/javascript">
                document.getElementById("markedOption").style.display = "none";
                document.getElementById("viewListOption").addEventListener('change', function() {
                    var viewListOptionElement  = document.getElementById("viewListOption");
                    var viewListOption = viewListOptionElement.options[viewListOptionElement.selectedIndex].value;
//                  alert(viewListOption);
                    document.getElementById("viewListInput").value = "";
                    if(viewListOption==="viewAll"){
//                      alert("Yes");
//                      document.getElementById("viewListInput").style.display = "none";
                        document.getElementById("viewListInput").style.display = "inline-block";
                        document.getElementById("markedOption").style.display = "none";
                        document.getElementById("viewListInput").disabled = true;
                        document.getElementById("viewListInput").style.cursor = "not-allowed";
                    }
                    else if(viewListOption==="marked"){
                        document.getElementById("viewListInput").style.display = "none";
                        document.getElementById("markedOption").style.display = "inline-block";
                    }
                    else{
                        document.getElementById("viewListInput").disabled = false;
                        document.getElementById("viewListInput").style.cursor = "default";
                        document.getElementById("markedOption").style.display = "none";
                        document.getElementById("viewListInput").style.display = "inline-block";
                    }
                })
            </script>
<!--            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
            <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
            <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
            <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
            <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js"></script>
            <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>-->
    
    <script type="text/javascript">
        
        function formValidate(){
            return true;
        }
        
//        $(document).ready(function() {
//            $('#dataTable').DataTable( {
//            dom: 'Bfrtip',
//            buttons: [
//                'copy', 'csv', 'excel', 'pdf', 'print'
//                ]
//            });
//        }); 
//        
    </script>
    </body>
</html>
