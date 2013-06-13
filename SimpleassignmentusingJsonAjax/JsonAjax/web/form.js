/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var Username;
var pass;
var conpass;
var flag;
var flaga=0;
var flagb=0;
var flagc=0;
var flagf=0;
var flagg=0;
var flagh=0;
var flagi=0;
var flagk=0;
var flagl=0;
var flagm=0;
var flags=0;
var x = 0;
var A = new Array();
var browserType;
var xhr;

function username(value)                       
    {
    
    var re = new RegExp(/[^a-zA-Z0-9]/);              
    var n = re.test(value);
    if(n == true) 
       
       {
             alert("please enter only letters and numbers");
                
        }                          
   
    else if(value == '' )
        {
            
            flaga=0;
        }
        else
        {
            flaga =1;               


        }               
 }

function namecheck1(value)                       
{
    fname = document.getElementById("fname");

    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
    {
        alert("please enter only letters");
    }
else if(value == '' )
        {
            
            flagb=0;
        }
        else
     {
        flagb=1;        

     }
}

function namecheck2(value)                       
{
    lname = document.getElementById("lname");

    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
    {
        alert("please enter only letters");
    }

     else if(value == '' )
        {
            
            flagc=0;
        }
        else
     {
        flagc=1;

     }
}

function namecheck5(value)                       
{
    city = document.getElementById("city");

    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
        {
            alert("please enter only letters");
        }

     else if(value == '' )
        {
            
            flagf=0;
        }
     else
         {
             flagf=1;

         }
}

function dobcheck(value)
{
    dob = document.getElementById("dob");
    var n = /(19|20)\d\d([-])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])/.test(value);
    if(n == false)
        {
            alert("please enter DOB in this format yyyy-mm-dd");
        }

     else if(value == '' )
        {
            flagm=0;
        }
     else
         {
             flagm=1;
         }
}

function passcheck(value)
{
   pass = document.getElementById("pass"); 

   if (value.length < 5 || value.length > 10)
    {        
        alert("enter 5-10 charecters");
        flagg=0;
    }
    else if(value == '' )
        {
            flagg=0;
        }
    else
    {   
        flagg=1;
    }
}

function confpass(value)
{
    vp = document.getElementById("vp");
    conpass = value;
    pass = document.getElementById("pass").value;
   if (conpass == pass)
       {                      
           flagh=1;
       }
       else if(value == '' )
        {
            flagh=0;
        }
   else
       {
           alert("Verify password does't match. Kindly check.");
           flagh=0;
        }
}

function zipcheck(value)
{
    zip = document.getElementById("zip");
    var re = new RegExp(/^\d{5}$/);              
    var n = re.test(value);
    if(n == false)
        {
            alert("please enter a valid zip with 5 digits");
        }
        else if(value == '' )
        {
            flagi=0;
        }
   else
        {
              flagi=1;
        }           
}

function ssncheck(value)
{   ssn = document.getElementById("ssn");
    var re = new RegExp(/^\d{3}-?\d{2}-?\d{4}$|^XXX-XX-XXXX$/);              
    var n = re.test(value);
    if(n == false)
    {
        alert("please enter a valid SSN in this form XXX-XX-XXXX");
        flagl = 0;   
    }
    else if(value == '' )
        {
            flagl=0;
        }
    else
    {
      flagl=1;
    }
}

function emailcheck(value)
{    
    email = document.getElementById("email");
    var re = new RegExp(/[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)\b/);              
    var n = re.test(value);
    if(n == false)
        {
            alert("please enter a valid Email");
            flagk=0;
        }
    else if(value == '')
        {
            flagk=0;
        }
    else
        {
             flagk=1;
        }
}

function validate()
{    
    if(flaga == 0 || flagb == 0 || flagc == 0 || flagf == 0 || flagg== 0 || flagh == 0 || flagi == 0 || flagk == 0 || flagl == 0 || flagm  == 0)
    {
        alert("Form Incomplete!");
        return false;
    }
    else 
    {
        document.getElementById("percentage").style.visibility="hidden";
        document.getElementById("reg").style.visibility="hidden";
        document.getElementById("submitted").style.visibility="visible";
        genericRemoteCall('homeservlet', -1, 'checker'); 
        return false;
    }       
}
    
function check()
{    
    flags = flaga + flagb + flagc + flagf + flagg + flagh + flagi + flagk + flagl + flagm;    
    document.getElementById("flags").value = flags;
    genericRemoteCall('homeservlet', flags, 'checker');                
}

function createXHR() { 
    var XHR;
    if (window.XMLHttpRequest) { // Mozilla, Safari,...
        XHR = new XMLHttpRequest();
        if (XHR.overrideMimeType) {
            XHR.overrideMimeType('text/html');
            }
        browserType="Mozilla"
        return XHR;
    } //end mozilla attempt
    if (window.ActiveXObject) { // IE
	try {
            XHR = new ActiveXObject("Msxml2.XMLHTTP");
            browserType="IE";
            return XHR;
	} 
        catch (e) {
            try {
                XHR = new ActiveXObject("Microsoft.XMLHTTP");
                browserType="IE";
                return XHR;
            }
            catch (e) {
                alert("Your browser does not support AJAX!");
                browserType="Unknown"
                return null;
                }
            }
    }//end IE attempt 
    //we should never get here, but if we do, things are bad
    return null;
}

function genericResponseHandler() {
   
      if (xhr.readyState == 4) {

        if (xhr.status == 200) {
          process_good_outcome(xhr.responseText);
        }
      }
}

function genericRemoteCall(servlet, n, idval) {      
    var request = {"filledper":n};
    var myform;
    xhr = createXHR();
    xhr.onreadystatechange = genericResponseHandler;
    if(n != -1)
    {
        if ((myform = document.getElementById(idval))!= null){
            var num = myform.elements.length;
            for(i=0; i < num; i++) {             
                    var name = myform.elements[i].name;                
                    var value = myform.elements[i].value;
                    request[name] = value;                           
            }
        }
    }
    else
    {
        if ((myform = document.getElementById(idval))!= null){
            num = myform.elements.length;
            for(i=0; i < num; i++) {
                name = myform.elements[i].name;
                if(document.getElementById("uname").name == name)
                    {                
                    value = myform.elements[i].value;
                    request[name] = value;                           
                    }
                }
            }     
    }
    
    //document.getElementById("deb").innerHTML = JSON.stringify(request);
    xhr.open("POST", servlet , true);
    xhr.send(JSON.stringify(request));
}

function process_good_outcome(response) 
{
    var JSONResp = JSON.parse(response);
    if(JSONResp.submitted != null)
      document.getElementById("submitted").innerHTML=JSONResp.submitted;
    else{
        document.getElementById("unameavailability").innerHTML=JSONResp.unameavailability;       
        document.getElementById("percentage").innerHTML=JSONResp.percentage;            
    }
}
