package com.grievance;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int complaintNumber = Integer.parseInt(request.getParameter("complaintNumber"));
        String status = request.getParameter("status"); // Get selected status

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/grievance_portal", "root", "Bmd#09082004");

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE complaints SET status = ? WHERE complaint_number = ?");
            ps.setString(1, status);
            ps.setInt(2, complaintNumber);

            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                response.sendRedirect("statusUpdated.html?complaintNumber=" + complaintNumber + "&status=" + status);
            } else {
                response.getWriter().println("Error: Complaint number not found.");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
