<%-- 
    Document   : Register
    Created on : Dec 6, 2012, 1:53:08 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <script language="JavaScript" src="formcheck.js"> </script>
        <link rel="stylesheet" type="text/css" href="Bank.css">        
        <title>Registration</title>
    </head>
    <body>
        <h1> Registration Form </h1>
                
        <form name = "register" action="RegisterServlet" method="POST" onsubmit="return validate();">             
            First Name : <input type="text" name="FirstName" value="" size="30" onchange = "namecheck1(this.value); return false;"/> 
            <br/><br/>Last Name : <input type="text" name="LastName" value="" size="30" onchange = "namecheck2(this.value); return false;"/> 
            <br/><br/>Middle Name :<input type="text" name="MiddleName" value="" size="30" onchange = "namecheck3(this.value); return false;"/>  
            <br/><br/>SSN : <input type="text" name="ssn" value="" size="30" onchange = "ssncheck(this.value); return false;" /> 
            <br/><br/>Gender : Male <input type="radio" name="Gender" value="Male" checked="checked" /> Female <input type="radio" name="Gender" value="Female" />
            <br/><br/>Email ID : <input type="text" name="Email" value="" size="30" onchange = "emailcheck(this.value); return false;"/>
            <br/><br/>Door No : <input type="text" name="DoorNo" value="" onchange = "doornocheck(this.value); return false;" /> <br/><br/>
                      Street : <input type="text" name="Street" value="" onchange = "namecheck4(this.value); return false;"/> <br/><br/>
                      City : <input type="text" name="City" value="" onchange = "namecheck5(this.value); return false;" /> <br/><br/>
                      Zip : <input type="text" name="Zip" value="" onchange = "zipcheck(this.value); return false;" />            
            <br/><br/>DOB : <input type="text" name="dob" value="yyyy-mm-dd" onchange = "dobcheck(this.value); return false;" />
            <%--<a href="javascript:show_calendar('document.register.dob', document.register.dob.value);"> 
<img src="DatePicker/cal.gif" width="16" height="16" border="0" alt="Click Here to select the date"> </a> --%>
            <br/><br/>User Name : <input type="text" name="Username" value="" size="30" onchange = "username(this.value); return false;"/>
            <% String msg = (String)request.getAttribute("msg"); 
                      if(msg != null){
            %>
        
            <%= msg %>

            <% } %>

            <br/><br/>Password : <input type="password" name="Password" value="" size="30"  onchange="passcheck(this.value); return false;" />
            <br/><br/>Verify Password : <input type="password" name="ComfirmPassword" value="" size="30" onchange = "confpass(this.value); return false;"/>                
            <br/><br/><input type="submit" value="Register" name="Register"/>          
        </form>
        
    </body>

</html>
