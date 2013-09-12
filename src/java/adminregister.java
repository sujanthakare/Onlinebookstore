/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(urlPatterns = {"/adminregister"})
public class adminregister extends HttpServlet {

    
    
     public boolean validate(String passkey)
    {
        boolean status = false;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con;
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app", "app");
            
            PreparedStatement ps = con.prepareStatement("select * from admipasskey where akey=?");
            
            ps.setString(1,passkey);
            
            ResultSet rs = ps.executeQuery();
            
            status = rs.next();
            
           
        }catch(Exception e){
            
        }
        
        return status;
    }
        
          
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String a = request.getParameter("adminid");
        String b = request.getParameter("password");
        String c = request.getParameter("passkey");
        
        
        try
        {
           Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection con;
                        con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            PreparedStatement ps;
             ps=con.prepareStatement("insert into admindata values(?,?)");
             ps.setString(1,a);
             ps.setString(2,b);
              int m = ps.executeUpdate();
            if(m>0 && validate(c))
            {
                RequestDispatcher  i=request.getRequestDispatcher ("adminhome.html");
                    i.forward(request, response);
                    PreparedStatement ps1;
                     ps1=con.prepareStatement("delete akey from adminpasskey where akey=c ");
                   
            }
            else{
                    out.println("wrong passkey");
                   RequestDispatcher  i=request.getRequestDispatcher ("editadmin.html");
                    i.forward(request, response);
                    PreparedStatement ps1;
                
            }
              
        }
        catch(Exception e)
        {
            
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
