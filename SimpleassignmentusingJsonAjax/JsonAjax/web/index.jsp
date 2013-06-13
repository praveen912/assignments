<%-- 
    Document   : index
    Created on : Jan 20, 2013, 7:36:24 PM
    Author     : Praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <script type="text/javascript" src="form.js"> </script>
        <script type="text/javascript" src="json2.js"> </script>
        <link rel="stylesheet" type="text/css" href="json.css">        
        <title>Registration</title>
    </head>
    <body onload="setInterval('check()', 2000)">
                
           <div id="percentage">  </div>      
       
           <form id ="checker" name = "register" action=" ">             
            <input type="hidden" name="flags" id="flags" value="0" />           
            <div id ="reg">
                       <p class="txt"> First Name :</p> <input class="name" type="text" name="FirstName" value="" id="fname" size="30" onchange = "namecheck1(this.value); return false;"/> 
            <br/><br/> <p class="txt"> Last Name :</p>  <input class="name" type="text" name="LastName" value="" id="lname" size="30" onchange = "namecheck2(this.value); return false;"/> 
            <br/><br/> <p class="txt">SSN :</p>         <input class="name" type="text" name="ssn" value="" id="ssn" size="30" onchange = "ssncheck(this.value); return false;" /> 
            <br/><br/> <p class="txt"> Email ID :</p>   <input class="name" type="text" name="Email" value="" id="email" size="30" onchange = "emailcheck(this.value); return false;"/>
            <br/><br/> <p class="txt"> City :</p>       <input class="name" type="text" name="City" value="" id="city" onchange = "namecheck5(this.value); return false;" /> <br/><br/>
                                                        <div id="submitted">  </div>
                       <p class="txt"> Zip :</p>        <input class="name" type="text" name="Zip" value="" id="zip" onchange = "zipcheck(this.value); return false;" />            
            <br/><br/> <p class="txt"> DOB :</p>        <input class="name" type="text" name="dob" value="yyyy-mm-dd" id="dob" onchange = "dobcheck(this.value); return false;" />
            <br/><br/> <p class="txt"> User Name :</p>  <input class="name" type="text" name="Username" value="" id="uname" size="30" onchange = "username(this.value); return false;"/> <div id="unameavailability"> </div>
            <br/><br/> <p class="txt"> Password : </p>  <input class="name" type="password" name="Password" value="" id="pass" size="30"  onchange="passcheck(this.value); return false;" />
            <br/><br/> <p class="txt"> Verify Password :</p> <input class="name" type="password" name="ComfirmPassword" value="" id="vp" size="30" onchange = "confpass(this.value); return false;"/>                
            <br/><br/>   <input type="submit" value="Register" name="Register" onclick="return validate();"/> </div>                     
           </form>
    </body>
</html>
