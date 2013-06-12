/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PurchaseandDeposit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author praveen
 */
public class PurchaseServlet extends HttpServlet {
    
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
        PrintWriter out = response.getWriter();
        
        int acctnum = 0;
        String dcnum = null, ccnum = null;
        double oldbal = 0.00, limit = 0.00;
        double newbal = 0.00;
        String type;        
        int tid = 0;
        int flag = 0;
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String p = request.getParameter("purchasefor");
        String paymode = request.getParameter("paymentmode");
        System.out.println(paymode);
        
        if(paymode.equals("acct"))
        {
            acctnum = Integer.parseInt(request.getParameter("acctnum"));
            System.out.println(acctnum + "purchaseservlet");
        }
        
        else if(paymode.equals("dc"))
        {
            dcnum = request.getParameter("dcnum");
        }
        
        else if(paymode.equals("cc"))
        {
            ccnum = request.getParameter("ccnum");
        }
        
        int amt = Integer.parseInt(request.getParameter("amount"));        
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());   
        
        
        try{            
            Statement st;
            ResultSet rs;        
            st = conn.createStatement();
            st.execute("SELECT * FROM userinfo;");
            rs = st.getResultSet();
            while(rs.next())
             {
               if(user.equals(rs.getString("Username")) && pass.equals(rs.getString("Password")))
               {
                   flag = 1;
                   System.out.println(flag);
               }
             }

             if(flag == 1)
             {
                if(paymode.equals("acct") || paymode.equals("dc"))
                {
                    st.execute("SELECT * FROM accountinfo;");
                    rs = st.getResultSet();
                    while(rs.next())
                    {
                        if(user.equals(rs.getString("Username")) && acctnum == rs.getInt("Accountnumber"))
                         {  
                            System.out.println("inside if "+ rs.getInt("Accountnumber")); 
                            oldbal = rs.getDouble("Balance");      
                            System.out.println(oldbal);
                         }
                    }

                    if(oldbal == 0.00)
                    {
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Purchase Details</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1> Please enter a valid accountnumber </h1>");
                        out.println("<a href='HomePage.jsp'> Go Home </a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    
                    else{
                        st.execute("SELECT * FROM transactioninfo;");
                        rs = st.getResultSet();
                        while(rs.next())
                        {
                            if(acctnum == rs.getInt("Accountnumber"))
                             {
                                System.out.println("inside if " + rs.getInt("TransactionId"));
                                tid = rs.getInt("TransactionId");      
                             }
                        }              

                        System.out.println(tid);
                        if(tid != 0)
                        {
                            tid = tid + 1;
                        }

                        else{
                            tid = Integer.parseInt(Integer.toString(acctnum)+1);
                        }
                        
                        newbal = oldbal - amt;
                        type = "debit";

                        PreparedStatement ps;

                        ps = conn.prepareStatement("UPDATE accountinfo SET Balance = ? WHERE Accountnumber = ?");                                                
                        ps.setDouble(1, newbal);
                        ps.setInt(2, acctnum);
                        ps.execute();                

                        ps = conn.prepareStatement("INSERT INTO transactioninfo VALUES(?,?,?,?,?,?,?)");                
                        ps.setInt(1, tid);
                        ps.setInt(2, acctnum);
                        ps.setDouble(3, newbal);
                        ps.setString(4, type);
                        ps.setInt(5, amt);
                        ps.setString(6, p);
                        ps.setString(7, timeStamp);
                        ps.execute();                

                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Purchase Details</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1> Purchase done successfully </h1>");
                        out.println("<a href='HomePage.jsp'> Go Home </a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
             
             
                else if(paymode.equals("cc"))
                {
                   st.execute("SELECT * FROM creditcardinfo;");
                   rs = st.getResultSet();
                   while(rs.next())
                   {
                       if(user.equals(rs.getString("Username")) && ccnum.equals(rs.getString("Creditcardnumber")))
                        {
                           limit = rs.getDouble("Limit");
                           oldbal = rs.getDouble("Balance");      
                           System.out.println(oldbal);
                        }
                   }

                   if(oldbal == 0.00)
                   {
                       out.println("<html>");
                       out.println("<head>");
                       out.println("<title>Purchase Details</title>");            
                       out.println("</head>");
                       out.println("<body>");
                       out.println("<h1> Your Card has Nil Balance </h1>");
                       out.println("<a href='HomePage.jsp'> Go Home </a>");
                       out.println("</body>");
                       out.println("</html>");

                   }

                   st.execute("SELECT * FROM cctransactioninfo;");
                   rs = st.getResultSet();
                   while(rs.next())
                   {
                       if(ccnum.equals(rs.getString("Creditcardnumber")))
                        {
                           System.out.println("inside if " + rs.getInt("TransactionId"));
                           tid = rs.getInt("TransactionId");      
                        }
                   }
                   
                   if(tid != 0)
                   {
                        tid = tid + 1;
                   }

                   else{
                        tid = Integer.parseInt(ccnum.substring(12)+1);
                   }
                  
                   newbal = oldbal - amt;
                   type = "debit";

                   PreparedStatement ps;

                   ps = conn.prepareStatement("UPDATE creditcardinfo SET Balance = ? WHERE Creditcardnumber = ?");                                                
                   ps.setDouble(1, newbal);
                   ps.setString(2, ccnum);
                   ps.execute();                

                   ps = conn.prepareStatement("INSERT INTO cctransactioninfo VALUES(?,?,?,?,?,?,?,?)");                
                   ps.setInt(1, tid);
                   ps.setString(2, ccnum);
                   ps.setDouble(3, limit);
                   ps.setDouble(4, newbal);
                   ps.setInt(5, amt);
                   ps.setString(6, type);                   
                   ps.setString(7, p);
                   ps.setString(8, timeStamp);
                   ps.execute();                

                   out.println("<html>");
                   out.println("<head>");
                   out.println("<title>Purchase Details</title>");            
                   out.println("</head>");
                   out.println("<body>");
                   out.println("<h1> Purchase done successfully </h1>");
                   out.println("<a href='HomePage.jsp'> Go Home </a>");
                   out.println("</body>");
                   out.println("</html>");
                }
            }

            else
            {
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Purchase Details</title>");            
               out.println("</head>");
               out.println("<body>");
               out.println("<h1> Invalid Username or Password </h1>");
               out.println("<a href='HomePage.jsp'> Go Home </a>");
               out.println("</body>");
               out.println("</html>");
            }
                     
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        finally {            
            out.close();
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
