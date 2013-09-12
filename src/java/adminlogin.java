/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sujan
 */
@WebServlet(urlPatterns = {"/adminlogin"})
public class adminlogin extends HttpServlet {

   
  
public boolean validate (String adminid,String password)
{
    boolean status = false;
    try{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con;
        con=DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app", "app");
        
        PreparedStatement ps = con.prepareStatement("select * from admindata where adminid=? and password=?");
        
        ps.setString(1,adminid);
        ps.setString(2,password);
        
        ResultSet rs = ps.executeQuery();
        status = rs.next();
        
        
    }catch(ClassNotFoundException | SQLException e){
        
    }
    return status;
}
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
    try (PrintWriter out = response.getWriter()) {
        String a = request.getParameter("adminid");
        String b = request.getParameter("password");
        
        
        if(validate(a,b))
        {
            out.println("Successfully login");
            RequestDispatcher i = request.getRequestDispatcher("adminhome.html");
            i.include(request, response);
        }
        else
        {
            out.println("Wrong adminid or password ");
            RequestDispatcher o = request.getRequestDispatcher("admin.html");
            o.include(request, response);
        }
    }   
    }

   
  
}
