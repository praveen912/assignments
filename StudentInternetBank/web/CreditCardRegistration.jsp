<%-- 
    Document   : CreditCardRegistration
    Created on : Dec 19, 2012, 2:53:58 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Bank.css">        
        <script language="JavaScript" src="formcheck.js"> </script> 
        <title>Pitt Student Credit Card</title>
    </head>
    <body>
        <h1>Credit Card Registration</h1>
        <h2>Please fill out the below form:</h2>
        
        <form action="CCRegisterServlet" method="POST" onsubmit="return validatecc();">                
        Employer Company:<input type="text" name="company" value="" size="30" onchange = "namecheck1(this.value); return false;" /> <br/><br/>        
        Employer Company Address: 
                      Door No : <input type="text" name="DoorNo" value="" onchange = "doornocheck(this.value); return false;" /> <br/><br/>
                      Street : <input type="text" name="Street" value="" onchange = "namecheck4(this.value); return false;"/> <br/><br/>
                      City : <input type="text" name="City" value="" onchange = "namecheck5(this.value); return false;" /> <br/><br/>
                      Zip : <input type="text" name="Zip" value="" onchange = "zipcheck(this.value); return false;" />            
        Designation:<input type="text" name="designation" value="" size="30" onchange = "namecheck2(this.value); return false;" /> <br/><br/>        
        Salary:<input type="text" name="salary" value="" size="30" onchange="salarycheck(this.value); return false;" /> <br/><br/>        
        Pin Number:<input type="text" name="pin" value="" size="30" onchange="pincheck(this.value); return false;" /> <br/><br/>        
        
        <input type="submit" value="Submit" name="Submit" />        
        </form>
    </body>
</html>
