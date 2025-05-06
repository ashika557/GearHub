package controller.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;
import model.cartModel;

/**
 * Servlet implementation class showCartServlet
 */
@WebServlet("/showCartServlet")
public class showCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Database_controller db = new Database_controller();
		String username = (String) request.getSession().getAttribute("username");
		
		int userID = 0;
		try {
			userID = db.getUserIDByUsername(username);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	        
	    List<cartModel> cartItems = null;
		try {
			cartItems = db.getCartItemsByUserID(userID);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	        // Set the cartItems attribute and forward to cart.jsp
	    request.setAttribute("cartItems", cartItems);
	    request.getRequestDispatcher("Pages/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
