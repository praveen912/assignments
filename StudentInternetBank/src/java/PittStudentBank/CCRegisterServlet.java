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
import java.sql.SQLException;
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
public class CCRegisterServlet extends HttpServlet {
    
    public Connection conn = null;    
    
   @Override public void init() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "studentbank";
        String driver = "com.mysql.jdbc.Driver";
        String Username = "root"; 
        String password = "";
     
        try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url+dbName,Username,password);
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
        
        String EmpName, Address, Street, City, msg = null, Designation;                
        int DoorNo, Zip, pin, Salary, bal, limit;
        long Cc;
        String ccnum = "",p1,p2;
                
        EmpName = request.getParameter("company");        
        DoorNo = Integer.parseInt(request.getParameter("DoorNo"));
        Street = request.getParameter("Street");
        City = request.getParameter("City");
        Zip = Integer.parseInt(request.getParameter("Zip"));
        Salary = Integer.parseInt(request.getParameter("salary"));
        Designation = request.getParameter("designation");
        pin = Integer.parseInt(request.getParameter("pin"));
                
        Address = DoorNo+", "+Street+", "+City+", "+Zip;
        Statement st;
        ResultSet rs;
        PreparedStatement ps;
         
        try {          
                Cc = (Math.round(Math.random() * 100000000));                
                p1 = Long.toString(Cc);
                Cc = (Math.round(Math.random() * 100000000));                
                p2 = Long.toString(Cc);
                ccnum = p1+p2;
                
                bal = limit = Salary*2;                
                
                ps = conn.prepareStatement("Insert into creditcardinfo values(?, ?, ?, ?, ?)");             
                
                ps.setString(1, u1.getUserName());
                ps.setString(2, ccnum);
                ps.setInt(3, pin);
                ps.setInt(4, bal);
                ps.setInt(5, limit);                
                ps.execute();      
                
                ps = conn.prepareStatement("Update accountinfo Set Creditcardnumber = ? where Username = ?");             
                
                ps.setString(1, ccnum);                
                ps.setString(2, u1.getUserName());                      
                ps.execute();      
                
                ps = conn.prepareStatement("Insert into useremployerinfo values(?,?,?,?,?)");             
                
                ps.setString(1, u1.getUserName());                
                ps.setString(2, EmpName);                
                ps.setString(3, Address);
                ps.setString(4, Designation);
                ps.setInt(5, Salary);
                ps.execute();      
                
               msg = "Registered Successfully. Your Credit Card Number is "+ ccnum;                              
               request.setAttribute("msg", msg);
               request.setAttribute("ccnum", ccnum);
               request.setAttribute("balance", bal);
               request.setAttribute("limit", limit);
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/RegisterSuccess.jsp");
               rd.forward(request, response);
            }      
            catch(SQLException sql){
                sql.printStackTrace();
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
