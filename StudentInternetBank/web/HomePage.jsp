<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <title>Pitt Student Bank</title>        
    </head>
    
    <body> 
        <h1>  Pitt Student Bank   </h1>    
        
        <form name="Login" action="LoginServlet" method="POST">
        Please Login: <br/> <br/>
        
        Username: <input type="text" name="username" value="" size="30" /> <br/> <br/>
        Password: <input type="password" name="password" value="" size="30" /> <br/> <br/>
        
        <input type="submit" value="Login" name="login" /> <br/> <br/>
        
        Haven't registered yet? Click here to <a href ="Register.jsp"> Register </a>        
        </form>
        
        <div id="extra" >            
            <a href = "Purchase.jsp" > Go for shopping </a>   <br/><br/>
            <a href = "Deposit.jsp" > Add money to the account </a>   
        </div>
    </body>
</html>