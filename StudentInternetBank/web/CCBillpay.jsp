<%-- 
    Document   : CCBillpay
    Created on : Dec 21, 2012, 2:15:51 PM
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
            <% Double a = (Double)request.getAttribute("amt"); 

               if(a == 0)
               { %>

               <h2> You don't have any outstanding amount for this month </h2>

            <%   }

                   else{ %>               

                   <h2> The outstanding amount to be paid for this month is: <%= a %> </h2>       

                   <a href="BillPayServlet?Ccnum=<%= request.getAttribute("ccnum") %>&Amt=<%= a %>&flag=0" > Click here </a> to pay using your account

            <%       }
            %>
         </div>
    </body>
</html>
