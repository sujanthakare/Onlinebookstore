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


@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {


    public boolean validate(String username,String password)
    {
        boolean status = false;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con;
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app", "app");
            
            PreparedStatement ps = con.prepareStatement("select * from userdata where username=? and password=?");
            
            ps.setString(1,username);
            ps.setString(2,password);
            
            ResultSet rs = ps.executeQuery();
            
            status = rs.next();
            
           
        }
        
        catch(Exception e)
        {
            System.out.println("e");
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
        PrintWriter out = response.getWriter();
        
        String n = request.getParameter("username");
        String p = request.getParameter("password");
        
        if(validate(n,p))
        { 
            out.println("successfully login");
            RequestDispatcher rd = request.getRequestDispatcher("loginsuccess.html");
            rd.include(request, response);
        }
        else
        {
            out.println("Wrong username or password");
            RequestDispatcher rd = request.getRequestDispatcher("signin.html");
            rd.include(request, response);
        }
        out.close();
    }
}
