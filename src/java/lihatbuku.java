
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brily Branco
 */
@WebServlet("/lihatbuku")
public class lihatbuku extends HttpServlet{
    Connection connection;
    PreparedStatement prepare;
    ResultSet resultset;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem","root","");
            prepare=connection.prepareStatement("select * from book");
            resultset=prepare.executeQuery();
            
            out.println("<h3>Daftar Buku</h3>");
            
            out.println("<table cellspacing='0' width='890px' border='10'>");
            out.println("<tr>");
            out.println("<td>ID</td>");
            out.println("<td>Name</td>");
            out.println("<td>Author</td>");
            out.println("<td>Year</td>");
            out.println("<td>Edit</td>");
            out.println("<td>Delete</td>");
            out.println("</tr>");
            
            while(resultset.next()){
                out.println("<tr>");
                out.println("<td>" +resultset.getString("bookid")+"</td>");
                out.println("<td>" +resultset.getString("name")+"</td>");
                out.println("<td>"+resultset.getString("author")+"</td>");
                out.println("<td>"+resultset.getString("year")+"</td>");
                out.println("<td>"+ "<a href='Editreturn?idedit="+resultset.getString("bookid")+"'>Edit</a>"+"</td>");
                out.println("<td>"+ "<a href='Delete?iddelete="+resultset.getString("bookid")+"'>Delete</a>"+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            out.println("Koneksi gagal "+e.getMessage());
        }
        
    }
}
