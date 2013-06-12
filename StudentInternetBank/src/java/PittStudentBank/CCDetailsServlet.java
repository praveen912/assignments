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
public class CCDetailsServlet extends HttpServlet {
    
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
                
        ArrayList <Handler> cctransinfo;
        cctransinfo = new ArrayList<Handler>();
        Handler h;
        
        Pattern p = Pattern.compile("([^\\d]+)");   
        String month = new SimpleDateFormat("MM").format(new Date());
        String ccnum = request.getParameter("Ccnum");
        
        int tid;        
        double bal, amt, limit;
        String tranto, dt, type, message;
        int info;
        
        try{            
            Statement st;
            ResultSet rs;        
            st = conn.createStatement();
            st.execute("SELECT * FROM cctransactioninfo;");
            rs = st.getResultSet();  
            while(rs.next())
            {                 
                String m = rs.getString("DateandTime");
                String[] pm = p.split(m);
                System.out.println(pm[1]);
                if(ccnum.equals(rs.getString("Creditcardnumber")) && month.equals(pm[1]))
                {                         
                   tid = rs.getInt("TransactionId");
                   limit = rs.getDouble("Limit");
                   bal = rs.getDouble("Balance");
                   type = rs.getString("Type");
                   amt = rs.getDouble("Amount");
                   tranto = rs.getString("Transferfor");
                   dt = rs.getString("DateandTime");                      
                   System.out.println(tid+" "+limit+" "+bal+" "+type+" "+amt+" "+tranto+" "+dt); 
                   h = new Handler(tid,limit,bal,amt,type,tranto,dt);
                   cctransinfo.add(h);                        
                }                 
            }           
            
            if(cctransinfo.isEmpty())
            {
                message = "There are no Transactions available in this month";
                info = 1;
                request.setAttribute("message", message);   
                request.setAttribute("info", info);   
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Invalid.jsp");
                rd.forward(request, response);                         
            }
            
            request.setAttribute("Ccnum", ccnum);
            request.setAttribute("cctransinfo", cctransinfo);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/CCDetails.jsp");
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
