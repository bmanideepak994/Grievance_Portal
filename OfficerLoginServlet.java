package com.grievance;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class OfficerLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/grievance_portal", "root", "Bmd#09082004");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM officer_login WHERE userid=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                res.sendRedirect("update.html");
            } else {
                PrintWriter out = res.getWriter();
                res.setContentType("text/html");
                out.println("<h3 style='color:red;'>Invalid Username or Password</h3>");
                out.println("<a href='officers.html'>Back to Login</a>");
            }

            con.close();
        } catch (Exception e) {
            PrintWriter out = res.getWriter();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
