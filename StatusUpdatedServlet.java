package com.grievance;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StatusUpdatedServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String complaintNumber = request.getParameter("complaintNumber");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Status Updated</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("body {");
        response.getWriter().println("  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
        response.getWriter().println("  background-color: #f0f8ff;");
        response.getWriter().println("  display: flex;");
        response.getWriter().println("  flex-direction: column;");
        response.getWriter().println("  align-items: center;");
        response.getWriter().println("  justify-content: center;");
        response.getWriter().println("  height: 100vh;");
        response.getWriter().println("  margin: 0;");
        response.getWriter().println("  text-align: center;");
        response.getWriter().println("}");
        response.getWriter().println("h1 { color: #2e8b57; }");
        response.getWriter().println("p { font-size: 18px; color: #333; }");
        response.getWriter().println("a {");
        response.getWriter().println("  display: inline-block;");
        response.getWriter().println("  margin-top: 20px;");
        response.getWriter().println("  padding: 10px 20px;");
        response.getWriter().println("  background-color: #4CAF50;");
        response.getWriter().println("  color: white;");
        response.getWriter().println("  text-decoration: none;");
        response.getWriter().println("  border-radius: 5px;");
        response.getWriter().println("  transition: background-color 0.3s;");
        response.getWriter().println("}");
        response.getWriter().println("a:hover { background-color: #45a049; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Complaint Number " + complaintNumber + " Status Updated</h1>");
        response.getWriter().println("<p>Status: <strong>" + status + "</strong></p>");
        response.getWriter().println("<a href='index.html'>Go back to Home</a>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
