/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* if(flaga==0 || flagb == 0 || flagc == 0 || flagd == 0 || flage == 0 || flagf == 0 || flagg== 0 || flagh == 0 || flagi == 0 || flagj == 0 || flagk == 0 || flagl == 0 || flagm == 0)
    {
        alert("Form Incomplete!");
    }*/

var Username;
var pass;
var conpass;
var flaga=0;
var flagb=0;
var flagc=0;
var flagd=0;
var flage=0;
var flagf=0;
var flagg=0;
var flagh=0;
var flagi=0;
var flagj=0;
var flagk=0;
var flags=0;
var flagp=0;

function username(value)                       
{
    Username = value;
    var re = new RegExp(/[^a-zA-Z0-9]/);              
    var n = re.test(value);
    if(n == true)
       {
             alert("please enter only letters and numbers");
       }                          
    else 
        {
            flaga =1;               
        }               
 }

 

function namecheck1(value)                       
{
    //var re = new RegExp(/[^a-zA-Z\s]/);              
    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
    {
        alert("please enter only letters");
    }

     else
     {
        flagb=1;        
     }
}

function namecheck2(value)                       
{
    //var re = new RegExp(/[^a-zA-Z\s]/);              
    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
    {
        alert("please enter only letters");
    }

     else
     {
        flagc=1;
     }
}
function namecheck3(value)                       
{
    //var re = new RegExp(/[^a-zA-Z\s]/);              
    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
        {
            alert("please enter only letters");
        }

     else
         {
             flagd=1;
         }
}

function namecheck4(value)                       
{
    //var re = new RegExp(/[^a-zA-Z\s]/);              
    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
        {
            alert("please enter only letters");
        }

     else
         {
             flage=1;
         }
}
function namecheck5(value)                       
{
    //var re = new RegExp(/[^a-zA-Z\s]/);              
    var n = /[^a-zA-Z\s]/.test(value);
    if(n == true)
        {
            alert("please enter only letters");
        }

     else
         {
             flagf=1;
         }
}

function dobcheck(value)
{
    var n = /(19|20)\d\d([-])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])/.test(value);
    if(n == false)
        {
            alert("please enter DOB in this format yyyy-mm-dd");
        }

     else
         {
             flagm=1;
         }
}

function passcheck(value)
{
    
   pass = value;    
   if (value.length < 5 || value.length > 10)
    {        
        alert("enter 5-10 charecters");
    }
    else
    {   
        flagg=1;
    }
        
}

function confpass(value)
{
    conpass = value;
   
   if (conpass == pass)
       {                      
           flagh=1;
       }
   else
       {
           alert("Verify password does't match. Kindly check.");
       }
   

}

function zipcheck(value)
{
    var re = new RegExp(/^\d{5}$/);              
    var n = re.test(value);
    if(n == false)
        {
            alert("please enter a valid zip with 5 digits");
        }
   else
        {
              flagi=1;
        }           
}

function doornocheck(value)
{
    var re = new RegExp(/^\d+[a-zA-Z]*$/);              
    var n = re.test(value);
    if(n == false)
        {
            alert("please enter a valid door no");
        }
    else
        {
          flagj=1;
        }

}

function ssncheck(value)
{    
    var re = new RegExp(/^\d{3}-?\d{2}-?\d{4}$|^XXX-XX-XXXX$/);              
    var n = re.test(value);
    if(n == false)
    {
        alert("please enter a valid SSN in this form XXX-XX-XXXX");
    }
    else
    {
      flagl=1;
    }
}

function emailcheck(value)
{    
    var re = new RegExp(/[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)\b/);              
    var n = re.test(value);
    if(n == false)
        {
            alert("please enter a valid Email");
        }
    else
        {
             flagk=1;
        }

}

function salarycheck(value)
{  
    var re = new RegExp(/[^\d+]/);              
    var n = re.test(value);
    if(n == true)
    {
        alert("please enter a valid salary");
        alert(flags);
    }
    else
    {
      flags=1;
    }
    
}

function pincheck(value)
{ 
    var re = new RegExp(/^\d{4}$/);              
    var n = re.test(value);
    if(n == false)
        {
            alert("please enter a 4 digit number");
        }
    else
        {
          flagp=1;
        }
    
}

function validate()
{
    if(flaga == 0 || flagb == 0 || flagc == 0 || flage == 0 || flagf == 0 || flagg== 0 || flagh == 0 || flagi == 0 || flagj == 0 || flagk == 0 || flagl == 0 || flagm  == 0)
    {
        alert("Form Incomplete!");
        return false;
    }
    return true;
}

function validatecc()
{
    if(flagb == 0 || flagc == 0 || flage == 0 || flagf == 0 || flagi == 0 || flagj == 0 || flags == 0 || flagp == 0)
    {
        alert("Form Incomplete!");
        return false;
    }    
    return true;
}