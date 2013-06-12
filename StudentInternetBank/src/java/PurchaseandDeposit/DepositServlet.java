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
public class DepositServlet extends HttpServlet {
    
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
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String d = request.getParameter("deposit");
        int acctnum = Integer.parseInt(request.getParameter("acctnum"));
        System.out.println(acctnum);
        int amt = Integer.parseInt(request.getParameter("amount"));
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        double oldbal = 0.00;
        double newbal;
        String type;        
        int tid = 0;
        int flag = 0;
        
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
               }
             }

             if(flag == 1)
             {
                st.execute("SELECT * FROM accountinfo;");
                rs = st.getResultSet();
                while(rs.next())
                {
                    if(user.equals(rs.getString("Username")) && acctnum == rs.getInt("Accountnumber"))
                     {                    
                        oldbal = rs.getDouble("Balance");      
                        System.out.println(oldbal);
                     }
                }
                
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
                
                if(tid != 0)
                {
                    tid = tid + 1;
                }
                
                else{
                    tid = Integer.parseInt(Integer.toString(acctnum)+1);
                }
                
                newbal = oldbal + amt;
                type = "credit";
                    
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
                ps.setString(6, d);
                ps.setString(7, timeStamp);
                ps.execute();                
                
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Deposit Details</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Deposited successfully </h1>");
                out.println("<a href='HomePage.jsp'> Go Home </a>");
                out.println("</body>");
                out.println("</html>");
             }
             
             else
             {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Deposit Details</title>");            
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
