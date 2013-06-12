<%-- 
    Document   : Welcome
    Created on : Dec 6, 2012, 6:20:42 PM
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
        <title>Pitt Student Bank</title>
        <jsp:useBean id="u1" class="PittStudentBank.userBean" scope="session" />
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
                <a href="HomePage.jsp" > Logout </a>
            </div>

            <h1> Welcome to Pitt Student Bank <jsp:getProperty name = "u1" property="name" /> ! </h1>

        </div>        
    </body>
</html>
