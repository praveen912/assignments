<%-- 
    Document   : Purchase
    Created on : Dec 19, 2012, 3:28:21 PM
    Author     : praveen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--<link rel="stylesheet" type ="text/css" href ="paymode.css" />  --%>       
        <link rel="stylesheet" type="text/css" href="Bank.css">
        <title>Purchase</title>
    </head>
    <body>
        <h1>Welcome</h1>        
        <form action="PurchaseServlet" method="POST">
            UserName: <input type="text" name="username" value="" size="30" /> <br/> <br/>          
            Password: <input type="password" name="password" value="" size="30" /> <br/> <br/>          
            Purchase towards: <input type="text" name="purchasefor" value="" size="30" /> <br/> <br/>   
            Payment Mode:<select name="paymentmode" id="paymentmode" onchange="showpaymode()">
                <option value="acct">E-check</option>
                <option value="dc">DebitCard</option>
                <option value="cc">CreditCard</option>
            </select> <br/><br/>
            <div id="paymode">
            Account Number: <input type="text" name="acctnum" id ="acctnum" value="" size="30" />             
            DebitCard Number: <input type="text" name="dcnum" id="dcnum" value="" size="30" />
            CreditCard Number: <input type="text" name="ccnum" id="ccnum" value="" size="30" /> 
            </div> <br>
            Amount: <input type="text" name="amount" value="" size="30" /> <br/> <br/>        
            <input type="submit" value="Submit" />        
        </form> 
        
       <%-- <script>
        function showpaymode()
        { 
            alert("inside");
            //('body').css('cursor','wait');
            var value = document.getElementBy
            Id('paymentmode').value;
            alert(value);
            
             ajax({
                type: "GET",            
                data:{'paymentmode':value}, 
                success:function(results)
                {       
                    document.getElementById('paymode').style.setAttribute("display", block);                                    

                    if (value == "acctnum") 
                    {
                        document.getElementById('acctnum').style.setAttribute("display", block);
                        //('#acctnum').css('display','block')                
                        //('#ccnum').css('display', 'none')
                        //('#dcnum').css('display','none')
                    } 
                    else if (value == "dcnum") 
                    {
                        document.getElementById('dcnum').style.setAttribute("display", block);
                        /*
                        ('#acctnum').css('display','none')                
                        ('#ccnum').css('display', 'none')
                        ('#dcnum').css('display','block') */
                    }
                    else if (value == "chain") 
                    {
                        document.getElementById('ccnum').style.setAttribute("display", block);
                        /*
                        ('#acctnum').css('display','none')                
                        ('#ccnum').css('display', 'block')
                        ('#dcnum').css('display','none') */
                    }

                    //('body').css('cursor','default');            
                }
            });
        }
        </script> --%>
    </body>
</html>
