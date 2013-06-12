/**Document   : Homepage.js
    Created on : Sep 22, 2012, 09:23:54 PM
    Author     : PraveenNK
 **/

var flaga;
var flagapple;
var flagb;
var flagball;
var flagc;
var flagcat;
var flagd;
var flagdog;
var flage;
var flagelephant;
var flagf;
var flaffrog;
var r11;
var r12;
var r21;
var r22;
var r31;
var r32;
var r41;
var r42;
var r51;
var r52;
var r61;
var r62;
var x; 
var d;
function Startgame()
{
    r12 = document.getElementById('APPLE');
    r11 = document.getElementById('A');
    r22 = document.getElementById('BALL');
    r21 = document.getElementById('B');
    r32 = document.getElementById('CAT');
    r31 = document.getElementById('C');
    r42 = document.getElementById('DOG');
    r41 = document.getElementById('D');
    r52 = document.getElementById('ELEPHANT');
    r51 = document.getElementById('E');    
    r62 = document.getElementById('FROG');
    r61 = document.getElementById('F');    
    x = document.getElementById("day1");
    flaga=0;
    flagapple=0;
    flagb=0;
    flagball=0;
    flagc=0;
    flagcat=0;
    flagd=0;
    flagdog=0;
    flage=0;
    flagelephant=0;
    flagf=0;
    flagfrog=0;
}


