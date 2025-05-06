package controller.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "AdminLoginServlet", value = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Pages/adminlogin.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Allow only admin credentials
        if ("admin".equals(username) && "admin123".equals(password)) {
            // Start session and store admin details
            HttpSession session = request.getSession();
            session.setAttribute("username", "admin");
            session.setAttribute("isAdmin", true);
            session.setMaxInactiveInterval(30 * 60); // 30-minute session timeout

            // Redirect to admin dashboard
            response.sendRedirect(request.getContextPath() + "/Pages/admin.jsp");
        } else {
            // Login failed, redirect with error flag
            response.sendRedirect(request.getContextPath() + "/Pages/adminlogin.jsp?error=true");
        }
    }

}