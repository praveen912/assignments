<%-- 
    Document   : Invalid
    Created on : Dec 20, 2012, 6:45:41 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="framecss.css">
        <link rel="stylesheet" type="text/css" href="Home.css">
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <title>Invalid Request</title>
    </head>
    <body>
        
        <% int info = (Integer)request.getAttribute("info");
           
           if(info == 0){
        %>
        <div id="framecontent" >
            <div id="stmt" > 
                <a id="link1" href="AccountDetailsServlet?Accnum=<%= (String)request.getAttribute("acct") %>&Stmt=currentmonth">  Current Statement </a> <br/><br/>
                <a id="link2" href="AccountDetailsServlet?Accnum=<%= (String)request.getAttribute("acct") %>&Stmt=previousmy&flag=0"> Statement for previous months/years </a>
            </div>
        </div>
            
        <% } 
            
           else{
        %>
        
        <div id="framecontent" >
            <div id="stmt" > 
                <a id="link1" href ="AccountServlet"> Account </a>     
                <a id="link2" href="CreditCardServlet"> CreditCards </a>
                <a id="link3" href="LoanServlet"> Loan </a>
            </div>
        </div>
            
        <% } %>    

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
        
        <h1> <%= (String)request.getAttribute("message") %> </h1>       
    </div>
    
    </body>
</html>
