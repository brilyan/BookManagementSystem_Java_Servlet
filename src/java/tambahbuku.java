
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@WebServlet("/tambahbuku")
public class tambahbuku extends HttpServlet {
    
    Connection connection;
    PreparedStatement prepare;
    int row;
    
    public void doPost(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws IOException{
        httpresponse.setContentType("text/html");
        PrintWriter out=httpresponse.getWriter();
        String id=httprequest.getParameter("txtid");
        String nama=httprequest.getParameter("txtbookname");
        String author=httprequest.getParameter("txtauthor");
        String year=httprequest.getParameter("txtyear");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanagementsystem","root","");
            
            
            prepare=connection.prepareStatement("insert into book(bookid,name,author,year)values(?,?,?,?)");
            prepare.setString(1, id);
            prepare.setString(2,nama);
            prepare.setString(3, author);
            prepare.setString(4, year);
            row=prepare.executeUpdate();
            
            out.println("<font color='green'> Buku Berhasil Ditambakan! </font>");
        } catch (Exception e) {
            out.println("Koneksi gagal "+e);
        }
    }
}
