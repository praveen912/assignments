/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PittStudentBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author praveen
 */
public class AccountDetailsServlet extends HttpServlet {
    
    public Connection conn = null;    
    
   @Override public void init() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "studentbank";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = "";
     
        try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url+dbName,userName,password);
        System.out.println(conn);
        System.out.println("Connected to the database");
        }
        
        catch(Exception e){
            e.printStackTrace();
        }        
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session=request.getSession(true);
        userBean u1;
        
        if (session != null && !session.isNew()) {
        // Session is valid          
          u1 = (userBean)session.getAttribute("u1");         
          session.setAttribute("u1", u1);
        }
        else {
          //Session is invalid
          response.sendRedirect("/HomePage.jsp");          
        } 
                
        ArrayList <Handler> transinfo;
        transinfo = new ArrayList<Handler>();
        Handler h;
        
        int tid,flag = 0,info;
        int count = 0;
        double bal, amt;
        String tranto, dt, type, mon = null, yr = null, title = null;
        String accountnumber, stmt, month, message;
        
        accountnumber = request.getParameter("Accnum"); 
        stmt = request.getParameter("Stmt");        
        month = new SimpleDateFormat("MM").format(new Date());        
        
        if(stmt.equals("previousmy"))
        {
            flag = Integer.parseInt(request.getParameter("flag"));
            if(flag == 1)
            {
                mon = request.getParameter("month");
                yr = request.getParameter("year");          
                System.out.println(mon+" "+yr);
            }
        }    
        
        //Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        Pattern p = Pattern.compile("([^\\d]+)");   
                
        try{            
        Statement st;
        ResultSet rs;        
        st = conn.createStatement();
        st.execute("SELECT * FROM transactioninfo;");
        rs = st.getResultSet();    
        
            if(stmt.equals("last10"))
            {                
                title = "Last 10 Transactions are displayed below";
                rs.last();
                do 
                 {                 
                     if(accountnumber.equals(rs.getString("Accountnumber")))
                     {                         
                        tid = rs.getInt("TransactionId");
                        bal = rs.getDouble("Balance");
                        type = rs.getString("Type");
                        amt = rs.getDouble("Amount");
                        tranto = rs.getString("Transfertofrom");
                        dt = rs.getString("DateandTime");                                              
                        //dt = dt.trim();
                        
                        h = new Handler(tid,bal,amt,type,tranto,dt);
                        transinfo.add(h);
                        count++;
                        
                        if(count == 10)
                        {break;}
                     }                 
                 }while(rs.previous());
                
                if(transinfo.isEmpty())
                {
                    message = "There are no Transactions available for this account";
                    info = 0;
                    request.setAttribute("info", info);   
                    request.setAttribute("message", message);                       
                    request.setAttribute("acct", accountnumber);   
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Invalid.jsp");
                    rd.forward(request, response);                         
                }
            }            
            
            else if(stmt.equals("currentmonth"))
            {
                title = "Current Month Transactions are displayed below";
                while(rs.next())
                 {                 
                     String m = rs.getString("DateandTime");
                     String[] pm = p.split(m);
                     if(accountnumber.equals(rs.getString("Accountnumber")) && month.equals(pm[1]))
                     {                         
                        tid = rs.getInt("TransactionId");
                        bal = rs.getDouble("Balance");
                        type = rs.getString("Type");
                        amt = rs.getDouble("Amount");
                        tranto = rs.getString("Transfertofrom");
                        dt = rs.getString("DateandTime");                      

                        h = new Handler(tid,bal,amt,type,tranto,dt);
                        transinfo.add(h);                        
                     }                 
                 }
                
                if(transinfo.isEmpty())
                {
                    message = "There are no Transactions available in this month";
                    info = 0;
                    request.setAttribute("info", info);   
                    request.setAttribute("message", message);   
                    request.setAttribute("acct", accountnumber);   
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Invalid.jsp");
                    rd.forward(request, response);                         
                }
            }
            
            else if(stmt.equals("previousmy"))
            {                
                if(flag == 0)
                {          
                    request.setAttribute("acct", accountnumber);   
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/AccountDisplay.jsp");
                    rd.forward(request, response);     
                }
                else
                {
                    title = "Transactions for the period " + mon + "/" + yr +  " are displayed below";
                    while(rs.next())
                    {                 
                        String m = rs.getString("DateandTime");
                        String[] pm = p.split(m);
                        if(accountnumber.equals(rs.getString("Accountnumber")) && mon.equals(pm[1]) && yr.equals(pm[0]))
                        {                         
                           tid = rs.getInt("TransactionId");
                           bal = rs.getDouble("Balance");
                           type = rs.getString("Type");
                           amt = rs.getDouble("Amount");
                           tranto = rs.getString("Transfertofrom");
                           dt = rs.getString("DateandTime");                      

                           h = new Handler(tid,bal,amt,type,tranto,dt);
                           transinfo.add(h);                        
                        }                 
                    }
                    
                    if(transinfo.isEmpty())
                    {
                        message = "There are no Transactions available in this period";
                        info = 0;
                        request.setAttribute("info", info);   
                        request.setAttribute("message", message);  
                        request.setAttribute("acct", accountnumber);   
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Invalid.jsp");
                        rd.forward(request, response);                         
                    }
                }
            }
            
            request.setAttribute("title", title);   
            request.setAttribute("acct", accountnumber);   
            request.setAttribute("transinfo", transinfo);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/AccountDetails.jsp");
            rd.forward(request, response); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
