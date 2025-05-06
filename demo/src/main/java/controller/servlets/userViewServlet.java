package controller.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Database_controller;
import model.ProductModel;

/**
 * Servlet implementation class userViewServlet
 */
@WebServlet("/userViewServlet")
public class userViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
//		System.out.println("view servlet ");
		Database_controller dbController = new Database_controller();
		
		List<ProductModel> products = null;
		
		try {
			products = dbController.getAllProducts();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	     request.setAttribute("products", products);
	     request.getRequestDispatcher("Pages/userDashboard.jsp").forward(request, response);	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
