package com.grievance;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ComplaintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        String district = request.getParameter("district");
        String mandal = request.getParameter("mandal");
        String complaint = request.getParameter("complaint");

        int complaintNumber = (int) (Math.random() * 900000) + 100000;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/grievance_portal", "root", "Bmd#09082004");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO complaints (complaint_number, name, designation, district, mandal, complaint, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, complaintNumber);
            ps.setString(2, name);
            ps.setString(3, designation);
            ps.setString(4, district);
            ps.setString(5, mandal);
            ps.setString(6, complaint);
            ps.setString(7, "Pending");
            ps.executeUpdate();

            response.sendRedirect("randomnum.html?number=" + complaintNumber + "&name=" + name +
                    "&designation=" + designation + "&district=" + district + "&mandal=" + mandal +
                    "&complaint=" + complaint);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
