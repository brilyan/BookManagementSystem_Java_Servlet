
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
@WebServlet("/Editreturn")
public class Editreturn extends HttpServlet {
    
    Connection connection;
    ResultSet resultset;
    PreparedStatement prepare;
    
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String id=request.getParameter("idedit");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem","root","");
            prepare=connection.prepareStatement("select * from book where bookid=?");
            prepare.setString(1, id);
            resultset=prepare.executeQuery();
            
            while(resultset.next()){
                out.print("<form action='Edit' method='POST' >");
                out.println("<table border='10'>");
                out.println("<tr><td>Book ID</td>  <td><input type='text' name='txtidedit' id='txtidedit' value='"+resultset.getString("bookid")+"'/></td></tr>");
                out.println("<tr><td>Book Name</td> <td><input type='text' name='txtnamaedit' id='txtnamaedit' value='"+resultset.getString("name")+"'/></td></tr>");
                out.println("<tr><td>Author</td> <td><input type='text' name='txtauthoredit' id='txtauthoredit' value='"+resultset.getString("author")+"'/></td></tr>");
                out.println("<tr><td>Year</td> <td><input type='text' name='txtyearedit' id='txtyearedit' value='"+resultset.getString("year")+"'/></td></tr>");
                out.println("<tr><td colspan='2'> <input type='submit' name='btnedit' id='btnedit' value='Edit'/></td></tr>");
                out.println("</table>");
                out.println("</form>");
            }
        } catch (Exception e) {
            out.println("Koneksi gagal "+e.getMessage());
        }
    }
    
}
