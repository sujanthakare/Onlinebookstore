/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


/**
 *
 * @author sujan
 */
@WebServlet(urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
                
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                         
       
  
   
      processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String a=request.getParameter("username");
        String b=request.getParameter("password");
        String c=request.getParameter("emailid");
        String d=request.getParameter("address");
        String e=request.getParameter("phone");
   
     
                try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con;
                        con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            PreparedStatement ps;
            ps=con.prepareStatement("insert into userdata values(?,?,?,?,?)");
            ps.setString(1,a);
            ps.setString(2,b);
            ps.setString(3,c);
            ps.setString(4,d);
            ps.setString(5,e);
            
            int m = ps.executeUpdate();
            if(m>0)
            {
                  
          
                    RequestDispatcher  i=request.getRequestDispatcher ("success.html");
                    i.forward(request, response);
                    processRequest(request, response);
                
            }
            
           
        } catch (ClassNotFoundException ex) {
          
            
        }catch (SQLException ex){
            
                
        }
                finally{
               out.close();
                }
          
            
     }
   
}
