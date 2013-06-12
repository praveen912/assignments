<%-- 
    Document   : PaymentDisplay
    Created on : Dec 21, 2012, 3:45:30 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <link rel="stylesheet" type="text/css" href="Home.css">
        <link rel="stylesheet" type="text/css" href="framecss.css">
        <title>Payment Details</title>
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
          
            <% String msg = (String)request.getAttribute("message"); %>

            <h1><%= msg %></h1>

            <% if(msg.equals("You do not have sufficient funds in this account.Select another account"))
                {  %>

                <a  href = BillPayServlet?Ccnum=<%= request.getAttribute("ccnum") %>&Amt=<%= request.getAttribute("amt") %>&flag=0" >Click here</a> to select a different account    

           <%     }     
            %>
         </div>      
    </body>
</html>
