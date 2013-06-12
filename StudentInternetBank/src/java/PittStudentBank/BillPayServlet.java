/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PittStudentBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class BillPayServlet extends HttpServlet {
    
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
        userBean u1 = null;
        
        if (session != null && !session.isNew()) {
        // Session is valid          
          u1 = (userBean)session.getAttribute("u1");         
          session.setAttribute("u1", u1);
        }
        else {
          //Session is invalid
          response.sendRedirect("/HomePage.jsp");          
        }
        
        String ccnum = request.getParameter("Ccnum");
        Double amt = Double.parseDouble(request.getParameter("Amt"));
        int flag = Integer.parseInt(request.getParameter("flag"));
        
        ArrayList <Handler> accountinfo;
        accountinfo = new ArrayList<Handler>();
        
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());     
        int actid = 0, cctid = 0;
        int accnum; 
        double acbal = 0, acnewbal, ccbal = 0, ccnewbal, limit = 0;        
        String message,typeac,typecc,t;
        
        if(flag == 0)
        {        
            try{            
            Statement st;
            ResultSet rs;        
            st = conn.createStatement();
            st.execute("SELECT * FROM accountinfo;");
            rs = st.getResultSet();
            while(rs.next())
             {
                 if(u1.getUserName().equals(rs.getString("Username")))
                 {
                    accnum = rs.getInt("Accountnumber");
                    acbal = rs.getDouble("Balance");

                    Handler h = new Handler(accnum,acbal);
                    accountinfo.add(h);
                 }
             }    

             request.setAttribute("ccnum", ccnum);
             request.setAttribute("amt", amt);
             request.setAttribute("accinfo", accountinfo);
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/Billpay.jsp");
             rd.forward(request, response);   
            }

            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        else if(flag == 1)
        {
           accnum = Integer.parseInt(request.getParameter("Accnum"));
           try{            
                Statement st;
                ResultSet rs;        
                st = conn.createStatement();
                st.execute("SELECT * FROM accountinfo;");
                rs = st.getResultSet();
                while(rs.next())
                {
                    if(u1.getUserName().equals(rs.getString("Username")) && accnum == rs.getInt("Accountnumber"))
                    {                       
                       acbal = rs.getDouble("Balance");                       
                    }
                }
                
                if(acbal > amt)
                {                
                    message = "Payment Successful";
                    
                    st.execute("SELECT * FROM transactioninfo;");
                    rs = st.getResultSet();
                    while(rs.next())
                    {
                        if(accnum == rs.getInt("Accountnumber"))
                         {                            
                            actid = rs.getInt("TransactionId");      
                         }
                    }
                    
                    if(actid != 0)
                    {
                        actid = actid + 1;
                    }

                    else{
                        actid = Integer.parseInt(Integer.toString(accnum)+1);
                    }                    
                    
                    acnewbal = acbal - amt;                                        
                    typeac = "debit";
                    t = "Credit card bill payment";
                    
                    st.execute("SELECT * FROM creditcardinfo;");
                    rs = st.getResultSet();
                    while(rs.next())
                    {
                        if(u1.getUserName().equals(rs.getString("Username")) && ccnum.equals(rs.getString("Creditcardnumber")))
                        {                       
                           ccbal = rs.getDouble("Balance");
                           limit = rs.getDouble("Limit");
                        }
                    }
                    
                    ccnewbal = ccbal + amt;
                    typecc = "credit";
                    
                    st.execute("SELECT * FROM cctransactioninfo;");
                    rs = st.getResultSet();
                    while(rs.next())
                    {
                        if(ccnum.equals(rs.getString("Creditcardnumber")))
                         {                           
                            cctid = rs.getInt("TransactionId");      
                         }
                    }
                    
                    if(cctid != 0)
                    {
                        cctid = cctid + 1;
                    }

                    else{
                        cctid = Integer.parseInt(ccnum.substring(12)+1);
                    }
                    
                    PreparedStatement ps;
                
                    ps = conn.prepareStatement("UPDATE accountinfo SET Balance = ? WHERE Accountnumber = ?");                                                
                    ps.setDouble(1, acnewbal);
                    ps.setInt(2, accnum);
                    ps.execute();                

                    ps = conn.prepareStatement("INSERT INTO transactioninfo VALUES(?,?,?,?,?,?,?)");                
                    ps.setInt(1, actid);
                    ps.setInt(2, accnum);
                    ps.setDouble(3, acnewbal);
                    ps.setString(4, typeac);
                    ps.setDouble(5, amt);
                    ps.setString(6, t);
                    ps.setString(7, timeStamp);
                    ps.execute(); 
                    
                    ps = conn.prepareStatement("UPDATE creditcardinfo SET Balance = ? WHERE Creditcardnumber = ?");                                                
                    ps.setDouble(1, ccnewbal);
                    ps.setString(2, ccnum);
                    ps.execute();                

                    ps = conn.prepareStatement("INSERT INTO cctransactioninfo VALUES(?,?,?,?,?,?,?,?)");                
                    ps.setInt(1, cctid);
                    ps.setString(2, ccnum);
                    ps.setDouble(3, limit);
                    ps.setDouble(4, ccnewbal);
                    ps.setDouble(5, amt);
                    ps.setString(6, typecc);                   
                    ps.setString(7, t);
                    ps.setString(8, timeStamp);
                    ps.execute();
                }
                
                else
                {
                    message = "You do not have sufficient funds in this account.Select another account";
                }
                
                request.setAttribute("message", message);
                request.setAttribute("ccnum", ccnum);
                request.setAttribute("amt", amt);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/PaymentDisplay.jsp");
                rd.forward(request, response);   
           }
           
           catch(Exception e)
            {
                e.printStackTrace();
            }          
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
