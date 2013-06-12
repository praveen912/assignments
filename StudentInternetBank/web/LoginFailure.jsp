<%-- 
    Document   : LoginFailure
    Created on : Dec 6, 2012, 6:20:58 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Bank.css">        
        <title>Login Failure</title>
        <jsp:useBean id="u1" class="PittStudentBank.userBean" scope="session" />
    </head>
    <body>
        <h2>  Pitt Student Bank   </h2>    
        
        <form name="Login" action="LoginServlet" method="POST">
        Please Login: <br/> <br/>
        
        Username: <input type="text" name="username" value="" size="30" /> <br/> <br/>
        Password: <input type="password" name="password" value="" size="30" /> <br/> <br/>
            
        <%= u1.getLoginStatus() %> <br/> <br/>

        <input type="submit" value="Login" name="Login" />                        
        </form> 
    </body>
</html>
