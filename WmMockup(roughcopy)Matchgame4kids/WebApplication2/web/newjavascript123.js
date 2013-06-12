/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var MODE_INIT          = 100;
var MODE_GAME          =  0;
var MODE_RESETSELECTED = -2;
var MODE_END           = -1;

var closedImage    = m_imgFolder + "http://t3.gstatic.com/images?q=tbn:ANd9GcQmk3eLvFbM5rpkoL_LG8_LBIy8nlZ77G2UBVovPeoS001NM7R1";
var m_images         = new Array();
var m_mode           = MODE_INIT;
var m_totals         = m_rows * m_cols;
var m_totalCells     = m_totals;
var m_totals2        = m_totals/2;
var m_status         = new Array();
var m_values         = new Array();
var m_types          = new Array();
var m_progress       = 0;
var m_steps          = 0;
var m_stepSize       = 2;
var m_delay          = 1;
var m_level          = 3; 
var m_score          = 0;
var m_invalidSteps   = 0;
var m_index          = new Array();
var m_cmdStart       = null; 
var m_cmdEnd         = null;
var m_cboLevel       = null;
var m_gameTime       = null;
var m_objScore       = null;
var m_objGameTime    = null;
var m_objProgress    = null;
var m_objRows        = null;
var m_objCols        = null;
var m_objSetSize     = null;
var m_objTotalSteps  = null;
var m_objPerComplete = null;
var m_timerSeconds   = 0;
var m_timerCheckStep = 0;

function about()
{
    alert("This game is for kids");
}

function rand (n)
{
  	return (Math.floor(Math.random() * n));
}

function end()
{
	var r,c;
	var i = 0;
	for(r=0;r<m_rows;++r){
		for(c=0;c<m_cols;++c){
		    if(i != m_totals2){
    			var id = "i" + i;
	    		var obj = document.getElementById(id);
			    obj.src = m_images[i];
			}
			m_status[i++] = 2;
		}
	}
    m_mode = MODE_END;
	m_cmdStart.disabled = false;
	m_cmdEnd.disabled   = true;
	m_cboLevel.disabled   = false;
	m_objSetSize.disabled = false;
	m_objRows.disabled = false;
	m_objCols.disabled = false;
}

function startGame()
{
	init();
	//  
	m_score = 0;
	m_invalidSteps = 0;
	m_delay = m_cboLevel.value;
	     if(m_delay == 1)m_level = 3;
	else if(m_delay == 2)m_level = 2;
	else                 m_level = 1;
	//
	m_objSetSize.disabled = true;
	m_cmdStart.disabled = true;
	m_cmdEnd.disabled   = false;
	m_cboLevel.disabled = true;
	m_objRows.disabled  = true;
	m_objCols.disabled  = true;
	//
	m_stepSize = parseInt(m_objSetSize.value);
	if(m_stepSize > 2) m_delay *= (m_stepSize-1);
	//
	var k = 0;
	var i = 0;
	var j = 0;
	for(r=0;r<m_rows;++r){
		for(c=0;c<m_cols;++c){
		    if(i == m_totals2){
		        m_values[i] = -1;
		        m_types[i]  = -1;
		    }
		    else {
			    m_values[i] = k;
			    m_types[i]  = j++;
			    if(j == m_stepSize){
				    j = 0;
				    ++k;	
			    }
		    }
		    m_status[i++] = 0;
		}
	}
	if(m_totals2 > 0 && k != m_totals2)alert("Error");
    //
	for(i=0;i<100;++i){
		r = rand(m_totals);
        if(r == m_totals2)continue;
		c = rand(m_totals);
        if(c == m_totals2)continue;
		if(r != c){
			var temp    = m_values[r];
			m_values[r] = m_values[c];
			m_values[c] = temp;
            //
		<!--	var temp  = m_types[r];
			m_types[r] = m_types[c];
			m_types[c] = temp;
		}//-->
	}
	//
	var i = 0;
	for(r=0;r<m_rows;++r){
		for(c=0;c<m_cols;++c){
		    if(i != m_totals2){
   			    var id = "i" + i;
    		    var obj = document.getElementById(id);
	    	    obj.src = m_closedImage;
	        }
	        
	        var img = m_imgFolder +"/" ;
	        if(m_stepSize == 2){
	            if(m_types[i] == 1)img += "3";
	            else               img += "1";
            }	           
            else img += m_types[i];	            
            img += "-";
            img += (m_values[i] + 1);
            img += ".gif";		
	        m_images[i++] = img;		
		}
	}
	//
	for(i=0;i<m_stepSize;++i)m_index[i]  = -1;
	m_steps    =  0;
	m_mode     =  MODE_GAME;
	m_progress =  0;
	m_gameTime = new Date();
	m_timerSeconds = setTimeout("checkSeconds()",1000);
    m_objPerComplete.value = "0%";
    m_objGameTime.value = "0";
    m_objScore.value = ""+m_score; 
    m_objProgress.value = "0 of "+ m_totals;
    m_objTotalSteps.value = "0";   
}

