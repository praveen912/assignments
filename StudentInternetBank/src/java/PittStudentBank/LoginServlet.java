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
public class LoginServlet extends HttpServlet {
    
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
        
        userBean u1 = new userBean();
        HttpSession session=request.getSession(true);
        session.setAttribute("u1",u1);     
        
        try{
        Statement st;
        ResultSet rs;        
        st = conn.createStatement();
        st.execute("SELECT * FROM userinfo;");
        rs = st.getResultSet();
        while(rs.next())
         {
           if(request.getParameter("username").equals(rs.getString("Username")) && request.getParameter("password").equals(rs.getString("Password")))
           {
               u1.setName(rs.getString("FirstName"));
               u1.setUserName(rs.getString("Username"));
               u1.setLoginStatus("Login Successful");
               u1.setId(1);
               System.out.println("Username and Password Matches");               
               
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/Welcome.jsp");
               rd.forward(request, response);                             
           }
         }
        
        if(u1.getName() == null)
           {
               u1.setLoginStatus("Incorrect Username or Password");
               System.out.println("Incorrect Username or Password");
               
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginFailure.jsp");
               rd.forward(request, response);               
           }       
       }
        catch(SQLException e)
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
