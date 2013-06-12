/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PittStudentBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author praveen
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        
        userBean u1 = new userBean();
        HttpSession session=request.getSession(true);
        session.setAttribute("u1",u1);
       
        String FirstName, MiddleName, LastName, Address, Email, Gender, DOB, UserName, Password, ssn, Street, City, msg = null;                
        int DoorNo, Zip, AccountNo, bal = 0;
        long Acc;
        String ccnum = "", dcnum,p1,p2;
                
        FirstName = request.getParameter("FirstName");
        MiddleName = request.getParameter("MiddleName"); 
        LastName = request.getParameter("LastName");
        ssn = request.getParameter("ssn");
        DoorNo = Integer.parseInt(request.getParameter("DoorNo"));
        Street = request.getParameter("Street");
        City = request.getParameter("City");
        Zip = Integer.parseInt(request.getParameter("Zip"));
        DOB = request.getParameter("dob");        
        Email = request.getParameter("Email");
        Gender = request.getParameter("Gender");
        UserName = request.getParameter("Username");
        Password = request.getParameter("Password");                
        
        Address = DoorNo+", "+Street+", "+City+", "+Zip;
        Statement st;
        ResultSet rs;
        PreparedStatement ps;
         
        try {
 
                st = conn.createStatement(); 
                st.execute("Select Username, Password from userinfo"); 
                rs = st.getResultSet(); 
                while (rs.next())
                {                 
                    if(rs.getString("Username").equals(UserName))       
                    {
                        msg = "UserName already exists";                        
                        request.setAttribute("msg", msg);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
                        rd.forward(request, response);
                    }
                }
                
                Acc = (Math.round(Math.random() * 100000));                
                AccountNo = (int)Acc;
                
                Acc = (Math.round(Math.random() * 100000000));                
                p1 = Long.toString(Acc);
                Acc = (Math.round(Math.random() * 100000000));                
                p2 = Long.toString(Acc);
                dcnum = p1+p2;
                
                ps = conn.prepareStatement("Insert into userinfo values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");             
                
                ps.setString(1, UserName);
                ps.setString(2, Password);
                ps.setString(3, FirstName);
                ps.setString(4, MiddleName);
                ps.setString(5, LastName);
                ps.setString(6, Gender);
                ps.setString(7, Address);
                ps.setString(8, Email);                
                ps.setString(9, DOB); 
                ps.setString(10, ssn);
                ps.execute();      
                
                ps = conn.prepareStatement("Insert into accountinfo values(?, ?, ?, ?, ?)");             
                
                ps.setString(1, UserName);                
                ps.setInt(2, AccountNo);
                ps.setInt(3, bal);
                ps.setString(4, dcnum);
                ps.setString(5, ccnum);                
                ps.execute();      
                
               msg = "Registered Successfully. Your Account Number is " + AccountNo + ". Your Debit Card Number is "+ dcnum;
               u1.setLoginStatus(msg);
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginFailure.jsp");
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