function checkSeconds()
{
    if(m_mode == MODE_INIT)return;
    if(m_mode != MODE_END)return;
    //
    var d = new Date();
    var timeDiff = d.getTime() - m_gameTime.getTime();
    timeDiff /= 1000;
    var seconds = Math.round(timeDiff);
    m_objGameTime.value = "" + seconds;
    m_timerSeconds = setTimeout("checkSeconds()",1000);
}

function init()
{
    if(m_mode != MODE_INIT)return;
    //
	m_cmdStart      = document.getElementById("cmdStart");
	m_cmdEnd        = document.getElementById("cmdEnd");
	m_cboLevel      = document.getElementById("cboLevel");
	m_objScore      = document.getElementById("score");
	m_objGameTime   = document.getElementById("gameTime");
	m_objProgress   = document.getElementById("progress");
    m_objTotalSteps = document.getElementById("totalSteps"); 
    m_objPerComplete= document.getElementById("percentComplete");
    m_objSetSize    = document.getElementById("cboSetSize");
    m_objRows       = document.getElementById("rows");
    m_objCols       = document.getElementById("cols");
	//
	if(m_totalCells % 2 != 0){
	    --m_totalCells;
        m_totals2 = m_totalCells / 2;
    }
    else m_totals2 = -1;
    //
	m_mode = MODE_END;
	//
}

function selectItem(index)
{
	var i;
	//
	if(m_status[index] != 0){
	    //alert(""+index+","+m_status[index]);
	    return;
    }	  
	if(m_mode == MODE_RESETSELECTED){
	    alert(""+index+","+m_status[index]);
	    return;
    }	   
	//
	if(m_timerCheckStep != 0)clearTimeout(m_timerCheckStep);	
	var value = m_values[index];
	var id  = "i"+index;
	var obj = document.getElementById(id);
	obj.src = m_images[index];
	//
	++m_steps;
    obj =  document.getElementById("totalSteps"); 
    obj.value = "" + m_steps;   
    //	
    m_status[index] = 1;  // Open for short time
	m_index[m_mode] = index;
	//
	if(++m_mode == 1){
		m_timerCheckStep = setTimeout("checkStep()",1000 * m_delay);
		return;
	}
	else if(m_mode != m_stepSize){
		return;
	}
    //
	var j = m_index[0];	
	value = m_values[j];
    //
	for(i=1;i<m_stepSize;++i){
		j = m_index[i];	
		if(m_values[j] != value){
		    m_mode = MODE_RESETSELECTED;
			m_timerCheckStep = setTimeout("resetStep()",1000);
			++m_invalidSteps;
			m_score -= m_level;
			m_score -= m_invalidSteps;
			m_objScore.value = m_score;
			return;
		}
	}
	//
	for(i=0;i<m_stepSize;++i){
	    j = m_index[i];	
		m_status[j] =  2; // Permantly open
		m_index[i]  = -1;
	}
	//
	m_invalidSteps = 0;
	m_score += (m_level    * m_level);
	m_score += (m_stepSize * m_stepSize);
	m_objScore.value = m_score;
	m_mode   = 0;
	m_progress += m_stepSize;
	if(m_progress == m_totalCells){
	    m_objPerComplete.value = "100%";
        m_objProgress.value = "" + m_progress + " of " + m_totalCells;
		alert("Done !\nGame is Over\nYour score is "+m_score);
		end();
	}
	else {
	    var percent = m_progress;
	    percent *= 100;
	    percent /= m_totalCells;
	    m_objPerComplete.value = "" +Math.round(percent) + "%";
        m_objProgress.value = "" + m_progress + " of " + m_totalCells;
	}
}

function resetStep()
{
	var i;
	//
	clearTimeout(m_timerCheckStep);
	m_timerCheckStep = 0;
	for(i=0;i<m_stepSize;++i){
		if(m_index[i] >= 0){
		    var j = m_index[i];
		    m_status[j] = 0;
			var id  = "i"+j;
			var obj = document.getElementById(id);
			obj.src = closedImage;
			m_index[i]  = -1;
			m_status[j] =  0;
		}
	}
	m_mode = MODE_GAME;
}

function checkStep()
{
	resetStep();
}


