package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;

@WebServlet("/deleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null || orderIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order ID is missing");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);
            Database_controller db = new Database_controller();
            int result = db.deleteOrder(orderId);
            
            if (result > 0) {
                request.getSession().setAttribute("message", "Order deleted successfully");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to delete order");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Invalid Order ID format");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Database error occurred");
        }

        response.sendRedirect("orderListServlet");
    }
} 
