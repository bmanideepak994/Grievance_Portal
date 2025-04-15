package com.grievance;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class OfficerSignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("name");
        String designation = req.getParameter("designation");
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/grievance_portal", "root", "Bmd#09082004");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO officer_login (userid, name, designation, password) VALUES (?, ?, ?, ?)");
            ps.setString(1, userid);
            ps.setString(2, name);
            ps.setString(3, designation);
            ps.setString(4, password);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3 style='color:green;'>Signup successful! <a href='officers.html'>Login now</a></h3>");
            } else {
                out.println("<h3 style='color:red;'>Signup failed!</h3>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
