<%-- 
    Document   : RegisterSuccess
    Created on : Jan 4, 2013, 1:36:47 PM
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
        <title>Pitt Student Credit Cards</title>        
    </head>
    <body>
        <div id="framecontent" >
            <div id="stmt">          
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
            
            <h1>Pitt Student Credit Card</h1>

            <%= (String)request.getAttribute("msg") %>               
            
            <h2> Credit Card Details </h2>
            
            <%  String c = (String)request.getAttribute("ccnum");
                int bal = (Integer)request.getAttribute("balance");
                int limit = (Integer)request.getAttribute("limit");
            %>               

                Creditcard Number: <a href="CCDetailsServlet?Ccnum=<%= c %>"> <%= c %> </a> <br/><br/>
                Balance: <%= bal%> <br/><br/>
                Limit: <%= limit%>         
        </div> 
    </body>
</html>
