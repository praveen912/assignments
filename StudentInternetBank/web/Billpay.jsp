<%-- 
    Document   : Billpay
    Created on : Dec 21, 2012, 2:59:17 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="PittStudentBank.Handler" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <link rel="stylesheet" type="text/css" href="Home.css">
        <link rel="stylesheet" type="text/css" href="framecss.css">
        <title>Credit Card Bill Payment</title>
    </head>
    <body>  
        
        <div id="framecontent" >
            <div id="stmt" >         
                <a id="link1" href ="AccountServlet"> Account </a>     
                <a id="link2" href="CreditCardServlet"> CreditCards </a>
                <a id="link3" href="LoanServlet"> Loan </a>
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
            
            <h1>Credit Card Bill Payment</h1>

            <h2> Select an account to pay the bill: </h2>

            <% ArrayList <Handler> a = (ArrayList <Handler>)request.getAttribute("accinfo"); 
               Handler h;                      
               for(int i =0; i < a.size(); i++)
               { 
                h = a.get(i); %>        
                <%= i+1 %>. Account Number: <a href="BillPayServlet?Ccnum=<%= request.getAttribute("ccnum") %>&Amt=<%= request.getAttribute("amt") %>&flag=1&Accnum=<%= h.getAcctnum() %>"> <%= h.getAcctnum() %> </a> - Balance: <%= h.getBalance() %> <br> <br> 
            <% }           
            %>
         </div>
    </body>
</html>
