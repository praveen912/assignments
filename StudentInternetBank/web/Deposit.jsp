<%-- 
    Document   : Deposit
    Created on : Dec 19, 2012, 7:54:36 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <title>Add money to Account</title>
    </head>
    <body>
        <h1>Please make you deposition here</h1>
        <form action="DepositServlet" method="POST">
            UserName: <input type="text" name="username" value="" size="30" /> <br/> <br/>          
            Password: <input type="password" name="password" value="" size="30" /> <br/> <br/>          
            Deposit towards: <input type="text" name="deposit" value="" size="30" /> <br/> <br/>        
            Account Number: <input type="text" name="acctnum" value="" size="30" /> <br/> <br/>        
            Amount: <input type="text" name="amount" value="" size="30" /> <br/> <br/>        
            <input type="submit" value="Submit" />        
        </form>
        
    </body>
</html>
