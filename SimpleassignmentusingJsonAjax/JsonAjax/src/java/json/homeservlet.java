package json;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 *
 * @author PraveenNK
 */

@WebServlet(name = "homeservlet", urlPatterns = {"/homeservlet"})
public class homeservlet extends HttpServlet {

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
    public Connection conn = null;    
    
   @Override public void init() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "json";
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        char[] cbuf = new char[20000];
        int amtread;
        JSONObject jo,jo2,jo3;
        PrintWriter out = response.getWriter();
        BufferedReader in = request.getReader();
        amtread = in.read(cbuf);        
        System.out.println("amtread ="+amtread);
        cbuf[amtread]='\0';
        int frm;
        String Username,User = null;
        String invalue = new String(cbuf,0,amtread);
        int flags,flag=0;         
       try {
            jo=new JSONObject(invalue);
            jo2=new JSONObject();
            Statement st;
            ResultSet rs;        
            PreparedStatement ps;
            System.out.println(jo);
            frm = Integer.parseInt(jo.getString("filledper"));
            
            if(frm != -1)
            {
                flags = Integer.parseInt(jo.getString("flags"));            
                int percent = flags*10;            
                String Messages = new String("<br/>Percentage of the form filled<br/>"  + percent + " %");            
                System.out.println(Messages);
                Username = jo.getString("Username");
                System.out.println(Username);
                
                st = conn.createStatement();
                st.execute("SELECT username FROM userdata");
                rs = st.getResultSet();

                if (Username.equals(""))
                {
                    User = new String("enter username");
                }
                else
                {
                    while(rs.next())
                    {          
                       if (rs.getString("username").equals(Username))
                       {
                           User = new String("Username already exist. Please try another name");                        
                           flag = 1;
                       }

                    }
                   if(flag == 0)                  
                   {                   
                       User = new String("Username successful");                   
                   }                  
                }                    
                System.out.println(User);
                jo2.put("percentage", Messages);                     
                jo2.put("unameavailability", User);            
                System.out.println(jo2);
                out.println(jo2.toString());
            }
            else
            {
                System.out.println("Registered Successfully");
                Username = jo.getString("Username");
                ps = conn.prepareStatement("Insert into userdata values(?)");      
                ps.setString(1, Username);
                ps.execute();
                jo2.put("submitted", "Registered Successfully");                     
                out.println(jo2.toString());
                System.out.println(jo2);
            }
       }
        catch (SQLException ex) {
            ex.printStackTrace();      
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(homeservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(homeservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
