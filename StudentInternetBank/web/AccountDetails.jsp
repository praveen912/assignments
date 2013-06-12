<%-- 
    Document   : AccountDetails
    Created on : Dec 18, 2012, 8:23:11 PM
    Author     : praveen
--%>

<%@page import="PittStudentBank.Handler"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" type="text/css" href="framecss.css">
        <link rel="stylesheet" type="text/css" href="Home.css">
        <link rel="stylesheet" type="text/css" href="Bank.css">
        
        <title>Account Details</title>
    </head>
    <body>
        <div id="framecontent" >
            <div id="stmt" > 
                <a id="link1" href="AccountDetailsServlet?Accnum=<%= (String)request.getAttribute("acct") %>&Stmt=currentmonth">  Current Statement </a> <br/><br/>
                <a id="link2" href="AccountDetailsServlet?Accnum=<%= (String)request.getAttribute("acct") %>&Stmt=previousmy&flag=0"> Statement for previous months/years </a>
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
            
        <h2>Selected Account: <%= (String)request.getAttribute("acct") %> </h2>  

        <h2> <%= (String)request.getAttribute("title") %>: </h2>
            
        <table border="1">
                <thead>
                    <tr>
                        <th>Transaction Id </th>
                        <th>Balance</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Transfer To/From</th>
                        <th>Date and Time</th>
                    </tr>
                </thead>        
         <% ArrayList <Handler> t = (ArrayList <Handler>)request.getAttribute("transinfo"); 
           Handler h;                      
           for(int i =0; i < t.size(); i++)
           { 
            h = t.get(i); %>       
                <tbody>
                    <tr>
                        <td> <%= h.getTid() %> </td>
                        <td> <%= h.getBalance() %> </td>
                        <td> <%= h.getType() %> </td>
                        <td> <%= h.getAmt() %> </td>
                        <td> <%= h.getTransfertofrom() %> </td>
                        <td> <%= h.getDt() %> </td>
                    </tr>
        <% }           
        %>
        
                    </tbody>
               </table>       
       </div>          
    </body>
</html>
