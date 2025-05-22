package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import controller.Database_controller;

@WebServlet("/deleteFeedbackServlet")
public class deleteFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public deleteFeedbackServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String feedback = request.getParameter("feedback");
        
        Database_controller db = new Database_controller();
        
        try {
            int result = db.deleteFeedback(userName, email, feedback);
            if (result > 0) {
                // Successfully deleted
                response.sendRedirect("viewFeedbackServlet");
            } else {
                // Failed to delete
                response.sendRedirect("viewFeedbackServlet?error=delete_failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewFeedbackServlet?error=server_error");
        }
    }
} 