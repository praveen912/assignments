<%-- 
    Document   : Account
    Created on : Dec 6, 2012, 8:06:07 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="PittStudentBank.Handler" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="u1" class="PittStudentBank.userBean" scope="session" />
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <link rel="stylesheet" type="text/css" href="framecss.css">
        <link rel="stylesheet" type="text/css" href="Home.css">
        <title>Pitt Student Account</title>
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

            <h1> Welcome to Pitt Student Bank <jsp:getProperty name = "u1" property="name" /> ! </h1>

            <h2>Account Details</h2>
            
            <div id="list">
            <% ArrayList <Handler> a = (ArrayList <Handler>)request.getAttribute("accinfo"); 
               Handler h;                      
               for(int i =0; i < a.size(); i++)
               { 
                h = a.get(i); %>        
               <%= i+1 %>. Account Number: <a href="AccountDetailsServlet?Accnum=<%= h.getAcctnum() %>&Stmt=last10"> <%= h.getAcctnum() %> </a> - Balance: <%= h.getBalance() %> <br> <br> 
            <% }           
            %>
            </div>
        </div>
    </body>
</html>