//this function is used to validate the answers and display alert messages for alphabets 
function fn1(val1)
{    
    this.val1 = val1;
    var temp = val1.id;
    ValueAssignAlphabet(temp);
        
    if(flaga == 1)
        {
            if(flagapple == 1)
                {
                    alert("You have selected the right choice");
                    flushall();                    
                    displayObject(val1);
                }
                
            else if(flagball == 1 || flagcat == 1 || flagdog == 1 || flagelephant == 1 || flagfrog == 1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
                
        }
        
     else if(flagb == 1)
        {
            if(flagball == 1)
                {
                    alert("You have selected the right choice");
                    flushall();
                    displayObject(val1);
                }
                
            else if(flagapple == 1 || flagcat == 1 || flagdog == 1 || flagelephant == 1 || flagfrog == 1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
        }
        
     else if(flagc == 1)
        {
            if(flagcat == 1)
                {
                    alert("You have selected the right choice");
                    flushall();
                    displayObject(val1);
                }
                
            else if(flagapple == 1 || flagball == 1 || flagdog == 1 || flagelephant == 1 || flagfrog == 1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
        }
      
      else if(flagd == 1)
        {
            if(flagdog == 1)
                {
                    alert("You have selected the right choice");
                    flushall();
                    displayObject(val1);
                }
                
            else if(flagapple == 1 || flagball == 1 || flagcat == 1 || flagelephant == 1 || flagfrog == 1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
        }
        
      else if(flage == 1)
        {
            if(flagelephant == 1)
                {
                    alert("You have selected the right choice");
                    flushall();
                    displayObject(val1);
                }
                
            else if(flagapple == 1 || flagball == 1 || flagcat == 1 || flagdog == 1 || flagfrog == 1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
        }
        
        else if(flagf == 1)
        {
            if(flagfrog == 1)
                {
                    alert("You have selected the right choice");
                    flushall();
                    displayObject(val1);
                }
                
            else if(flagapple == 1 || flagball == 1 || flagcat == 1 || flagdog == 1 || flagelephant == 1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
        }
} 

//this function is used to validate the answers and display alert messages for images
function fn2(val2)
{
    this.val2 = val2;
    var temp = val2.id;
    ValueAssignImage(temp);
       
    if(flagapple == 1)
        {
            if(flaga == 1)
                {
                    alert("You have selected the right choice");
                    flushall();
                    displayObject(val2);
                }
            else if(flagb == 1 || flagc==1 || flagd==1 || flage==1 || flagf==1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
        }
        
    else if(flagball == 1)
        {
            if(flagb == 1)
                {
                    alert("You have selected the right choice");
                    flushall();   
                    displayObject(val2);
                }
            else if(flaga == 1 || flagc==1 || flagd==1 || flage==1 || flagf==1)
                {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
        }
        
    else if(flagcat == 1)
    {
        if(flagc == 1)
            {
                alert("You have selected the right choice");
                flushall();
                displayObject(val2);
            }
        else if(flaga == 1 || flagb==1 || flagd==1 || flage==1 || flagf==1)
            {
                 alert("This is wrong! Start again!");                
                 flushall();
            }
    }
    
    else if(flagdog == 1)
    {
        if(flagd == 1)
            {
                alert("You have selected the right choice");
                flushall();
                displayObject(val2);
            }
        else if(flaga == 1 || flagb==1 || flagc==1 || flage==1 || flagf==1)
            {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
    }

    else if(flagelephant == 1)
    {
        if(flage == 1)
            {
                alert("You have selected the right choice");
                flushall();
                displayObject(val2);
            }
        else if(flaga == 1 || flagc==1 || flagd==1 || flagb==1 || flagf==1)
            {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
    }
    else if(flagfrog == 1)
    {
        if(flagf == 1)
            {
                alert("You have selected the right choice");
                flushall();   
                displayObject(val2);
            }
        else if(flaga == 1 || flagc==1 || flagd==1 || flagb==1 || flage==1)
            {
                    alert("This is wrong! Start again!");                
                    flushall();
                }
    }
}

//this function is used to assign flag value to alphabets
function ValueAssignAlphabet(exp1)
{
    switch(exp1)
    {
        case r11.id:
            {
                if(flaga == 0)
                    flaga=1;
                else
                    alert("This is already selected");
                if(flagb == 1 || flagc == 1 || flagd == 1 || flage == 1 || flagf ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
        case r21.id:
            {
                if(flagb == 0)
                    flagb=1;
                else
                    alert("This is already selected");            
                if(flaga == 1 || flagc == 1 || flagd == 1 || flage == 1 || flagf ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
        case r31.id:
            {
                if(flagc == 0)
                    flagc=1;
                else
                    alert("This is already selected");            
                if(flaga == 1 || flagb == 1 || flagd == 1 || flage == 1 || flagf ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
         case r41.id:
            {
                if(flagd == 0)
                    flagd=1;
                else
                    alert("This is already selected");            
                if(flaga == 1 || flagb == 1 || flagc == 1 || flage == 1 || flagf ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
         case r51.id:
            {
                if(flage == 0)
                    flage=1;
                else
                    alert("This is already selected");            
                if(flaga == 1 || flagb == 1 || flagc == 1 || flagd == 1 || flagf ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
        case r61.id:
            {
                if(flagf == 0)
                    flagf=1;
                else
                    alert("This is already selected");            
                if(flaga == 1 || flagb == 1 || flagc == 1 || flagd == 1 || flage ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }               
        }
}        

//this function is usd to assign flag value to images
function ValueAssignImage(exp2)
{
    switch(exp2)
    {
        case r12.id:
            {
                if(flagapple == 0)
                    flagapple=1;
                else
                    alert("This is already selected");
                if(flagball == 1 || flagcat == 1 || flagdog == 1 || flagelephant == 1 || flagfrog ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
         case r22.id:
            {
                if(flagball == 0)
                    flagball=1;
                else
                    alert("This is already selected");
                if(flagapple == 1 || flagcat == 1 || flagdog == 1 || flagelephant == 1 || flagfrog ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
        case r32.id:
            {
                if(flagcat == 0)
                    flagcat=1;
                else
                    alert("This is already selected");
                if(flagball == 1 || flagapple == 1 || flagdog == 1 || flagelephant == 1 || flagfrog ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
         case r42.id:
            {
                if(flagdog == 0)
                    flagdog=1;
                else
                    alert("This is already selected");
                if(flagball == 1 || flagcat == 1 || flagapple == 1 || flagelephant == 1 || flagfrog ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
            
         case r52.id:
            {
                if(flagelephant == 0)
                    flagelephant=1;
                else
                    alert("This is already selected");
                if(flagball == 1 || flagcat == 1 || flagdog == 1 || flagapple == 1 || flagfrog ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            }
           
          case r62.id:
            {
                if(flagfrog == 0)
                    flagfrog=1;
                else
                    alert("This is already selected");
                if(flagball == 1 || flagcat == 1 || flagdog == 1 || flagelephant == 1 || flagapple ==1)
                    {
                    alert("This is wrong! Start again!");                
                    flushall();
                    }
                break;
            } 
            
    }
 }
 
 
 //this function is used to set the flag value to default
 function flushall()
 {
    flaga=0;
    flagapple=0;
    flagb=0;
    flagball=0;
    flagc=0;
    flagcat=0;
    flagd=0;
    flagdog=0;
    flage=0;
    flagelephant=0;
    flagf=0;
    flagfrog=0;
 }

//this functon will make a copy of the correct pair of images and display them in the Answers block.
//After that it also hide the existing pair of images
function displayObject(res)
{
    var oldobj1 = res.childNodes.item(1).childNodes.item(1);
    var newobj1 = oldobj1.cloneNode(true);
    var oldobj2 = getObj(res.id);
    var newobj2 = oldobj2.cloneNode(true);
    newobj1.setAttribute("width","100");
    newobj1.setAttribute("height","100");
    newobj2.setAttribute("width","100");
    newobj2.setAttribute("height","100");
    var FinalList = document.getElementById('answers');
    FinalList.appendChild(newobj2);
    FinalList.appendChild(newobj1);
    oldobj1.style.setProperty("visibility","hidden","important");
    oldobj2.style.setProperty("visibility","hidden","important");
}

//this function will get the image object depending upon the image click 
function getObj(exp3)
{
    var oldobj2;       
    var div;
    
    switch(exp3)
    {
        case r11.id:
                {
                div = document.getElementById('APPLE');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
                }
        case r12.id:    
            {
                div = document.getElementById('A');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
            }            
        
        case r21.id:
                {
                div = document.getElementById('BALL');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);                
                break;
                }
        case r22.id:    
            {
                div = document.getElementById('B');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
            }
        
        case r31.id:
                {
                div = document.getElementById('CAT');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
                }
        case r32.id:    
            {
                div = document.getElementById('C');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
            }
         
         case r41.id:
                {
                div = document.getElementById('DOG');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
                }
        case r42.id:    
            {
                div = document.getElementById('D');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
            }
         
         case r51.id:
                {
                div = document.getElementById('ELEPHANT');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
                }
        case r52.id:    
            {
                div = document.getElementById('E');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
            }
        
        case r61.id:
                {
                div = document.getElementById('FROG');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
                }
        case r62.id:    
            {
                div = document.getElementById('F');
                oldobj2 = div.childNodes.item(1).childNodes.item(1);
                break;
            }
    }
    
    return(oldobj2);        
}

//this function will generate and the day of the week when clicked on a link
function day()
{
var d = new Date();
var weekday=new Array(7);
weekday[0]="Sunday";
weekday[1]="Monday";
weekday[2]="Tuesday";
weekday[3]="Wednesday";
weekday[4]="Thursday";
weekday[5]="Friday";
weekday[6]="Saturday";
x.innerHTML= weekday[d.getDay()];
}
