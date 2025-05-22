package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;

@WebServlet("/addCartServlet")
public class addCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = (String) request.getSession().getAttribute("username");
		System.out.println("username: " + username);
		if (username == null) {
			response.sendRedirect("Pages/login.jsp");
			return;
		}

		try {
			String productId = request.getParameter("productId");
			String quantity = request.getParameter("quantity");
			String price = request.getParameter("price");

			int productIdInt = Integer.parseInt(productId);
			int quantityInt = Integer.parseInt(quantity);
			double priceDouble = Double.parseDouble(price);
			int priceInt = (int) priceDouble; // This drops any decimal part
			int totalPrice = quantityInt * priceInt;

			System.out.println(productId + " " + quantity + " " + price + " " );
			Database_controller db = new Database_controller();
			int userID = db.getUserIDByUsername(username);
			System.out.println(userID + "here");
			
			// Add to cart
			db.addToCart(userID, productIdInt, quantityInt, totalPrice);
			
			// Create order
			db.createOrder(userID, productIdInt, quantityInt, totalPrice);
			
			System.out.println("working");
			// Optional: add success feedback using session
			request.getSession().setAttribute("cartMessage", "Product added to cart successfully.");
			response.sendRedirect("userViewServlet");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product data.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
		}
	}
}
