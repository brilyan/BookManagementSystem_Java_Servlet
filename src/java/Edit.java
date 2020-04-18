
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
@WebServlet("/Edit")
public class Edit extends HttpServlet{
    Connection connection;
    ResultSet resultset;
    PreparedStatement prepare;
    int row;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String id=request.getParameter("txtidedit");
        String nama=request.getParameter("txtnamaedit");
        String author=request.getParameter("txtauthoredit");
        String year=request.getParameter("txtyearedit");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem","root","");
            prepare=connection.prepareStatement("update book set name=?,author=?,year=? where bookid=?");
            prepare.setString(1,nama);
            prepare.setString(2,author);
            prepare.setString(3,year);
            prepare.setString(4,id);
            row=prepare.executeUpdate();
            response.sendRedirect("http://localhost:8080/Bookmanagementsystem/lihatbuku");
        } catch (Exception e) {
            out.println("Koneksi gagal "+e.getMessage());
        }
        
    }
}
