
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brily Branco
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
    Connection connection;
    PreparedStatement prepare;
    
    public void doGet(HttpServletRequest request,HttpServletResponse respone)throws IOException,ServletException{
        respone.setContentType("text/html");
        PrintWriter out=respone.getWriter();
        String idhapus=request.getParameter("iddelete");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem","root","");
            prepare=connection.prepareStatement("delete from book where bookid=?");
            prepare.setString(1,idhapus);
            prepare.executeUpdate();
            respone.sendRedirect("http://localhost:8080/Bookmanagementsystem/lihatbuku");
        } catch (Exception e) {
            out.println("Koneksi gagal "+e.getMessage());
        }
        
    }
    
}
