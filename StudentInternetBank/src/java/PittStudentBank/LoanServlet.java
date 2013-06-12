/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PittStudentBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class LoanServlet extends HttpServlet {
    
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
        
        int bal = 0, limit = 0;
        String cc = null,message = null;      
                
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
                cc = rs.getString("Creditcardnumber");
             }
         }
        
        if(!cc.equals(""))
        {
            st.execute("SELECT * FROM creditcardinfo;");
            rs = st.getResultSet();
            while(rs.next())
             {
                 if(cc.equals(rs.getString("Creditcardnumber")))
                 {
                    bal = rs.getInt("Balance");
                    limit = rs.getInt("Limit");
                 }
             }
            
            if(bal >= limit/2)
            {
                message = "You are eligible to apply for a loan";
               // request.setAttribute("balance", bal);
                //request.setAttribute("limit", limit);
            }
            
            else
            {
                message = "You do not have sufficient Credit balance to be eligible for a loan. Please pay your CreditCard Bill to improve your Credit Balance";
            }
        }
        
        else{
            
            message = "Please apply for Credit Card. You do not qualify for a loan";
        }        
         
         request.setAttribute("message", message);
         RequestDispatcher rd = getServletContext().getRequestDispatcher("/Loan.jsp");
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
