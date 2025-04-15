package com.grievance;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class TrackComplaintServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int complaintNumber = Integer.parseInt(request.getParameter("complaintNumber"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
            		"jdbc:mysql://localhost:3306/grievance_portal", "root", "Bmd#09082004");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM complaints WHERE complaint_number = ?");
            ps.setInt(1, complaintNumber);
            ResultSet rs = ps.executeQuery();

            out.println("<html><head><title>Complaint Status</title><style>");
            out.println("body { font-family: Arial; background-color: #f2f2f2; text-align: center; padding: 30px; }");
            out.println("table { margin: auto; border-collapse: collapse; width: 80%; background-color: #fff; }");
            out.println("th, td { padding: 12px; border: 1px solid #ccc; }");
            out.println("th { background-color: #0a9396; color: white; }");
            out.println("</style></head><body>");

            if (rs.next()) {
                out.println("<h2>Complaint Details</h2>");
                out.println("<table>");
                out.println("<tr><th>Complaint Number</th><td>" + rs.getInt("complaint_number") + "</td></tr>");
                out.println("<tr><th>Name</th><td>" + rs.getString("name") + "</td></tr>");
                out.println("<tr><th>Designation</th><td>" + rs.getString("designation") + "</td></tr>");
                out.println("<tr><th>District</th><td>" + rs.getString("district") + "</td></tr>");
                out.println("<tr><th>Mandal</th><td>" + rs.getString("mandal") + "</td></tr>");
                out.println("<tr><th>Complaint</th><td>" + rs.getString("complaint") + "</td></tr>");
                out.println("<tr><th>Status</th><td>" + rs.getString("status") + "</td></tr>");
                out.println("</table>");
            } else {
                out.println("<h3 style='color:red;'>No complaint found with number: " + complaintNumber + "</h3>");
            }

            out.println("<br><a href='index.html'>Back to Home</a>");
            out.println("</body></html>");

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
