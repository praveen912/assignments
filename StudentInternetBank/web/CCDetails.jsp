<%-- 
    Document   : CCDetails
    Created on : Dec 20, 2012, 10:16:29 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="PittStudentBank.Handler"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <link rel="stylesheet" type="text/css" href="Home.css">
        <link rel="stylesheet" type="text/css" href="framecss.css">
        <title>Credit Card Details</title>
    </head>
    <body>
        <div id="framecontent" >
            <div id="stmt">          
                <a id="link1" href="CCBillServlet?Ccnum=<%= request.getAttribute("Ccnum") %>"> Pay Bill </a>             
            </div>
        </div>
            
        <div id="maincontent">    
            
             <div id="home" >
                <% if(session.getAttribute("u1") != null){ %>                
                <a href="Welcome.jsp" > Home </a>                
                <% }
                   else{
                %>                
                <a href="HomePage.jsp" > Home </a>                
                <% } %>                
                <a href="HomePage.jsp" > Logout </a>
            </div>
            
        <h1> CreditCard Number: <%= request.getAttribute("Ccnum") %></h1>
        
        <table border="1">
                <thead>
                    <tr>
                        <th>Transaction Id </th>
                        <th>Balance</th>
                        <th>Limit</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Transfer For</th>
                        <th>Date and Time</th>
                    </tr>
                </thead>        
         <% ArrayList <Handler> t = (ArrayList <Handler>)request.getAttribute("cctransinfo"); 
           Handler h;                      
           for(int i =0; i < t.size(); i++)
           { 
            h = t.get(i); %>       
                <tbody>
                    <tr>
                        <td> <%= h.getTid() %> </td>
                        <td> <%= h.getBalance() %> </td>
                        <td> <%= h.getLimit() %> </td>
                        <td> <%= h.getType() %> </td>
                        <td> <%= h.getAmt() %> </td>
                        <td> <%= h.getTransfertofrom() %> </td>
                        <td> <%= h.getDt() %> </td>
                    </tr>
            <% }           
             %>
        
                 </tbody>
               </table> <br/> <br/>           
        </div>
    </body>
</html>
