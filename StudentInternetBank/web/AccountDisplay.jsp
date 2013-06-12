<%-- 
    Document   : Display
    Created on : Dec 20, 2012, 5:20:20 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="framecss.css">
        <link rel="stylesheet" type="text/css" href="Home.css">
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <title>Statement</title>
    </head>
    <body>
        
    <div id="framecontent" >
            <div id="stmt" > 
                <a id="link1" href="AccountDetailsServlet?Accnum=<%= (String)request.getAttribute("acct") %>&Stmt=currentmonth">  Current Statement </a> <br/><br/>
                <a id="link2" href="AccountDetailsServlet?Accnum=<%= (String)request.getAttribute("acct") %>&Stmt=previousmy&flag=0"> Statement for previous months/years </a>
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
        
    <h1>Please select an year and month:</h1>        
        <form action="AccountDetailsServlet" method="POST">
            <input type="hidden" name="Accnum" value="<%= request.getAttribute("acct") %>"/>
            <input type="hidden" name="flag" value="1"/>
            <input type="hidden" name="Stmt" value="previousmy"/>
            <select name="month">
                <option value="01">Jan</option>
                <option value="02">Feb</option>
                <option value="03">Mar</option>
                <option value="04">Apr</option>
                <option value="05">May</option>
                <option value="06">Jun</option>
                <option value="07">Jul</option>
                <option value="08">Aug</option>
                <option value="09">Sep</option>
                <option value="10">Oct</option>
                <option value="11">Nov</option>
                <option value="12">Dec</option>
            </select>        
            <select name="year">
                <option value="2012">2012</option>
                <option value="2011">2011</option>
                <option value="2010">2010</option>
                <option value="2009">2009</option>
                <option value="2008">2008</option>
                <option value="2007">2007</option>
                <option value="2006">2006</option>
                <option value="2005">2005</option>
                <option value="2004">2004</option>
                <option value="2003">2003</option>
                <option value="2002">2002</option>
                <option value="2001">2001</option>
                <option value="2000">2000</option>
            </select>            
            <input type="submit" value="Submit" name="submit" />
        </form>            
    </div>
</body>
</html>
