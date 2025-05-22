package controller.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;

/**
 * Servlet implementation class removeCartItemServlet
 */
@WebServlet("/removeCartItemServlet")
public class removeCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public removeCartItemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database_controller db = new Database_controller();

		// Get cart item ID parameter
		String cartIdStr = request.getParameter("cartId");

		// Validate the cartId parameter
		if (cartIdStr == null || cartIdStr.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cart ID is missing");
			return;
		}

		try {
			// Parse cartId to integer
			int cartId = Integer.parseInt(cartIdStr);
			// Remove item from database
			db.removeItem(cartId);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Cart ID format");
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
			return;
		}

		// Redirect to show cart page
		response.sendRedirect("showCartServlet");
	}
}